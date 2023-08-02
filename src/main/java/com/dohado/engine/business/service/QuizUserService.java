package com.dohado.engine.business.service;

import com.dohado.engine.business.model.QuizUser;
import com.dohado.engine.exceptions.UserAlreadyRegisteredException;
import com.dohado.engine.exceptions.UserNotFoundException;
import com.dohado.engine.persistence.QuizUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class QuizUserService {

    @Autowired
    private QuizUserRepository quizUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(QuizUserService.class);

    public QuizUser saveUser(QuizUser quizUser) {
        if (!quizUserRepository.existsByEmail(quizUser.getEmail())) {
            quizUser.setPassword(passwordEncoder.encode(quizUser.getPassword()));
            logger.info("Saving user {}", quizUser);
            return quizUserRepository.save(quizUser);
        } else {
            logger.info("User {} already exist", quizUser);
            throw new UserAlreadyRegisteredException(quizUser.getEmail());
        }
    }

    public QuizUser findUserByEmail(String email) {
        return quizUserRepository.findQuizUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

    }

    public boolean existByEmail(String email) {
        return quizUserRepository.existsByEmail(email);
    }



}
