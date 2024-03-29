package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.TaskForm;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;

@Controller()
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;
    private final TaskService taskService;

    public TaskController(TaskRepository taskRepository, TaskService taskService) {
	this.taskRepository = taskRepository;
	this.taskService = taskService;
    }

    @GetMapping("")
    public String index(Model model) {
	var tasks = taskRepository.selectTasks();
	model.addAttribute("tasks", tasks);
	return "task/index";
    }

    @GetMapping("/new")
    public String newForm(@ModelAttribute TaskForm form) {
	return "task/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute @Validated TaskForm form, BindingResult result) {
	if (result.hasErrors()) {
	    return "task/new";
	}
	this.taskService.insertTask(form);
	return "redirect:/task";
    }
}