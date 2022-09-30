package guru.jpa.orderservice.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "billingAddress.address", column = @Column(
                name = "bill_to_address"
        )),
        @AttributeOverride(name = "billingAddress.city", column = @Column(
                name = "bill_to_city"
        )),
        @AttributeOverride(name = "billingAddress.state", column = @Column(
                name = "bill_to_state"
        )),
        @AttributeOverride(name = "billingAddress.zipCode", column = @Column(
                name = "bill_to_zip"
        )),
        @AttributeOverride(name = "shippingAddress.address", column = @Column(
                name = "shipping_address"
        )),
        @AttributeOverride(name = "shippingAddress.city", column = @Column(
                name = "shipping_city"
        )),
        @AttributeOverride(name = "shippingAddress.state", column = @Column(
                name = "shipping_state"
        )),
        @AttributeOverride(name = "shippingAddress.zipCode", column = @Column(
                name = "shipping_zip"
        )),
})
public class OrderHeader extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Embedded
    private Address billingAddress;

    @Embedded
    private Address shippingAddress;

    @OneToMany(mappedBy = "orderHeader",
            cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
            fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Set<OrderLine> orderLineSet;

    @ManyToOne
    private Customer customer;

    @OneToOne(cascade = {
            CascadeType.PERSIST
    }, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    OrderApproval orderApproval;

    @Version
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public void setOrderApproval(OrderApproval orderApproval) {
        this.orderApproval = orderApproval;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<OrderLine> getOrderLineSet() {
        return orderLineSet;
    }

    public void setOrderLineSet(Set<OrderLine> orderLineSet) {
        this.orderLineSet = orderLineSet;
    }

    public void addOrderLine(OrderLine orderLine){
        if (orderLineSet == null){
            this.orderLineSet = new HashSet<>();
        }

        this.orderLineSet.add(orderLine);
        orderLine.setOrderHeader(this);
    }



    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        OrderHeader that = (OrderHeader) o;
        return getOrderStatus() == that.getOrderStatus() && Objects.equals(getBillingAddress(), that.getBillingAddress())
                && Objects.equals(getShippingAddress(), that.getShippingAddress()) && Objects.equals(
                getOrderLineSet(), that.getOrderLineSet()) && Objects.equals(getCustomer(), that.getCustomer());
    }

    @Override public int hashCode() {
        return Objects.hash(super.hashCode(), getOrderStatus(), getBillingAddress(), getShippingAddress(), getCustomer());
    }
}
