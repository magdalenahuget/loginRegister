package org.example.controller;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
public class UserController {

    private UserService userService = new UserService(new UserRepository());

    @GET
    @Path("/")
    public Response getUsers() {

        List<User> allUsers = userService.getAll();

        System.out.println(allUsers);
        return Response.ok(allUsers.toString()).build();
    }

    @POST
    @Consumes("application/json")
    @Path("/login")
    public Response loginUser(String requestBody) {
        JSONObject jsonObj = new JSONObject(requestBody);
        if (jsonObj.getString("login").isEmpty() || jsonObj.getString("password").isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("No login or no password provided.").build();
        }
        User user = new User();
        user.setLogin(jsonObj.getString("login"));
        user.setPassword(jsonObj.getString("password"));

        User existingUser = userService.getUserByLoginAndPassword(user.getLogin(), user.getPassword());
        if (existingUser != null) {
            return Response.status(Response.Status.OK).entity(existingUser).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid login credentials").build();
    }

    @POST
    @Consumes("application/json")
    @Path("/register")
    public Response registerUser(String requestBody) {
        JSONObject jsonObj = new JSONObject(requestBody);
        if (jsonObj.getString("login").isEmpty() || jsonObj.getString("password").isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("No login or no password provided.").build();
        }
        User user = new User();
        user.setLogin(jsonObj.getString("login"));
        user.setPassword(jsonObj.getString("password"));
        boolean isRegistered = userService.registerNewUser(user);
        if (isRegistered) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Registration failed.").build();
    }
}

