package com.example.todo.dto.response;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProfileResponseDTO {

    private String referenceId;

    private String username;

    private String emailId;

    private Date createdAt;

    private Date updatedAt;

}