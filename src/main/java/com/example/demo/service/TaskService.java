package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.form.TaskForm;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
	this.taskRepository = taskRepository;
    }

    /**
     * タスクの新規登録
     * 
     * @param taskForm
     */
    public void insertTask(TaskForm taskForm) {
	var task = new Task();
	task.setTitle(taskForm.getTitle());
	task.setContent(taskForm.getContent());
	this.taskRepository.insertTask(task);
    }
}
