package com.example.demo.controler;

import com.example.demo.models.Stock;
import com.example.demo.models.UserCreator;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.service.StockService;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/userStock")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final StockService stockService;
    private final UserService userService;

    public UserController(StockService stockService, UserService userService) {
        this.stockService = stockService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO user){
        return userService.login(user);
    }

    @PostMapping("/createUser")
    public UserCreator createUser(@RequestBody UserCreator user){
        return userService.createUser(user);
    }

    // TODO falta query
    @DeleteMapping("/deleteUser")
    public UserCreator deleteUser(@RequestBody UserCreator user){
        return userService.deleteUser(user);
    }
}
