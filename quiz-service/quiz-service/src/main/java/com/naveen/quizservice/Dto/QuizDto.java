package com.naveen.quizservice.Dto;

import lombok.Data;

@Data
public class QuizDto {
  String categoryName;
  Long numQuestions;
  String title;
}
