package com.example.pollprojectmain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "create_at", nullable = false)
    private Timestamp createAt;

    public Vote(Answer answer, User user) {
        this.answer = answer;
        this.user = user;
        this.createAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public void setCreateAt(Timestamp createAt) {
        if (createAt == null) {
            this.createAt = Timestamp.valueOf(LocalDateTime.now());
            return;
        }
        this.createAt = createAt;
    }
}
