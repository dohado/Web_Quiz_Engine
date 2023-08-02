package com.dohado.engine.business.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Response {
    SUCCESS(true, "Congratulations, you're right!"),
    FAILURE(false, "Wrong answer! Please, try again.");

    private boolean success;
    private String feedback;


}
