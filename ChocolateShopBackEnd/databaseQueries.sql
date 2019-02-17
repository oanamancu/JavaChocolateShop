CREATE TABLE category(
    id IDENTITY,
    name VARCHAR(50),
    is_active BOOLEAN,
    
    CONSTRAINT pk_category_id PRIMARY KEY (id)
);

INSERT INTO category(name, is_active) VALUES ('Tablets', true);
INSERT INTO category(name, is_active) VALUES ('Pralines', true);
INSERT INTO category(name, is_active) VALUES ('Gift Boxes', true);
INSERT INTO category(name, is_active) VALUES ('Christmas', true);
INSERT INTO category(name, is_active) VALUES ('Easter', false);
INSERT INTO category(name, is_active) VALUES ('Valentine''s Day', false);

CREATE TABLE user_detail(
     id IDENTITY,
     first_name VARCHAR(50),
     last_name VARCHAR(50),
     role VARCHAR(50),
     enabled BOOLEAN,
     password VARCHAR(60),
     email VARCHAR(100),
     contact_number VARCHAR(15),
     CONSTRAINT pk_user_id PRIMARY KEY(id)
);

INSERT INTO user_detail
(first_name, last_name, role, enabled, password, email, contact_number)
VALUES('Oana', 'Mancu', 'ADMIN', true, '$2a$10$hFuj6c8wRxK/PWLGuot4S.N.qON/A2MqU1KRILrS4iEk0dFjKIy/i', 'mo@gmail.com', '0000000000');

INSERT INTO user_detail
(first_name, last_name, role, enabled, password, email, contact_number)
VALUES('One', 'First', 'USER', true, '$2a$10$SB6xApRC3mhUpf3fTUWPhOuo3dxZu45FiMmVjJBsH4lQ3JGxNfTxC', 'of1@gmail.com','1111111111');

INSERT INTO user_detail
(first_name, last_name, role, enabled, password, email, contact_number)
VALUES('Two', 'Second', 'SUPPLIER', true, '	$2a$10$l/kVWBPS5VDQ3jxBCmdBYOAm4IHVk2EBB0jA.v6a1oPpP/c/wkba.', 'ts2@gmail.com', '2222222222');


CREATE TABLE product(
    id IDENTITY,
    code VARCHAR(20),
    name VARCHAR(50),
    image VARCHAR(50),
    description VARCHAR(255),
    unit_price DECIMAL(10,2),
    weight DECIMAL(10,2),
    dimensions VARCHAR(50),
    ingredients VARCHAR(255),
    is_active BOOLEAN,
    category_id INT,
    purchases INT DEFAULT 0,
    views INT DEFAULT 0,
    CONSTRAINT pk_product_id PRIMARY KEY (id),
    CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category(id),
);

INSERT INTO product (code, name, image, description, unit_price, weight, dimensions, ingredients, is_active, category_id, purchases, views)
VALUES ('PRDABC123DEFX', 'Dark Chocolate Tablet', 'tablet1.jpg', null, 7, 150, '17 x 10 x 1 cm', 
        'cocoa solids (cocoa mass, cocoa butter), sugar, emulsifier (soya lecithin), natural flavour (vanilla)',
         true, 1, 0, 0 );

INSERT INTO product (code, name, image, description, unit_price, weight, dimensions, ingredients, is_active, category_id, purchases, views)
VALUES ('PRDDEF123DEFX', 'Hazelnuts Chocolate Tablet', 'tablet4.png', null, 11, 150, '17 x 10 x 1 cm', 
        'Milk Chocolate (sugar, milk, cocoa butter, unsweetened chocolate, soy lecithin [an emulsifier], vanilla extract), hazelnuts',
         true, 1, 0, 0 );

INSERT INTO product (code, name, image, description, unit_price, weight, dimensions,ingredients, is_active, category_id, purchases, views)
VALUES ('PRDPQR123WGTX', 'Milk Chocolate Tablet', 'tablet2.png', null, 7, 150, '17 x 10 x 1 cm',
        'Milk Chocolate (sugar, milk, cocoa butter, unsweetened chocolate, soy lecithin [an emulsifier], vanilla extract)',
         true, 1, 0, 0 );

INSERT INTO product (code, name, image, description, unit_price, weight, dimensions, ingredients, is_active, category_id, purchases, views)
VALUES ('PRDMNO123PQRX', 'Mint Chocolate Tablet', 'tablet3.png', null, 10, 150, '17 x 10 x 1 cm',
        'cocoa solids (cocoa mass, cocoa butter), sugar, emulsifier (soya lecithin), natural flavour (vanilla).,mint',
        true, 1, 0, 0 );

INSERT INTO product (code, name, image, description, unit_price, weight, dimensions,ingredients, is_active, category_id, purchases, views)
VALUES ('PRDABCXYZDEFX', 'Chocolate Wood Box', 'pralines12.jpg', 
       'Not just tasty, also practical. You can refill it with chocolate as many times you like. Or, why not, use it as a jewellery or a tea bag box after the chocolate is over. ', 
        54, 0 , '30 x 30 x 7 cm', null, true, 3, 0, 0 );