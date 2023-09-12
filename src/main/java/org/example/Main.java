package org.example;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;

public class Main {
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);

        User kot = new User();
        kot.setLogin("kot@pl");
        kot.setPassword("kot123");

        User shrek = new User();
        shrek.setLogin("shrek@gmail");
        shrek.setPassword("shrek123");

        User pluto = new User();
        pluto.setLogin("pluto@gmail");
        pluto.setPassword("pluto123");


        boolean registrationKotResult = userService.registerNewUser(kot);
        boolean registrationShrekResult = userService.registerNewUser(shrek);
        boolean registrationPlutoResult = userService.registerNewUser(pluto);
        if(registrationKotResult && registrationShrekResult && registrationPlutoResult){
            System.out.println("Success.");
        }

        User userLogged = userService.loginUser("pluto@cfghfghjgmail", "pluto123");
        if (userLogged == null) {
            System.out.println("No user found.");
        }
    }
}