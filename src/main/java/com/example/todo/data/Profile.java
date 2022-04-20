package com.example.todo.data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="Profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(unique = true)
    private String referenceId;

    @NotNull(message = "Profile's username must not be null")
    @Column(unique = true)
    private String username;

    @NotNull(message = "Profile's emailId must not be null")
    @Email
    @Column(unique = true)
    private String emailId;

    @NotNull(message = "Profile's password must not be null")
    private String password;

    private Date createdAt;

    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Todo> todos;

}
