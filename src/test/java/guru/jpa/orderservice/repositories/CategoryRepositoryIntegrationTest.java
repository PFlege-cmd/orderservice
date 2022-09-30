package guru.jpa.orderservice.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import guru.jpa.orderservice.domain.Category;
import guru.jpa.orderservice.domain.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("guru.jpa.orderservice.repositories")
public class CategoryRepositoryIntegrationTest {

    private Product product;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductEntityRepository productEntityRepository;

    @BeforeEach()
    void setUp(){
        Product newProduct = new Product();
        newProduct.setDescription("new product");
        product = productEntityRepository.save(newProduct);
    }

    @Test
    void testPersistCategoryAndProducts(){
        Category category = new Category();
        category.addProduct(product);
        product.addCategory(category);
        Category savedCategory = categoryRepository.save(category);

        assertThat(savedCategory.getProducts()).contains(product);
        assertThat(product.getCategories()).contains(savedCategory);
    }


}
