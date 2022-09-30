INSERT INTO category_product(product_id, category_id)
    SELECT p.id, c.id FROM product p, category c
    WHERE p.id = 4
    AND c.id = 1;

INSERT INTO category_product(product_id, category_id)
    SELECT p.id, c.id FROM product p, category c
    WHERE p.id = 5
    AND c.id = 2;

INSERT INTO category_product(product_id, category_id)
    SELECT p.id, c.id FROM product p, category c
    WHERE p.id = 6
    AND c.id = 2;

INSERT INTO category_product(product_id, category_id)
    SELECT p.id, c.id FROM product p, category c
    WHERE p.id = 7
    AND c.id = 3;

