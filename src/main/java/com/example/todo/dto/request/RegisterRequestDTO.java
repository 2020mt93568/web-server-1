package com.example.todo.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegisterRequestDTO {

    @Email
    @NotEmpty
    private String emailId;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

}