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

    // TODO falta query
    @PostMapping("/login")
    public Optional<UserCreator> login(@RequestBody UserDTO user){
        return userService.login(user);
    }

    // TODO falta query
    @GetMapping("/findUser")
    public Optional<UserCreator> findUser(@RequestBody UserDTO user){
        return userService.findUser(user);
    }

    @PostMapping("/createUser")
    public UserCreator createUser(@RequestBody UserCreator user){
        return userService.createUser(user);
    }

    @PostMapping("/requisitionDonation")
    public Stock requisitionDonation(@RequestBody UserDTO user, @RequestBody Stock stock){
        return stockService.updatePiece(user,stock);
    }

    // TODO falta query
    @DeleteMapping("/deleteUser")
    public ResponseEntity deleteUser(@RequestBody UserCreator user){
        return userService.deleteUser(user);
    }
}
