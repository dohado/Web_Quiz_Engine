package com.dohado.engine.business.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompletedQuizzesStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "quiz_id")
    @JsonProperty("id")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Quiz quiz;

    @ManyToOne(optional = false)
    @JoinColumn(name = "quiz_user_id")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private QuizUser quizUser;

    @CurrentTimestamp
    private LocalDateTime completedAt;

}
