package com.example.demo.controler;

import com.example.demo.models.Stock;
import com.example.demo.models.User;
import com.example.demo.service.StockService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "http://localhost:3000")
public class StockController {

    private final StockService stockService;
    private final UserService userService;

    public StockController(StockService stockService, UserService userService) {
        this.stockService = stockService;
        this.userService = userService;
    }

    @GetMapping()
    public List<Stock> findAllPieces(){
        return stockService.findAllPieces();
    }

    @GetMapping()
    public Optional<Stock> findByPiece(@RequestBody Stock stock, @RequestBody User user){
        return stockService.findByPiece(stock, user);
    }

    @PostMapping
    public Optional<Stock> createDonation(@RequestBody Stock stock, @RequestBody User user){
        return stockService.createDonation(stock,user);
    }
}
