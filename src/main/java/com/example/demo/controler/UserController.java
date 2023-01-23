package com.example.demo.controler;

import com.example.demo.models.Stock;
import com.example.demo.models.User;
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
    public Optional<User> login(@RequestBody User user){
        return userService.login(user);
    }

    @GetMapping("/findUser")
    public Optional<User> findUser(@RequestBody User user){
        return userService.findUser(user);
    }

    @PostMapping("/createUser")
    public Optional<User> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/donation")
    public ResponseEntity requisitionDonation(@RequestBody User user, @RequestBody Stock stock){
        return stockService.updatePiece(user,stock);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity deleteUser(@RequestBody User user){
        return userService.deleteUser(user);
    }
}
