package guru.jpa.orderservice.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

@Entity
public class Category extends BaseEntity{

    @ManyToMany
    @JoinTable(name="category_product",
            joinColumns =
            @JoinColumn(name = "category_id"),
            inverseJoinColumns =
            @JoinColumn(name = "product_id"))
    Set<Product> products;


    private String description;

    public Set<Product> getProducts() {
        return products;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Category category = (Category) o;
        return Objects.equals(getDescription(), category.getDescription());
    }

    @Override public int hashCode() {
        return Objects.hash(super.hashCode(), getDescription());
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        if (products ==null){
            products = new HashSet<>();
        }

        products.add(product);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
