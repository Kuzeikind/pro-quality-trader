
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS stocks CASCADE;
DROP TABLE IF EXISTS user_has_stock CASCADE;

CREATE TABLE users (
    id SERIAL NOT NULL PRIMARY KEY,
    email VARCHAR NOT NULL UNIQUE
);

CREATE TABLE stocks (
    id SERIAL NOT NULL PRIMARY KEY,
    ticker VARCHAR NOT NULL UNIQUE,
    sell_price DOUBLE PRECISION NOT NULL,
    buy_price DOUBLE PRECISION NOT NULL
);

CREATE TABLE user_has_stock (
    user_id INTEGER NOT NULL,
    stock_id INTEGER NOT NULL,
    amount INTEGER NOT NULL,
    CONSTRAINT user_has_stock_pk PRIMARY KEY (user_id, stock_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_stock_id FOREIGN KEY (stock_id) REFERENCES stocks(id)
);

INSERT INTO users VALUES
(1, 'test.email1@mymail.com'),
(2, 'test.email2@mymail.com'),
(3, 'test.email3@mymail.com'),
(4, 'test.email4@mymail.com')
;

INSERT INTO stocks VALUES
(1, 'APPL', 20.9, 21.1),
(2, 'NTFLX', 5.1, 6.9),
(3, 'AMZN', 100.2, 120.5),
(4, 'GGL', 50.4, 60.4),
(5, 'FCBK', 5.0, 6.1)
;

INSERT INTO user_has_stock VALUES
(1, 1, 2),
(1, 2, 4),
(2, 1, 5),
(2, 5, 1),
(3, 3, 2)
;

