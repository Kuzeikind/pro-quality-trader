package kg.proquality.trader.repository;

import java.util.Optional;
import kg.proquality.trader.model.Stock;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    Optional<Stock> findByTicker(String ticker);

}
