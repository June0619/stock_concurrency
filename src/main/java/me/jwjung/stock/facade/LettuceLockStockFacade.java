package me.jwjung.stock.facade;

import me.jwjung.stock.repository.RedisLockRepository;
import me.jwjung.stock.service.StockService;
import org.springframework.stereotype.Component;

@Component
public class LettuceLockStockFacade {

    private RedisLockRepository redisLockRepository;
    private StockService stockService;

    public LettuceLockStockFacade(final RedisLockRepository redisLockRepository,
        final StockService stockService) {
        this.redisLockRepository = redisLockRepository;
        this.stockService = stockService;
    }

    public void decrease(Long id, Long quantity) {
        while (!redisLockRepository.lock(id)) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        try {
            stockService.decrease(id, quantity);
        } finally {
            redisLockRepository.unlock(id);
        }
    }
}
