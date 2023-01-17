package com.example.demo.controler;

import com.example.demo.models.Stock;
import com.example.demo.models.User;
import com.example.demo.service.StockService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public User findUser(@RequestBody User user){
        userService.findUser(user);
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }

    @PostMapping
    public void requisitionDonation(@RequestBody User user, @RequestBody Stock stock){
        stockService.requisitionDonation(user,stock);
    }
}
