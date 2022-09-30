package guru.jpa.orderservice;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.jpa.orderservice.dao.bootstrap.BootstrapOrderheaderService;
import guru.jpa.orderservice.domain.Customer;
import guru.jpa.orderservice.domain.OrderHeader;
import guru.jpa.orderservice.domain.OrderLine;
import guru.jpa.orderservice.domain.Product;
import guru.jpa.orderservice.repositories.CustomerRepository;
import guru.jpa.orderservice.repositories.OrderHeaderRepository;
import guru.jpa.orderservice.services.ProductService;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired BootstrapOrderheaderService bootstrapOrderheaderService;
    @Autowired ProductService productService;
    @Autowired CustomerRepository customerRepository;
    @Autowired OrderHeaderRepository orderHeaderRepository;


    @Override public void run(String... args) throws Exception {
        bootstrapOrderheaderService.readData();

        Product productEntity = new Product();

        Product savedProduct = productService.save(productEntity);
        productService.updateQuantityOnHand(25,savedProduct.getId());

        Customer customer0 = new Customer("Dummy customer");
        Customer customer1 = customerRepository.save(customer0);
        Customer customer2 = customerRepository.save(customer1);
        Customer customer3 = customerRepository.save(customer2);

        OrderHeader orderHeader = new OrderHeader();
        OrderLine orderLine1 = new OrderLine();

        orderHeader.setOrderLineSet(Set.of(orderLine1));

        OrderHeader savedOrderHeader = orderHeaderRepository.save(orderHeader);
        OrderHeader savedOrderHeader1 = orderHeaderRepository.save(savedOrderHeader);
        OrderHeader savedOrderHeader2 = orderHeaderRepository.save(savedOrderHeader1);
    }
}
