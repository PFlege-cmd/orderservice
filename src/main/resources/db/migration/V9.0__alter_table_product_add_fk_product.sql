ALTER TABLE order_line
ADD COLUMN product_id bigint;

ALTER TABLE order_line
ADD CONSTRAINT order_line_product_id_pk FOREIGN KEY (product_id) REFERENCES product(id);
