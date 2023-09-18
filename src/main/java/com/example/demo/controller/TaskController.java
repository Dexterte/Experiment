package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.TaskForm;

import jakarta.validation.Valid;

@Controller()
@RequestMapping("/task")
public class TaskController {

    @GetMapping("")
    public String index() {
	return "task/index";
    }

    @GetMapping("/new")
    public String newForm(@ModelAttribute TaskForm form) {
	return "task/new";
    }
}