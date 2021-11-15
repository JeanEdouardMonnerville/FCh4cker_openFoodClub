DROP TABLE if exists CUSTOMER;
Create table Customer (
    id VARCHAR(256) PRIMARY KEY,
    name VARCHAR(255),
    secondName VARCHAR(255),
    email VARCHAR(255)
   -- password VARCHAR(70) NOT NULL,
    --enabled TINYINT NOT NULL DEFAULT 1
);

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

DROP TABLE if exists Subscription;
Create table Subscription (
    id Integer(255) AUTO_INCREMENT,
    quantity Number(255),
    sub_date DATE,
    product Varchar(255),
    customer Varchar(256),
    CONSTRAINT fk_sub_constumer FOREIGN KEY (customer) REFERENCES customer (id), 
    CONSTRAINT fk_prod_constumer FOREIGN KEY (product) REFERENCES product (id)
);

DROP TABLE if exists t_Order;
Create table t_Order (
    id VARCHAR(255) PRIMARY KEY,
    product Varchar(255),
    customer Varchar(256),
    creation_date TIMESTAMP,
    quantity integer(255),
    open boolean,
    Constraint fk_customer_order FOREIGN KEY (customer) REFERENCES customer (id),
    Constraint fk_product_order FOREIGN KEY (product) REFERENCES product (id)
)