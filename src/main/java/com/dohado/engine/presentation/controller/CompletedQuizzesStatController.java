package com.dohado.engine.presentation.controller;

import com.dohado.engine.business.model.CompletedQuizzesStat;
import com.dohado.engine.business.model.QuizUser;
import com.dohado.engine.business.service.CompletedQuizzesStatService;
import com.dohado.engine.business.service.QuizUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CompletedQuizzesStatController {

    @Autowired
    CompletedQuizzesStatService statService;
    @Autowired
    QuizUserService quizUserService;

    @GetMapping("/quizzes/completed")
    public Iterable<CompletedQuizzesStat> getStat(
            @RequestParam int page,
            @AuthenticationPrincipal UserDetails userDetails) {
        QuizUser quizUser = quizUserService.findUserByEmail(userDetails.getUsername());
        return statService.getStat(quizUser, page);
    }

}
