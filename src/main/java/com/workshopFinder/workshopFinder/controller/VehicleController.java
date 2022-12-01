package com.workshopFinder.workshopFinder.controller;

import com.workshopFinder.workshopFinder.model.User;
import com.workshopFinder.workshopFinder.model.Vehicle;
import com.workshopFinder.workshopFinder.service.IVehicleService;
import com.workshopFinder.workshopFinder.service.UserServiceImpl;
import com.workshopFinder.workshopFinder.service.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.workshopFinder.workshopFinder.controller.HomeController.defaultProperties;

@Controller
public class VehicleController {

    @Autowired
    private VehicleServiceImpl vehicleService;

    @Autowired
    private UserServiceImpl userService;


    //Si estamos iniciados sesión nos muestra el formulario de creación de vehiculos, si no, redirecciona al login
    @GetMapping("/vehicle/new")
    public String profileNewVehicle(@ModelAttribute Vehicle vehicle, Model model) {
        String email = (String) defaultProperties.get("email");
        User user1 = userService.findByEmail(email);
        model.addAttribute("user", user1);
        return "formNewVehicle";
    }

    //Crea un vehiculo con los datos introducidos y muestra la lista de vehiculos
    @PostMapping("/vehicle/new")
    public String nuevoVehiculo(@ModelAttribute Vehicle vehicle,
                                Model model, RedirectAttributes flash) {
        String email = (String) defaultProperties.get("email");
        User user1 = userService.findByEmail(email);
        model.addAttribute("user", user1);
        vehicleService.addVehicle(user1.getIdUser(), vehicle);
        flash.addFlashAttribute("mensaje", "Vehiculo creado correctamente");
        return "redirect:/profile/vehicles";
    }

    //Metodo para eliminar un vehiculo
    @GetMapping("/vehicle/{id}")
    public String borrarVehiculo(@PathVariable(value="id") Long idVehiculo,Model model, RedirectAttributes flash) {
        String email = (String) defaultProperties.get("email");
        flash.addFlashAttribute("email",email);
        Vehicle vehicle = vehicleService.findById(idVehiculo);
        vehicleService.deleteVehicle(idVehiculo);
        flash.addFlashAttribute("msg", "El Vehiculo fue eliminado!");
        return "redirect:/profile/vehicles";
    }


}
