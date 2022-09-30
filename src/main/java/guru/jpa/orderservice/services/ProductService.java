package guru.jpa.orderservice.services;

import guru.jpa.orderservice.domain.Product;

public interface ProductService {

    Product save(Product product);

    Product updateQuantityOnHand(Integer quantityOnHand, Long id);
}
