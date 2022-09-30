package guru.jpa.orderservice.dao;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import guru.jpa.orderservice.domain.OrderHeader;
import guru.jpa.orderservice.repositories.OrderHeaderRepository;

@Component
public class OrderHeaderDao {

    private final OrderHeaderRepository orderHeaderRepository;

    public OrderHeaderDao(OrderHeaderRepository orderHeaderRepository){
        this.orderHeaderRepository = orderHeaderRepository;
    }

    @Transactional
    public OrderHeader persist(OrderHeader orderHeader){
        OrderHeader orderHeader1 = orderHeaderRepository.save(orderHeader);
        return orderHeader;
    }

    public Optional<OrderHeader> getOrderHeaderById(Long id){

        Optional<OrderHeader> byId = this.orderHeaderRepository.findById(id);
        return byId;
    }

}
