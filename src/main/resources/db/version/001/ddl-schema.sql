
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS stocks CASCADE;
DROP TABLE IF EXISTS user_has_stock CASCADE;

CREATE TABLE trader.users (
    id SERIAL NOT NULL PRIMARY KEY,
    email VARCHAR NOT NULL UNIQUE
);

CREATE TABLE trader.stocks (
    id SERIAL NOT NULL PRIMARY KEY,
    ticker VARCHAR NOT NULL UNIQUE,
    sell_price DOUBLE PRECISION NOT NULL,
    buy_price DOUBLE PRECISION NOT NULL
);

CREATE TABLE trader.user_has_stock (
    user_id INTEGER NOT NULL,
    stock_id INTEGER NOT NULL,
    amount INTEGER NOT NULL,
    CONSTRAINT user_has_stock_pk PRIMARY KEY (user_id, stock_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES trader.users(id),
    CONSTRAINT fk_stock_id FOREIGN KEY (stock_id) REFERENCES trader.stocks(id)
);

INSERT INTO trader.users (email) VALUES
('test.email1@mymail.com'),
('test.email2@mymail.com'),
('test.email3@mymail.com'),
('test.email4@mymail.com')
;

INSERT INTO trader.stocks (ticker, sell_price, buy_price) VALUES
('APPL', 20.9, 21.1),
('NTFLX', 5.1, 6.9),
('AMZN', 100.2, 120.5),
('GGL', 50.4, 60.4),
('FCBK', 5.0, 6.1)
;

INSERT INTO trader.user_has_stock VALUES
(1, 1, 2),
(1, 2, 4),
(2, 1, 5),
(2, 5, 1),
(3, 3, 2)
;

