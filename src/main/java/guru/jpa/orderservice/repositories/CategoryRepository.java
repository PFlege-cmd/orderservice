package guru.jpa.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.jpa.orderservice.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByDescription(String description);
}
