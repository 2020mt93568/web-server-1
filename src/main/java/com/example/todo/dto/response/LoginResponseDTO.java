package com.example.todo.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LoginResponseDTO {
    private String token;
}