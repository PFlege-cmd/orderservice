DROP TABLE IF EXISTS order_line;

CREATE TABLE order_line(
    id                  bigint not null auto_increment primary key,
    quantity_ordered    bigint,
    updated_timestamp   timestamp,
    created_timestamp   timestamp,
    order_header_id     bigint,
    constraint order_header_pk FOREIGN KEY (order_header_id) REFERENCES order_header(id)
) engine = InnoDB;
