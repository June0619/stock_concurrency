package me.jwjung.stock.service;

import me.jwjung.stock.domain.Stock;
import me.jwjung.stock.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(final StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void decrease(Long id, Long quantity) {
        // Stock 조회
        // 재고 감소 후
        // 갱신된 값을 저장

        Stock stock = stockRepository.findById(id).orElseThrow();
    }
}
