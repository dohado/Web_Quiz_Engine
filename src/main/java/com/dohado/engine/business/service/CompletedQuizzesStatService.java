package com.dohado.engine.business.service;

import com.dohado.engine.business.model.CompletedQuizzesStat;
import com.dohado.engine.business.model.QuizUser;
import com.dohado.engine.persistence.CompletedQuizzesStatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CompletedQuizzesStatService {

    @Autowired
    private CompletedQuizzesStatRepository statRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CompletedQuizzesStatService.class);
    private static final int DEFAULT_PAGE_SIZE = 10;

    public Iterable<CompletedQuizzesStat> getStat(QuizUser quizUser, int pageNumber) {
        return statRepository.findAllByQuizUser(quizUser,
                PageRequest.of(pageNumber, DEFAULT_PAGE_SIZE, Sort.by("completedAt").descending()));
    }

    public CompletedQuizzesStat addStat(CompletedQuizzesStat completedQuizzesStat) {
        return statRepository.save(completedQuizzesStat);
    }

}
