package com.naveen.questionservice.Dto;

import lombok.Data;

@Data
public class QuestionVo {

  private String questionTitle;
  private String option1;
  private String option2;
  private String option3;
  private String option4;
  private String rightAnswer;
  private String difficultylevel;
  private String category;
}