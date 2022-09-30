package guru.jpa.orderservice.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class OrderLine extends BaseEntity{

    @ManyToOne
    OrderHeader orderHeader;

    @ManyToOne
    Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    Integer quantityOrdered;

    @Version
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        OrderLine orderLine = (OrderLine) o;
        return Objects.equals(getOrderHeader(), orderLine.getOrderHeader()) && Objects.equals(getProduct(),
                orderLine.getProduct()) && Objects.equals(getQuantityOrdered(), orderLine.getQuantityOrdered());
    }

    @Override public int hashCode() {
        return Objects.hash(super.hashCode(), getOrderHeader(), getProduct(), getQuantityOrdered());
    }
}
