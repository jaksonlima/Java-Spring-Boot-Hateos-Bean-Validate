DROP TABLE IF EXISTS product;

CREATE TABLE product (id INT AUTO_INCREMENT  PRIMARY KEY,
                     name VARCHAR(250) NOT NULL,
                     price VARCHAR(250) NOT NULL);

INSERT INTO product (name, price) VALUES ('Xaiomi', '2900');

