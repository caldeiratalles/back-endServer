package com.example.demo.controler;

import com.example.demo.models.Stock;
import com.example.demo.models.StockSimple;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/allPiece")
    public List<StockSimple> findAllPieces(@RequestBody UserDTO userDTO){
        return stockService.findAllPieces(userDTO).getBody();
    }

    @PutMapping("/editar/{id}")
    public void editarRequest(@RequestBody StockSimple stock, @PathVariable Integer id){
        stockService.editarPeca(stock,id);
    }

    @PostMapping("/createDonation")
    public void createDonation(@RequestBody Stock stock) throws Exception {
        stockService.createDonation(stock).getBody();
    }

    @PostMapping("/requestDonation")
    public void requestDonation(@RequestBody Stock stock) throws Exception {
        stockService.requestDonation(stock).getBody();
    }

    @DeleteMapping("/delete/{id}")
    public void deletepeca(@PathVariable Integer id) throws Exception {
        stockService.deletePeca(id);
    }
}
