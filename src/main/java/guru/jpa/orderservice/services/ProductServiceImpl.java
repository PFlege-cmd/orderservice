package guru.jpa.orderservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.jpa.orderservice.domain.Product;
import guru.jpa.orderservice.repositories.ProductEntityRepository;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired private final ProductEntityRepository productEntityRepository;

    ProductServiceImpl(ProductEntityRepository productEntityRepository){
        this.productEntityRepository = productEntityRepository;
    }

    @Override
    public Product save(Product product) {
        return productEntityRepository.saveAndFlush(product);
    }

    @Transactional
    @Override
    public Product updateQuantityOnHand(Integer quantityOnHand, Long id) {
        Product product = productEntityRepository.findById(id).orElseThrow();
        product.setQuantityOnHand(quantityOnHand);
        return productEntityRepository.saveAndFlush(product);
    }
}
