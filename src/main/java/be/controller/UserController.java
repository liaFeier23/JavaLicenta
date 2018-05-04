package be.controller;

import be.dto.AutentificareDTO;
import be.dto.PasswordDTO;
import be.dto.UserDTO;
import be.model.User;
import be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {


    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserDTO registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public UserDTO updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public UserDTO updateUser(@RequestBody PasswordDTO password) {
        return userService.changePassword(password);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserDTO loginUser(@RequestBody AutentificareDTO user) {
        return userService.loginUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDTO getUser(@PathVariable("id") int idUser) {
        return userService.findById(idUser);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
