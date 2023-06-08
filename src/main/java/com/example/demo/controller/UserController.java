package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.User;
import com.example.demo.repository.UserReository;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserReository userReository;

    public UserController(UserReository userReository) {
	this.userReository = userReository;
    }

    @GetMapping("")
    public String index(Model model) {
	List<User> users = userReository.selectUsers();
	model.addAttribute("users", users);
	return "user/index";
    }

}
