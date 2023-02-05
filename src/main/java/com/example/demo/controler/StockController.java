package com.example.demo.controler;

import com.example.demo.models.Stock;
import com.example.demo.models.dto.CategoriaItemDTO;
import com.example.demo.models.dto.CategoriasItemDTO;
import com.example.demo.models.dto.StockDTO;
import com.example.demo.service.StockService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<StockDTO>> findAllPieces(){
        return stockService.findAllPieces();
    }

    @GetMapping("/byPiece/{id}")
    public ResponseEntity<StockDTO> findByPiece(@PathVariable Integer id){
        return stockService.findByPiece(id);
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<CategoriasItemDTO>> findCategoria(){
        return stockService.categoriaItem();
    }

    @GetMapping("/byPieceCategoria/{id}")
    public ResponseEntity<List<CategoriaItemDTO>> findCategoriabyPiece(@PathVariable Integer id){
        return stockService.findCategoriabyPiece(id);
    }

    @PostMapping("/createDonation")
    public ResponseEntity<Stock> createDonation(@RequestBody Stock stock){
        return stockService.createDonation(stock);
    }

    @PostMapping("/requestDonation")
    public ResponseEntity<Stock> requestDonation(@RequestBody Stock stock){
         return stockService.requestDonation(stock);
    }

    @DeleteMapping("/delete/{id}")
    public void deletepeca(@PathVariable Integer id){
        stockService.deletePeca(id);
    }
}
