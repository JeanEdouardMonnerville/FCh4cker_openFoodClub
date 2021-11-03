DROP TABLE CUSTOMER;
Create table Customer (
    id VARCHAR(256) PRIMARY KEY,
    name VARCHAR(255),
    secondName VARCHAR(255),
    email VARCHAR(255)
   -- password VARCHAR(70) NOT NULL,
    --enabled TINYINT NOT NULL DEFAULT 1
);

DROP TABLE Subscription;
Create table Subscription (
    quantity Number(255),
    sub_date DATE,
    product Varchar(255),
    customer Varchar(256),
    CONSTRAINT fk_sub_constumer FOREIGN KEY (customer) REFERENCES customer (id) 
);