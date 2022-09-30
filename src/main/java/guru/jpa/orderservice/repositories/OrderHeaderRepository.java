package guru.jpa.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.jpa.orderservice.domain.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

}
