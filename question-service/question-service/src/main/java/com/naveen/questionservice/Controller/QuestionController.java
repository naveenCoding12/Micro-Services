package com.naveen.questionservice.Controller;


import com.naveen.questionservice.Dto.QuestionVo;
import com.naveen.questionservice.Entity.Question;
import com.naveen.questionservice.Entity.QuestionWrapper;
import com.naveen.questionservice.Entity.Response;
import com.naveen.questionservice.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

  @Autowired
  private QuestionService questionService;
  @GetMapping("/allQuestions")
  public ResponseEntity<List<Question>> getAllQuestions(){
    return  questionService.getAllQuestions();
  }

  @PostMapping("/add")
  public ResponseEntity<String> addQuestions(@RequestBody  QuestionVo questionVo){
    return  questionService.addQuestions(questionVo);
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateQuestions(@RequestParam Long id ,@RequestBody QuestionVo questionVo){
    return questionService.updateQuestions(id,questionVo);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteQuestion(@RequestParam Long id){
    return questionService.deleteQuestion(id);
  }

  @GetMapping("/generate")
  public ResponseEntity<List<Long>> getQuestionsForQuiz
    (@RequestParam String categoryName, @RequestParam Long numQuestions ){
    return questionService.getQuestionsForQuiz(categoryName, numQuestions);
  }

  @PostMapping("/getQuestions")
  public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Long> questionIds) {
    return questionService.getQuestionsFromId(questionIds);
  }

  @PostMapping("/getScore")
  public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
  {
    return questionService.getScore(responses);
  }

}
