package com.example.demo.controler;

import com.example.demo.models.Stock;
import com.example.demo.models.dto.CategoriaItemDTO;
import com.example.demo.models.dto.CategoriasItemDTO;
import com.example.demo.models.dto.StockDTO;
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
    public List<Stock>  findAllPieces(@RequestBody UserDTO userDTO){
        return stockService.findAllPieces(userDTO).getBody();
    }

    @GetMapping("/byPiece/{id}")
    public StockDTO findByPiece(@PathVariable Integer id,@RequestBody UserDTO userDTO){
        return stockService.findByPiece(id,userDTO).getBody();
    }

    @GetMapping("/categoria")
    public List<CategoriasItemDTO> findCategoria(){
        return stockService.categoriaItem().getBody();
    }

    @GetMapping("/byPieceCategoria/{id}")
    public List<CategoriaItemDTO> findCategoriabyPiece(@PathVariable Integer id){
        return stockService.findCategoriabyPiece(id).getBody();
    }

    @PostMapping("/createDonation")
    public Stock createDonation(@RequestBody Stock stock) throws Exception {
        return stockService.createDonation(stock).getBody();
    }

    @PostMapping("/requestDonation")
    public Stock requestDonation(@RequestBody Stock stock) throws Exception {
         return stockService.requestDonation(stock).getBody();
    }

    @PutMapping("/editar/{id}")
    public void editarRequest(@RequestBody Stock stock, @PathVariable Integer id){
        stockService.editarPeca(stock,id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletepeca(@PathVariable Integer id) throws Exception {
        stockService.deletePeca(id);
    }
}
