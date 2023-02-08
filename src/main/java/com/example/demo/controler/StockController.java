package com.example.demo.controler;

import com.example.demo.models.Stock;
import com.example.demo.models.StockSimple;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/allPiece")
    public List<StockSimple> findAllPieces(){
        return stockService.findAllPieces().getBody();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/editar")
    public void editarRequest(@RequestBody StockSimple stock){
        stockService.editarPeca(stock);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/byPiece/{id}")
    public StockSimple findByPiece(@PathVariable Integer id){
        return stockService.findByPiece(id).getBody();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/createDonation")
    public void createDonation(@RequestBody Stock stock) throws Exception {
        stockService.createDonation(stock).getBody();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/requestDonation")
    public void requestDonation(@RequestBody Stock stock) throws Exception {
        stockService.requestDonation(stock).getBody();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/delete/{id}")
    public void deletepeca(@PathVariable Integer id) throws Exception {
        stockService.deletePeca(id);
    }
}
