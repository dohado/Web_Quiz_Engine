package com.dohado.engine.presentation.controller;

import com.dohado.engine.business.model.QuizUser;
import com.dohado.engine.business.service.QuizUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuizUserController {

    @Autowired
    QuizUserService quizUserService;

    @PostMapping("/register")
    public QuizUser registerUser(@Valid @RequestBody QuizUser quizUser) {
        return quizUserService.saveUser(quizUser);
    }

}
