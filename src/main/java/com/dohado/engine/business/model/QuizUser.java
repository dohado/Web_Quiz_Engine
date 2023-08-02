package com.dohado.engine.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class QuizUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Email(message = "Please provide a valid email address", regexp = "\\w+@\\w+\\.\\w+")
    @NotBlank(message = "Email should not be blank")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 5, message = "A password must have at least five characters")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private String password;

    @OneToMany(mappedBy = "quizUser", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    Set<CompletedQuizzesStat> completedQuizzesStats;


}
