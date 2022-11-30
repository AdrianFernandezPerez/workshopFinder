package com.workshopFinder.workshopFinder.service.db;

import com.workshopFinder.workshopFinder.model.User;
import com.workshopFinder.workshopFinder.repository.UserRepository;
import com.workshopFinder.workshopFinder.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class UserServiceJpa implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User usuario) {
        userRepository.save(usuario);
    }

    @Override
    public void delete(Integer idUsuario) {
        userRepository.deleteById(Long.valueOf(idUsuario));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByNombre(String nombre) {
        return userRepository.findByNombre(nombre);
    }

}
