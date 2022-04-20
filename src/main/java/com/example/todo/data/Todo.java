package com.example.todo.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table
public class Todo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotNull
    @Column(unique = true)
    private String referenceId;

    @NotNull(message = "Todo's title must not be null")
    private String title;

    private String description;

    private Date date;

    private Date startTime;

    private Boolean isCompleted;

    @ManyToOne
    private User user;

    private Date createdAt;

    private Date updatedAt;

}
