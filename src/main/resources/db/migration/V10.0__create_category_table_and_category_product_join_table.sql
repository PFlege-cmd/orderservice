CREATE TABLE category (
    id                 bigint NOT NULL auto_increment PRIMARY KEY,
    description        VARCHAR(200),
    creation_timestamp TIMESTAMP,
    updated_timestamp  TIMESTAMP
);

CREATE TABLE category_product(
    product_id      BIGINT NOT NULL,
    category_id     BIGINT NOT NULL,
    PRIMARY KEY(product_id, category_id),
    constraint category_product_product_id FOREIGN KEY (product_id) REFERENCES product(id),
    constraint category_product_category_id FOREIGN KEY (category_id) REFERENCES category(id)
);

INSERT INTO category(description,creation_timestamp,updated_timestamp) VALUES ("CATEGORY1", now(), now());
INSERT INTO category(description,creation_timestamp,updated_timestamp) VALUES ("CATEGORY2", now(), now());
INSERT INTO category(description,creation_timestamp,updated_timestamp) VALUES ("CATEGORY3", now(), now());

INSERT INTO product(description, product_status, creation_timestamp, updated_timestamp) VALUES ("PROD1", "NEW", now(), now());
INSERT INTO product(description, product_status, creation_timestamp, updated_timestamp) VALUES ("PROD2", "NEW", now(), now());
INSERT INTO product(description, product_status, creation_timestamp, updated_timestamp) VALUES ("PROD3", "NEW", now(), now());
INSERT INTO product(description, product_status, creation_timestamp, updated_timestamp) VALUES ("PROD4", "NEW", now(), now());
