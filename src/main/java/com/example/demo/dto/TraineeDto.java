package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeDto {
    @NotNull
    private String name;

    @NotNull
    private String office;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String github;
}