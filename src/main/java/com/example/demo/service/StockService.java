package com.example.demo.service;

import com.example.demo.models.Stock;
import com.example.demo.models.UserCreator;
import com.example.demo.models.dto.UserDTO;
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

    public Stock findByPiece(Stock stock, UserCreator user) {
        return stockRepository.findByPiece(stock,user);
    }

    public Stock createDonation(Stock stock, UserCreator user) {
        return stockRepository.createDonation(stock,user);
    }

    @Transactional
    public Stock updatePiece(UserDTO user, Stock stock) {
        if(stockRepository.requestDonation(stock,user) == 0){
            return null;
        }
        return stock;
    }
}
