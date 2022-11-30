package com.workshopFinder.workshopFinder.service;

import com.workshopFinder.workshopFinder.model.User;
import com.workshopFinder.workshopFinder.model.Vehicle;
import com.workshopFinder.workshopFinder.repository.UserRepository;
import com.workshopFinder.workshopFinder.service.exception.UserServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public enum LoginStatus {LOGIN_OK, USER_NOT_FOUND, ERROR_PASSWORD}

    /*
     * Crea el objeto user y lo guarda en la base de datos
     */
    @Transactional
    public User newUser(String nombre, String password, int telefono, String email, String direccion, String dni) {
        User user = new User(nombre, password, telefono, email, direccion, dni);
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

    @Transactional(readOnly = true)
    public LoginStatus login(String eMail, String password) {
        Optional<User> user = userRepository.findByEmail(eMail);
        if (!user.isPresent()) {
            return LoginStatus.USER_NOT_FOUND;
        } else if (!user.get().getPassword().equals(password)) {
            return LoginStatus.ERROR_PASSWORD;
        } else {
            return LoginStatus.LOGIN_OK;
        }
    }

    @Transactional
    public User registrar(User user) {
        Optional<User> usuarioBD = userRepository.findByEmail(user.getEmail());
        if (usuarioBD.isPresent())
            throw new UserServiceException("El usuario " + user.getEmail() + " ya está registrado");
        else if (user.getEmail() == null)
            throw new UserServiceException("El usuario no tiene email");
        else if (user.getPassword() == null)
            throw new UserServiceException("El usuario no tiene password");
        else return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

}
