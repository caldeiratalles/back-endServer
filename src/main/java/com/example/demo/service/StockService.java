package com.example.demo.service;

import com.example.demo.models.Stock;
import com.example.demo.models.User;
import com.example.demo.repository.StockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> findAllPieces() {
        return stockRepository.findAllPieces();
    }

    public Optional<Stock> findByPiece(Stock stock, User user) {
        return stockRepository.findByPiece(stock,user);
    }

    public Optional<Stock> createDonation(Stock stock, User user) {
        Optional<Stock> stockReturn = stockRepository.createDonation(stock,user);
        return stockReturn;
    }

    @Transactional
    public ResponseEntity updatePiece(User user, Stock stock) {
        Integer status = stockRepository.updatePiece(stock,user);
        if(status<0){
            return new ResponseEntity<>("null", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }
}
