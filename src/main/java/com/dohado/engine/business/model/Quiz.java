package com.dohado.engine.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Title should not be blank")
    private String title;

    @NotBlank(message = "Text should not be blank")
    @Column(nullable = false)
    private String text;

    @Size(min = 2, message = "At least two options should be provided")
    @NotNull(message = "Options are mandatory field")
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(nullable = false)
    @Fetch(FetchMode.SUBSELECT)
    private List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Integer> answer;

    @Column(name = "created_by")
    @JsonIgnore
    private String createdByUserWithEmail;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    Set<CompletedQuizzesStat> completedQuizzesStats;



}
