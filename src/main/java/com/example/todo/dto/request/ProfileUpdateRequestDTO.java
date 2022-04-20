package com.example.todo.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProfileUpdateRequestDTO {

    private String username;

    private String password;

    private String newPassword;

}