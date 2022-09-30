package guru.jpa.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.jpa.orderservice.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
