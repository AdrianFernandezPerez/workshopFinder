package com.workshopFinder.workshopFinder.controller;

import com.workshopFinder.workshopFinder.model.User;
import com.workshopFinder.workshopFinder.model.Vehicle;
import com.workshopFinder.workshopFinder.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

import static com.workshopFinder.workshopFinder.controller.HomeController.defaultProperties;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/profile")
    public String formProfileUser(Model model) {
        String email = (String) defaultProperties.get("email");
        User user1 = userService.findByEmail(email);
        model.addAttribute("user", user1);
        return "formProfileUser";
    }

    @GetMapping("/profile/vehicles")
    public String profileVehicles(Model model) {
        String email = (String) defaultProperties.get("email");
        User user1 = userService.findByEmail(email);
        System.out.println(user1);
        Collection<Vehicle> vehicles = user1.getVehiculos();
        model.addAttribute("user", user1);
        model.addAttribute("vehicles", vehicles);

        return "profileVehicles";
    }




}
