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
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "address.address",
                column = @Column(name = "address"
        )),
        @AttributeOverride(name = "address.city",
                column = @Column(name = "city"
        )),
        @AttributeOverride(name = "address.state",
                column = @Column(name = "state"
        )),
        @AttributeOverride(name = "address.zipCode",
                column = @Column(name = "zip_code"
        ))
})
public class Customer extends BaseEntity{

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    Set<OrderHeader> orderHeaders;

    @Valid
    @Embedded
    Address address;

    @Version
    private Integer version;

    @Size(max = 100)
    private String name;

    @Email
    private String email;



    public Customer(String name) {
        this();
        this.name = name;
    }

    public Customer(){
    }

    public Set<OrderHeader> getOrderHeaders() {
        return orderHeaders;
    }

    public void setOrderHeaders(Set<OrderHeader> orderHeaders) {
        this.orderHeaders = orderHeaders;
    }

    public void addOrderHeader(OrderHeader orderHeader){
        if (orderHeaders == null){
            orderHeaders = new HashSet<>();
        }

        orderHeaders.add(orderHeader);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Customer customer = (Customer) o;
        return Objects.equals(getOrderHeaders(), customer.getOrderHeaders()) && Objects.equals(getAddress(),
                customer.getAddress()) && Objects.equals(getName(), customer.getName());
    }

    @Override public int hashCode() {
        return Objects.hash(super.hashCode(), getAddress(), getName());
    }
}
