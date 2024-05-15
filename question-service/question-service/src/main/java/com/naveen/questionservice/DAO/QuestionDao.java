package com.naveen.questionservice.DAO;

import com.naveen.questionservice.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question,Long> {

  @Query("SELECT q.id FROM Question q WHERE q.category = :categoryName ORDER BY RAND() LIMIT :numQuestions")
  List<Long> findRandomQuestionsByCategory(String categoryName, Long numQuestions);

}
