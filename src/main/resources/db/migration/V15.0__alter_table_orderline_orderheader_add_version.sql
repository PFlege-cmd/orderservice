ALTER TABLE orderservice.order_line
    ADD COLUMN version INTEGER;

ALTER TABLE orderservice.order_header
    ADD COLUMN version INTEGER;
