package guru.jpa.orderservice.domain;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Address {

    @Size(max = 100)
    private String address;
    @Size(max = 100)
    private String city;
    @Size(max = 100)
    private String state;
    @Size(max = 100)
    private String zipCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Address Address1 = (Address) o;
        return Objects.equals(getAddress(), Address1.getAddress()) && Objects.equals(getCity(), Address1.getCity())
                && Objects.equals(getState(), Address1.getState()) && Objects.equals(getZipCode(),
                Address1.getZipCode());
    }

    @Override public int hashCode() {
        return Objects.hash(getAddress(), getCity(), getState(), getZipCode());
    }
}
