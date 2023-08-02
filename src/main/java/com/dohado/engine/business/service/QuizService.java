package com.dohado.engine.business.service;

import com.dohado.engine.business.model.Quiz;
import com.dohado.engine.exceptions.QuizNotFoundException;
import com.dohado.engine.persistence.QuizRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;
    private final static Logger LOGGER = LoggerFactory.getLogger(QuizService.class);
    private final static int DEFAULT_PAGE_SIZE = 10;


    public Quiz addQuiz(Quiz quiz) {
        LOGGER.info("Adding new quiz {}", quiz);
        Quiz addedQuiz = quizRepository.save(quiz);
        LOGGER.info("Quiz added, id = {}", addedQuiz.getId());
        return addedQuiz;
    }

    public Quiz getQuizById(long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException(id));
        LOGGER.info("Quiz stat: {}", quiz.getCompletedQuizzesStats());
        return quiz;
    }

    public Iterable<Quiz> getAllQuizzes(int pageNumber) {
        return quizRepository.findAll(PageRequest.of(pageNumber, DEFAULT_PAGE_SIZE));
    }

    public boolean checkAnswer(Quiz quiz, List<Integer> answer) {
        LOGGER.info("Checking answer {} for quiz {}", answer, quiz);
        return answer.equals(quiz.getAnswer());
    }

    public void deleteQuizById(long id) {
        quizRepository.deleteById(id);
    }



}
