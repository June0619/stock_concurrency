package me.jwjung.stock.service;

import me.jwjung.stock.domain.Stock;
import me.jwjung.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(final StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    //Transactional 애노테이션은 proxy 객체로 만들기 떄문에 synchronized 를 사용할 수 없음
//    @Transactional
    public synchronized void decrease(Long id, Long quantity) {
        // 1. Stock 조회
        // 2. 재고 감소 후
        // 3. 갱신된 값을 저장
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }

}
