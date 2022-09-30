package guru.jpa.orderservice.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import guru.jpa.orderservice.domain.Address;
import guru.jpa.orderservice.domain.Customer;
import guru.jpa.orderservice.domain.OrderHeader;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("patrick")
public class CustomerRepositoryIntegrationTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    private OrderHeader orderHeader;

    @BeforeEach
    void setUp() {
        OrderHeader newOrderHeader = new OrderHeader();
        orderHeader = orderHeaderRepository.save(newOrderHeader);
    }

    @Test
    void testCustomerHasOneToManyOrderHeaders() {

        Customer customer = new Customer();
        customer.addOrderHeader(orderHeader);
        orderHeader.setCustomer(customer);
        Customer newCustomer = customerRepository.save(customer);

        assertThat(customer.getOrderHeaders()).contains(orderHeader);
        assertThat(orderHeader.getCustomer()).isEqualTo(newCustomer);
        assertThat(customer.getCreationTimestamp()).isNotNull();

    }


    @Test
    void testValidatesConstraints(){
        Customer customer = new Customer();
        customer.setName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");

        Address address = new Address();
        address.setAddress("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
        address.setCity("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
        address.setState("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
        address.setZipCode("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");

        customer.setAddress(address);
        customer.setEmail("me@mail.com");

        try {
            customerRepository.save(customer);
        } catch (Exception e){
            assertThat(e).isInstanceOf(ValidationException.class);
            var constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
            assertThat(constraintViolations).hasSize(5);
        }
    }
}
