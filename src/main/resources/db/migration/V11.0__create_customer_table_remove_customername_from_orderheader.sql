DROP TABLE IF EXISTS customer;

CREATE TABLE customer(
    id                  bigint not null auto_increment primary key,
    name                varchar(100),
    creation_timestamp  timestamp,
    updated_timestamp   timestamp,
    address             varchar(100),
    city                varchar(100),
    state               varchar(100),
    zip_code            varchar(100)
) engine=InnoDB;

ALTER TABLE order_header
    ADD COLUMN customer_id bigint,
    ADD CONSTRAINT order_header_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer(id);
