package com.dohado.engine.exceptions;

public class UserAlreadyRegisteredException extends CustomException {

    public UserAlreadyRegisteredException(String email) {
        super("User with email " + email + " already registered");
    }

}
