package com.workshopFinder.workshopFinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {

    @GetMapping("/")
    public String mostrarHome(Model model){
        return "home";
    }

    /**
    //Damos uso al objeto auth para obtener datos del usuario iniciado
    @GetMapping("/index")
    public String mostrarIndex(Authentication auth, HttpSession session){
        String username = auth.getName();
        System.out.println("Nombre del usuario: " + username);
        for(GrantedAuthority rol: auth.getAuthorities()){
            System.out.println("ROL: " + rol.getAuthority());
        }

        if(session.getAttribute("Usuario") == null){
            Usuario usuario = serviceUsuarios.buscarPorUsername(username);
            usuario.setPassword(null);
            System.out.println("Usuario: " + usuario);
            session.setAttribute("usuario", usuario);
        }
        return "redirect:/";
    }
    */
}
