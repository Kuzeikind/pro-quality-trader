INSERT INTO trader.user_has_stock (user_id, stock_id, amount)
VALUES
(?, ?, ?)
ON CONFLICT DO NOTHING