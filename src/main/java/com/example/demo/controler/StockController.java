package com.example.demo.controler;

import com.example.demo.models.Stock;
import com.example.demo.models.UserCreator;
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

    // TODO falta query
    @GetMapping("/allPiece")
    public List<Stock> findAllPieces(){
        return stockService.findAllPieces();
    }

    // TODO falta query
    @GetMapping("/byPiece")
    public Stock findByPiece(@RequestBody Stock stock, @RequestBody UserCreator user){
        return stockService.findByPiece(stock, user);
    }

    @PostMapping("/createDonation")
    public Stock createDonation(@RequestBody Stock stock, @RequestBody UserCreator user){
        return stockService.createDonation(stock,user);
    }
}
