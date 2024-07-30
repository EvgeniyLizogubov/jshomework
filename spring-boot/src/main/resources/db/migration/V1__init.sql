CREATE TABLE IF NOT EXISTS product
(
    id    BIGSERIAL PRIMARY KEY,
    name  VARCHAR(255),
    price INT
);

INSERT INTO product(name, price)
VALUES ('Молоко', 450),
       ('Хлеб', 350),
       ('Колбаса', 2600),
       ('Сыр', 1700),
       ('Пельмешки', 2350);