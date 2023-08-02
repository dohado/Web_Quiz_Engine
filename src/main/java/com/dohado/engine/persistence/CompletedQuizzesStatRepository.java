package com.dohado.engine.persistence;

import com.dohado.engine.business.model.CompletedQuizzesStat;
import com.dohado.engine.business.model.QuizUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompletedQuizzesStatRepository extends PagingAndSortingRepository<CompletedQuizzesStat, Long>,
        CrudRepository<CompletedQuizzesStat, Long> {

    Page<CompletedQuizzesStat> findAllByQuizUser(QuizUser quizUser, Pageable pageable);

}
