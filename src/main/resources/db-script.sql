CREATE TABLE customer(
                         id VARCHAR(36) PRIMARY KEY,
                         name VARCHAR(200) NOT NULL,
                         address VARCHAR(300) NOT NULL
);

INSERT INTO customer (id,name,address)
VALUES (UUID(),'Chalana','Kalawewa'),
       (UUID(),'Naween','Alawwa'),
       (UUID(),'Mahesh','SriPura'),
       (UUID(),'Suresh','Embilipitiya'),
       (UUID(),'Prasanna','SriPura'),
       (UUID(),'Amith','Kalawewa');

/*Create the Item Table*/

CREATE TABLE item( stock INT(10) NOT NULL,
                   unit_price DECIMAL(10) NOT NULL,
                   description VARCHAR(200) NOT NULL,
                   code VARCHAR(50) PRIMARY KEY

);
INSERT INTO item(stock, unit_price, description, code)
VALUES ('1','20000.50','testing1', UUID()),
       ('2','30000.50','testing2', UUID()),
       ('3','35000.50','testing3', UUID()),
       ('4','40000.50','testing4', UUID()),
       ('5','67000.50','testing5', UUID()),
       ('6','98000.50','testing6', UUID());
