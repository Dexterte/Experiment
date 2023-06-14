package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
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

    @PostMapping("/name/search")
    public String search(@RequestParam("name") String name, Model model) {
	// 検索結果
	List<User> users = userpository.selectUsersByName(name);
	model.addAttribute("users", users);
	return "user/index";
    }
}
