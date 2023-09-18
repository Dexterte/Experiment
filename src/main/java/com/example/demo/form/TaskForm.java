package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskForm {

    @NotBlank
    private String title;
    private String content;
}
