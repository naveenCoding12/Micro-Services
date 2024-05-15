package com.naveen.questionservice.Service;


import com.naveen.questionservice.DAO.QuestionDao;
import com.naveen.questionservice.Dto.QuestionVo;
import com.naveen.questionservice.Entity.Question;
import com.naveen.questionservice.Entity.QuestionWrapper;
import com.naveen.questionservice.Entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {


  @Autowired
  private QuestionDao questionDao;

  public ResponseEntity<List<Question>> getAllQuestions() {
    try {
      List<Question> questions = questionDao.findAll();

      return new ResponseEntity<>(questions, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
  }

  public ResponseEntity<String> addQuestions(QuestionVo questionVo) {
    try {
      Question question = new Question();
      question.setCategory(questionVo.getCategory());
      question.setDifficultylevel(questionVo.getDifficultylevel());
      question.setQuestionTitle(questionVo.getQuestionTitle());
      question.setOption1(questionVo.getOption1());
      question.setOption2(questionVo.getOption2());
      question.setOption3(questionVo.getOption3());
      question.setOption4(questionVo.getOption4());
      question.setRightAnswer(questionVo.getRightAnswer());

      questionDao.save(question);
      return new ResponseEntity<>("questions added successfully", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>("internal server error in server side", HttpStatus.BAD_REQUEST);
    }
  }

  public ResponseEntity<String> updateQuestions(Long id, QuestionVo questionVo) {
    try {
      Optional<Question> optionalQuestion = questionDao.findById(id);
      if (optionalQuestion.isPresent()) {
        Question question = optionalQuestion.get();
        question.setCategory(questionVo.getCategory());
        question.setDifficultylevel(questionVo.getDifficultylevel());
        question.setQuestionTitle(questionVo.getQuestionTitle());
        question.setOption1(questionVo.getOption1());
        question.setOption2(questionVo.getOption2());
        question.setOption3(questionVo.getOption3());
        question.setOption4(questionVo.getOption4());
        question.setRightAnswer(questionVo.getRightAnswer());

        questionDao.save(question);
        return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Question not found with id: " + id, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>("Internal server error on the server side", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<String> deleteQuestion(Long id) {
    try {
      questionDao.deleteById(id);
      return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("internal server error", HttpStatus.BAD_REQUEST);
    }
  }


  public ResponseEntity<List<Long>> getQuestionsForQuiz(String categoryName, Long numQuestions) {
    List<Long> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);
    return new ResponseEntity<>(questions, HttpStatus.OK);
  }

  public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Long> questionIds) {
    try {
      List<QuestionWrapper> wrappers = new ArrayList<>();
      List<Question> questions = new ArrayList<>();

      for (Long id : questionIds) {
        Optional<Question> optionalQuestion = questionDao.findById(id);
        if (optionalQuestion.isPresent()) {
          questions.add(optionalQuestion.get());
        } else {
          // If the question ID doesn't exist, return a 404 Not Found response
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      }

      for (Question question : questions) {
        QuestionWrapper wrapper = new QuestionWrapper();
        wrapper.setId(question.getId());
        wrapper.setQuestionTitle(question.getQuestionTitle());
        wrapper.setOption1(question.getOption1());
        wrapper.setOption2(question.getOption2());
        wrapper.setOption3(question.getOption3());
        wrapper.setOption4(question.getOption4());
        wrappers.add(wrapper);
      }

      return new ResponseEntity<>(wrappers, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  public ResponseEntity<Integer> getScore(List<Response> responses) {


    int right = 0;

    for (Response response : responses) {
      Optional<Question> questionResponse = questionDao.findById(response.getId());
      Question question = questionResponse.get();
      if (response.getResponse().equals(question.getRightAnswer()))
        right++;
      }
      return new ResponseEntity<>(right, HttpStatus.OK);
    }

  }

