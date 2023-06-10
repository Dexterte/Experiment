package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.User;
import com.example.demo.repository.Userpository;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Userpository userpository;

    public UserController(Userpository userpository) {
	this.userpository = userpository;
    }

    @GetMapping("")
    public String index(Model model) {
	List<User> users = userpository.selectUsers();
	model.addAttribute("users", users);
	return "user/index";
    }
}
