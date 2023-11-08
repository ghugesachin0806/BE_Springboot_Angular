package com.example.coders_battle.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.coders_battle.Entity.Answers;

@Repository
public interface AnswerRepository extends JpaRepository<Answers,Long> {
    
}
