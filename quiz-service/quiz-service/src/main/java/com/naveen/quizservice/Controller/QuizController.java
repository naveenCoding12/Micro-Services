package com.naveen.quizservice.Controller;


import com.naveen.quizservice.Dto.QuizDto;
import com.naveen.quizservice.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
public class QuizController {

  @Autowired
  private QuizService quizService;

  @GetMapping("/create")
  public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
    return quizService.createQuiz(quizDto);
  }
}
