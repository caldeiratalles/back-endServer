package com.example.demo.controler;

import com.example.demo.models.UserCreator;
import com.example.demo.models.dto.UserChangeSenha;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.service.StockService;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userStock")
public class UserController {

    private final StockService stockService;
    private final UserService userService;

    public UserController(StockService stockService, UserService userService) {
        this.stockService = stockService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO user){
        return userService.login(user);
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody UserCreator user){
        userService.createUser(user);
    }

    @PutMapping("/trocarSenha")
    public void changeSenha(@RequestBody UserChangeSenha user){
        userService.changeSenha(user);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestBody UserCreator user){
        userService.deleteUser(user);
    }
}
