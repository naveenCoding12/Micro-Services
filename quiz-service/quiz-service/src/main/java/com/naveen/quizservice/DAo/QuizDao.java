package com.naveen.quizservice.DAo;


import com.naveen.quizservice.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao  extends JpaRepository<Quiz,Long> {
}
