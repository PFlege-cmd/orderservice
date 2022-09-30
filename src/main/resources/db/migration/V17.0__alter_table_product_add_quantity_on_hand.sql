ALTER TABLE orderservice.product
    ADD COLUMN quantity_on_hand INTEGER;

UPDATE orderservice.product
    SET quantity_on_hand = 10;
