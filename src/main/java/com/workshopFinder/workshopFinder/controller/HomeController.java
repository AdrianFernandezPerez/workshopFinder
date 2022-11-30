package com.workshopFinder.workshopFinder.controller;

import com.workshopFinder.workshopFinder.authentication.ManagerUserSession;
import com.workshopFinder.workshopFinder.model.User;
import com.workshopFinder.workshopFinder.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
public class HomeController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ManagerUserSession managerUserSession;

    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        User user = userService.findById(managerUserSession.usuarioLogeado(session));
        System.out.println(user);
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginData", new LoginData());
        return "formLogin";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute LoginData loginData, Model model, HttpSession session) {

        // Llamada al servicio para comprobar si el login es correcto
        UserServiceImpl.LoginStatus loginStatus = userService.login(loginData.geteMail(), loginData.getPassword());

        if (loginStatus == UserServiceImpl.LoginStatus.LOGIN_OK) {
            User user = userService.findByEmail(loginData.geteMail());

            managerUserSession.logearUsuario(session, user.getIdUser());

            return "redirect:/home";
        } else if (loginStatus == UserServiceImpl.LoginStatus.USER_NOT_FOUND) {
            model.addAttribute("error", "No existe usuario");
            return "formLogin";
        } else if (loginStatus == UserServiceImpl.LoginStatus.ERROR_PASSWORD) {
            model.addAttribute("error", "Contraseña incorrecta");
            return "formLogin";
        }
        return "formLogin";
    }

    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("registroData", new User());
        return "formRegistro";
    }

    @PostMapping("/registro")
    public String registroSubmit(@Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "formRegistro";
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("registroData", user);
            model.addAttribute("error", "El usuario " + user.getEmail() + " ya existe");
            return "formRegistro";
        }

        User user1 = new User(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setDireccion(user.getDireccion());
        user1.setDni(user.getDni());
        user1.setTelefono(user.getTelefono());
        user1.setNombre(user.getNombre());

        userService.registrar(user1);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        managerUserSession.logout(session);
        return "redirect:/login";
    }

}
