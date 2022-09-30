ALTER TABLE order_header
    ADD COLUMN order_approval_id bigint,
    ADD CONSTRAINT order_approval_fk FOREIGN KEY (order_approval_id) references order_approval(id);
