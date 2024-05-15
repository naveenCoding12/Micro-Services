package com.naveen.quizservice.Interface;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

  @GetMapping("questions/generate")
  public ResponseEntity<List<Long>>  getQuestionsForQuiz
    (@RequestParam String categoryName, @RequestParam Long numQuestions );
}
