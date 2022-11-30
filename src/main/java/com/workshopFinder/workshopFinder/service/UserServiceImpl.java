package com.workshopFinder.workshopFinder.service;

import com.workshopFinder.workshopFinder.model.User;
import com.workshopFinder.workshopFinder.model.Vehicle;
import com.workshopFinder.workshopFinder.repository.UserRepository;
import com.workshopFinder.workshopFinder.service.exception.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * Crea el objeto user y lo guarda en la base de datos
     */
    @Transactional
    public User newUser(String nombre, int telefono, String email, String direccion, String dni) {
        User user = new User(nombre, telefono, email, direccion, dni, false);
        userRepository.save(user);
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Transactional(readOnly = true)
    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Transactional
    public User updateUser(Long idUser, String nuevoNombre, int nuevoTelefono, String nuevoEmail, String nuevaDireccion, String nuevoDni) {
        User user = userRepository.findById(idUser).orElse(null);
        if (user == null) {
            throw new UserServiceException("No existe usuario con id " + idUser);
        }
        user.setNombre(nuevoNombre);
        user.setTelefono(nuevoTelefono);
        user.setEmail(nuevoEmail);
        user.setDireccion(nuevaDireccion);
        user.setDni(nuevoDni);
        user.setPremium(false);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void deleteUser(Long idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        if (user == null) {
            throw new UserServiceException("No existe usuario con id " + idUser);
        }
        userRepository.delete(user);
    }

    public void addVehicle(Long idUser, Vehicle vehicle) {
        User user = userRepository.findById(idUser).orElse(null);
        if (user == null) {
            throw new UserServiceException("No existe usuario con id " + idUser);
        }
        user.addVehiculos(vehicle);
        userRepository.save(user);
    }

}
