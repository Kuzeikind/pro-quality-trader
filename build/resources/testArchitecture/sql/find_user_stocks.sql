SELECT s.ticker AS ticker,
    uhs.amount AS amount
FROM trader.stocks AS s
    JOIN trader.user_has_stock AS uhs ON s.id = uhs.stock_id
WHERE uhs.user_id = ?