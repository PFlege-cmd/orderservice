package guru.jpa.orderservice.dao.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import guru.jpa.orderservice.domain.Customer;
import guru.jpa.orderservice.domain.OrderHeader;
import guru.jpa.orderservice.domain.OrderLine;
import guru.jpa.orderservice.repositories.CustomerRepository;
import guru.jpa.orderservice.repositories.OrderHeaderRepository;

@Service
public class BootstrapOrderheaderService {

    @Autowired OrderHeaderRepository orderHeaderRepository;

    @Transactional
    public void readData(){
        OrderHeader orderHeader = orderHeaderRepository.findById(41L).get();

        System.out.println("Commandlinerunner started");

        for (OrderLine orderLine : orderHeader.getOrderLineSet()) {
            System.out.println(orderLine);
            orderLine.getProduct().getCategories().forEach(cat -> System.out.println(cat.getDescription()));

        }
    }
}
