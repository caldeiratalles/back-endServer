package com.example.demo.service;

import com.example.demo.models.Stock;
import com.example.demo.models.UserCreator;
import com.example.demo.models.dto.CategoriaItemDTO;
import com.example.demo.models.dto.CategoriasItemDTO;
import com.example.demo.models.dto.StockDTO;
import com.example.demo.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public ResponseEntity<List<StockDTO>> findAllPieces() {
        return new ResponseEntity<>(stockRepository.findAllPieces(), HttpStatus.OK);
    }

    public ResponseEntity<StockDTO> findByPiece(Integer id) {
        return new ResponseEntity<>(stockRepository.findByPiece(id), HttpStatus.OK);
    }

    public ResponseEntity<Stock> createDonation(Stock stock) {
        LOGGER.info(stock.toString());
        if(stockRepository.createDonation(stock) == 0){
            LOGGER.info("Impossivel fazer doação");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    public ResponseEntity<Stock> requestDonation(Stock stock) {
        LOGGER.info(stock.toString());
        if(stockRepository.requestDonation(stock) == 0){
            LOGGER.info("Impossivel fazer requisição da doação");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    public ResponseEntity<List<CategoriasItemDTO>> categoriaItem() {
        return new ResponseEntity<>(stockRepository.findCategorias(), HttpStatus.OK);
    }

    public ResponseEntity<List<CategoriaItemDTO>> findCategoriabyPiece(Integer id) {
        return new ResponseEntity<>(stockRepository.findCategoriabyPiece(id), HttpStatus.OK);
    }

    public ResponseEntity<Integer> deletePeca(Integer id) {
        if(stockRepository.deletePeca(id) == 0){
            LOGGER.info("Impossivel fazer deleção da peça");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
