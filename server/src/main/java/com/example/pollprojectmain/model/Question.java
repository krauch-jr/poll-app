package com.example.pollprojectmain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "poll_id", nullable = false)
    @JsonIgnore
    private Poll poll;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> answers = new ArrayList<>();

    @Column(nullable = false)
    private Boolean multiple;

    @Column(nullable = false)

    private String text;

    public Question(Poll poll, List<Answer> answers, Boolean multiple, String text) {
        this.poll = poll;
        this.answers = answers;
        this.multiple = multiple;
        this.text = text;
    }

    public Question produceCopyTo(Poll poll) {
        Question copyQuestion = new Question();
        copyQuestion.setPoll(poll);
        copyQuestion.setText(this.getText());
        copyQuestion.setMultiple(this.getMultiple());

        for (Answer answer : this.getAnswers()) {
            copyQuestion.getAnswers().add(answer.produceCopyTo(copyQuestion));
        }

        return copyQuestion;
    }
}
