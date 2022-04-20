package com.example.todo.data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotNull
    @Column(unique = true)
    private String referenceId;

    @NotNull(message = "User's username must not be null")
    @Column(unique = true)
    private String username;

    @NotNull(message = "User's emailId must not be null")
    @Email
    @Column(unique = true)
    private String emailId;

    @NotNull(message = "User's password must not be null")
    private String password;

    private Date createdAt;

    private Date updatedAt;

    @OneToMany(mappedBy = "user")
    private List<Todo> todos;

}
