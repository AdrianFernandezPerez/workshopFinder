package com.workshopFinder.workshopFinder.controller;

import com.workshopFinder.workshopFinder.model.User;
import com.workshopFinder.workshopFinder.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/profile/{email}")
    public String formProfileUser(@PathVariable(value="email") String email, Model model)  {
        User user1 = userService.findByEmail(email);
        model.addAttribute("user", user1);
        return "formProfileUser";
    }

}
