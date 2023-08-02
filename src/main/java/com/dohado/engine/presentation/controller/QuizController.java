package com.dohado.engine.presentation.controller;

import com.dohado.engine.business.model.CompletedQuizzesStat;
import com.dohado.engine.business.model.Quiz;
import com.dohado.engine.business.model.QuizUser;
import com.dohado.engine.business.model.Response;
import com.dohado.engine.business.service.CompletedQuizzesStatService;
import com.dohado.engine.business.service.QuizService;
import com.dohado.engine.business.service.QuizUserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    QuizService quizService;
    @Autowired
    QuizUserService quizUserService;
    @Autowired
    CompletedQuizzesStatService statService;

    private static final Logger LOGGER = LoggerFactory.getLogger(QuizController.class);

    @PostMapping("/quizzes/{id}/solve")
    public Response checkAnswer(@PathVariable long id ,@RequestBody Map<String, List<Integer>> answer
            , @AuthenticationPrincipal UserDetails userDetails) {
        LOGGER.info("Received new request for checking of quiz id = {}, answer = {}", id, answer.get("answer").toString());
        Quiz quiz = quizService.getQuizById(id);
        if (quizService.checkAnswer(quiz, answer.get("answer"))) {
            QuizUser quizUser = quizUserService.findUserByEmail(userDetails.getUsername());
            CompletedQuizzesStat completedQuizzesStat = new CompletedQuizzesStat();
            completedQuizzesStat.setQuizUser(quizUser);
            completedQuizzesStat.setQuiz(quiz);
            CompletedQuizzesStat addedStat = statService.addStat(completedQuizzesStat);
            LOGGER.info("New stat added: {}", addedStat);
            return Response.SUCCESS;
        } else {
            return Response.FAILURE;
        }
    }

    @PostMapping("/quizzes")
    public Quiz addQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal UserDetails userDetails) {
        QuizUser quizUser = quizUserService.findUserByEmail(userDetails.getUsername());
        quiz.setCreatedByUserWithEmail(quizUser.getEmail());
        return quizService.addQuiz(quiz);
    }

    @GetMapping("/quizzes/{id}")
    public Quiz getQuiz(@PathVariable long id) {
        return quizService.getQuizById(id);
    }

    @GetMapping("/quizzes")
    public Iterable<Quiz> getAllQuizzes(@RequestParam int page) {
        Page<Quiz> quizPage = (Page<Quiz>) quizService.getAllQuizzes(page);
        LOGGER.info("Returned quizzes, page #{}, number of quizzes: {}", page, quizPage.getSize());
        return quizPage;
    }

    @DeleteMapping("/quizzes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) {
        Quiz quiz = quizService.getQuizById(id);
        if (userDetails.getUsername().equals(quiz.getCreatedByUserWithEmail())) {
            quizService.deleteQuizById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Please provide id of quiz created by your user");
        }
    }


}
