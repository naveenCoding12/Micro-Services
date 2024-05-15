package com.naveen.quizservice.Service;


import com.naveen.quizservice.DAo.QuizDao;
import com.naveen.quizservice.Dto.QuizDto;
import com.naveen.quizservice.Entity.Quiz;
import com.naveen.quizservice.Interface.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

  @Autowired
  private QuizDao quizDao;

  @Autowired
  private QuizInterface quizInterface;
  public ResponseEntity<String> createQuiz(QuizDto quizDto) {
    List<Long> question=quizInterface.getQuestionsForQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions()).getBody();
    Quiz quiz=new Quiz();
    quiz.setTitle(quizDto.getTitle());
    quiz.setQuestionIds(question);

    quizDao.save(quiz);
    return  new ResponseEntity<>("created successfully", HttpStatus.CREATED);



  }
}
