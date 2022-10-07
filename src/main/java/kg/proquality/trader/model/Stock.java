package kg.proquality.trader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Table(name = "stocks", schema = "trader")
@EqualsAndHashCode(of = "id")
@ToString
@Getter
@Setter
@Accessors(chain = true)
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "sell_price")
    private Double sellPrice;

    @Column(name = "buy_price")
    private Double buyPrice;
}
