package com.example.demo.controler;

import com.example.demo.models.Stock;
import com.example.demo.models.User;
import com.example.demo.service.StockService;
import com.example.demo.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Stock> findStockAll(){
        List<Stock> stockAll = stockService.findAll();
        return stockAll;
    }

    @GetMapping()
    public Stock findStockByName(@RequestBody Stock stock, @RequestBody User user){
        Stock stockByPiece = stockService.findStockByName(stock, user);
        return stockByPiece;
    }

    @PostMapping
    public void createStockPiece(@RequestBody Stock stock, @RequestBody User user){
        stockService.createStockPiece(stock);
    }
}
