DROP TABLE if exists product;
CREATE TABLE product
(
    id VARCHAR (256) PRIMARY KEY ,
    name VARCHAR (256) ,
    price NUMERIC,
    measure_unit VARCHAR (256),
    provider VARCHAR (256),
    vat_type VARCHAR (100),
    category VARCHAR(256),
    initial_date DATE,
    day_of_week VARCHAR(20),
    num_of_periods INT,
    period VARCHAR(20),
    image CLOB
);