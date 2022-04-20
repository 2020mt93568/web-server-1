package com.example.todo.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LoginRequestDTO {

    @NotEmpty
    private String emailId;
    @NotEmpty
    private String password;

}