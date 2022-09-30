package guru.jpa.orderservice.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import guru.jpa.orderservice.dao.OrderHeaderDao;
import guru.jpa.orderservice.domain.Customer;
import guru.jpa.orderservice.domain.OrderApproval;
import guru.jpa.orderservice.domain.OrderHeader;
import guru.jpa.orderservice.domain.OrderLine;
import guru.jpa.orderservice.domain.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("patrick")
@ComponentScan({ "guru.jpa.orderservice.dao", "guru.jpa.orderservice.repositories" })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderHeaderDaoIntegrationTest {

    @Autowired OrderHeaderDao orderHeaderDao;
    @Autowired OrderHeaderRepository orderHeaderRepository;
    @Autowired ProductEntityRepository productEntityRepository;
    @Autowired CustomerRepository customerRepository;
    Long persistedId;
    Product product;

    @BeforeEach
    void setUp(){
        Product newProduct = new Product();
        newProduct.setDescription("Test product");
        product = productEntityRepository.save(newProduct);
    }


    @Test
    @Order(1)
    @Rollback(value = false)
    void testPersistOrder(){
        OrderHeader orderHeader = new OrderHeader();
        OrderLine orderLine = new OrderLine();
        Customer customer = new Customer();


        orderHeader.addOrderLine(orderLine);
        orderLine.setOrderHeader(orderHeader);
        orderLine.setProduct(product);
        customer.addOrderHeader(orderHeader);
        orderHeader.setCustomer(customer);
        OrderApproval orderApproval = new OrderApproval();
        orderApproval.setApprovedBy("Me");

        orderHeader.setOrderApproval(orderApproval);

        OrderHeader savedOrderHeader = orderHeaderDao.persist(orderHeader);
        Customer newCustomer = customerRepository.save(customer);

        assertThat(savedOrderHeader.getId()).isNotNull();
        assertThat(savedOrderHeader.getOrderLineSet()).contains(orderLine);
        assertThat(savedOrderHeader.getCreationTimestamp()).isNotNull();
        assertThat(savedOrderHeader.getCustomer()).isNotNull();
        assertThat(savedOrderHeader.getOrderApproval()).isNotNull();
        persistedId = savedOrderHeader.getId();

    }

    @Test
    @Order(2)
    void testFindPersisted(){
        Optional<OrderHeader> orderHeaderById = orderHeaderDao.getOrderHeaderById(persistedId);
        assertThat(orderHeaderById).isNotEmpty();
        assertThat(orderHeaderById.get().getCreationTimestamp()).isNotNull();
    }

    @Test
    void testDelete(){
        Customer customer = new Customer();
        OrderHeader orderHeader = new OrderHeader();
        OrderLine orderLine = new OrderLine();
        OrderApproval orderApproval = new OrderApproval();

        orderHeader.addOrderLine(orderLine);
        orderHeader.setOrderApproval(orderApproval);
        orderHeader.setCustomer(customerRepository.save(customer));
        OrderHeader savedOrder = orderHeaderRepository.saveAndFlush(orderHeader);

        SoftAssertions.assertSoftly(softAssertions -> {
                    softAssertions.assertThat(savedOrder.getId()).isNotNull();
                    softAssertions.assertThat(orderLine.getId()).isNotNull();
                    softAssertions.assertThat(orderApproval.getId()).isNotNull();


                });

        orderHeaderRepository.deleteById(savedOrder.getId());
        orderHeaderRepository.flush();
        var nullReturned = orderHeaderRepository.getById(savedOrder.getId());
        nullReturned.getId();
    }
}
