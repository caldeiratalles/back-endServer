package com.example.demo.controler;

import com.example.demo.models.UserCreator;
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
    public ResponseEntity<UserCreator> createUser(@RequestBody UserCreator user){
        return userService.createUser(user);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<UserCreator> deleteUser(@RequestBody UserCreator user){
        return userService.deleteUser(user);
    }
}
