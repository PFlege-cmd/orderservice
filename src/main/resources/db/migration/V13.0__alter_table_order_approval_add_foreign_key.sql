ALTER TABLE order_approval
    ADD COLUMN order_header_id bigint;

ALTER TABLE order_approval
    ADD CONSTRAINT order_header_by_order_approval_pk FOREIGN KEY (order_header_id) references order_header(id);
