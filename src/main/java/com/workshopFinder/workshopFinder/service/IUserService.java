package com.workshopFinder.workshopFinder.service;

import com.workshopFinder.workshopFinder.model.User;

import java.util.List;

public interface IUserService {

    void save(User user);

    void delete(Integer idUser);

    List<User> findAll();

}


