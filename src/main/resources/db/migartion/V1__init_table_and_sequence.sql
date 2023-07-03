CREATE SEQUENCE IF NOT EXISTS order_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS Order
(
    id int NOT NULL UNIQUE PRIMARY KEY,
    status varchar2(255) NOT NULL,
    name_of_order varchar2(255),
    order_price int4,
    date_of_order date
);