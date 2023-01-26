package com.example.demo.controler;

import com.example.demo.models.Stock;
import com.example.demo.models.dto.CategoriaItemDTO;
import com.example.demo.models.dto.CategoriasItemDTO;
import com.example.demo.models.dto.StockDTO;
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
    public List<StockDTO> findAllPieces(){
        return stockService.findAllPieces();
    }

    @GetMapping("/byPiece/{id}")
    public StockDTO findByPiece(@PathVariable Integer id){
        return stockService.findByPiece(id);
    }

    @GetMapping("/categoria")
    public List<CategoriasItemDTO> findCategoria(){
        return stockService.categoriaItem();
    }

    @GetMapping("/byPieceCategoria/{id}")
    public List<CategoriaItemDTO> findCategoriabyPiece(@PathVariable Integer id){
        return stockService.findCategoriabyPiece(id);
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
