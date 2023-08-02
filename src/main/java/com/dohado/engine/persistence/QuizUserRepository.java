package com.dohado.engine.persistence;

import com.dohado.engine.business.model.QuizUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface QuizUserRepository extends CrudRepository<QuizUser, Long> {

    public Optional<QuizUser> findQuizUserByEmail(String email);
    public boolean existsByEmail(String email);

}
