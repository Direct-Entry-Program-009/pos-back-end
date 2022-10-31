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

