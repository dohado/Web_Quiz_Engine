package com.dohado.engine.business.service;

import com.dohado.engine.business.model.QuizUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    QuizUserService quizUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QuizUser quizUser = quizUserService.findUserByEmail(username);
        return User.withUsername(quizUser.getEmail())
                .password(quizUser.getPassword())
                .build();
    }
}
