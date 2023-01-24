package com.example.demo.controler;

import com.example.demo.models.Stock;
import com.example.demo.models.UserCreator;
import com.example.demo.models.dto.StockDTO;
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

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/allPiece")
    public List<StockDTO> findAllPieces(){
        return stockService.findAllPieces();
    }

    @GetMapping("/byPiece/{id}")
    public StockDTO findByPiece(@PathVariable Integer id){
        return stockService.findByPiece(id);
    }

    @PostMapping("/createDonation")
    public Stock createDonation(@RequestBody Stock stock){
        return stockService.createDonation(stock);
    }

    @PostMapping("/requestDonation")
    public Stock requestDonation(@RequestBody Stock stock){
        return stockService.requestDonation(stock);
    }
}
