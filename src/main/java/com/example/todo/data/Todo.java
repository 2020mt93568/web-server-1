package com.example.todo.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="Todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(unique = true)
    private String referenceId;

    @NotNull(message = "Todo's title must not be null")
    private String title;

    private String description;

    private Date date;

    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "Profile_id")
    private Profile profile;

    private Date createdAt;

    private Date updatedAt;

}
