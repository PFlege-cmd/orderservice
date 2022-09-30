package guru.jpa.orderservice.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Product extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    @ManyToMany
    @JoinTable(name="category_product", joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;
    private String description;
    private Integer quantityOnHand;

    public ProductStatus getProductStatus() {

        return productStatus;
    }

    public void setProductStatus(ProductStatus orderStatus) {

        this.productStatus = orderStatus;
    }

    public Set<Category> getCategories() {

        return categories;
    }

    public void setCategories(Set<Category> categories) {

        this.categories = categories;
    }

    public void addCategory(Category category){
        if (categories == null){
            categories = new HashSet<>();
        }

        categories.add(category);
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Integer getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(Integer quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Product that = (Product) o;
        return productStatus == that.productStatus;
    }

    @Override public int hashCode() {
        return Objects.hash(super.hashCode(), productStatus);
    }
}
