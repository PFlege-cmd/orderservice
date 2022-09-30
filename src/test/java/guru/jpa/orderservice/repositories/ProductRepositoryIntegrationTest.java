package guru.jpa.orderservice.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import guru.jpa.orderservice.domain.Product;
import guru.jpa.orderservice.services.ProductService;

@ComponentScan({"guru.jpa.orderservice.repositories", "guru.jpa.orderservice.services"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@ActiveProfiles("patrick")
public class ProductRepositoryIntegrationTest {

    @Autowired ProductEntityRepository productEntityRepository;
    @Autowired ProductService productService;

    @Test
    void testPersistAndFindProductEntity(){
        Product productEntity = new Product();

        productEntityRepository.save(productEntity);

        assertThat(productEntity.getId()).isNotNull();
    }

    @Test
    void testChangeQuantityOnHand(){
        Product productEntity = new Product();

        Product savedProduct = productService.save(productEntity);
        productService.updateQuantityOnHand(25,savedProduct.getId());

        assertThat(savedProduct.getQuantityOnHand()).isEqualTo(25);
    }
}
