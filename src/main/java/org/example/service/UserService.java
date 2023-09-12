package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;

import java.util.List;

public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerNewUser(User userToRegister) {
        boolean isRegistered = userRepository.insertUser(userToRegister);
        return isRegistered;
    }

    public User loginUser(String login, String password) {
        User userLogged = userRepository.getUserByUserLoginAndPassword(login, password);
        System.out.println("yes " + userLogged);
        return userLogged;
    }

    public List<User> getAll() {
        List<User> allUsers = userRepository.readAll();
        return allUsers;
    }

    public User getUserByLoginAndPassword(String login, String password) {
        User user = userRepository.getUserByUserLoginAndPassword(login, password);
        return user;
    }
}
