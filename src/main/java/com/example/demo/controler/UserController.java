package com.example.demo.controler;

import com.example.demo.models.UserCreator;
import com.example.demo.models.dto.UserChangeSenha;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.service.StockService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userStock")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO user) throws Exception {
        return userService.login(user).getBody();
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody UserCreator user) throws Exception {
        userService.createUser(user);
    }

    @PutMapping("/changePassword")
    public void changeSenha(@RequestBody UserChangeSenha user) throws Exception {
        userService.changeSenha(user);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestBody UserDTO user) throws Exception {
        userService.deleteUser(user);
    }
}
