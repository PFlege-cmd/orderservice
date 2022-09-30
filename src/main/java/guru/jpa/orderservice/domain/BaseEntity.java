package guru.jpa.orderservice.domain;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Column(updatable = false)
    Timestamp creationTimestamp;

    @UpdateTimestamp
    Timestamp updatedTimestamp;

    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    Long id;

    public Timestamp getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreationTimestamp() {
        return creationTimestamp;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(getCreationTimestamp(), that.getCreationTimestamp()) && Objects.equals(
                getUpdatedTimestamp(), that.getUpdatedTimestamp()) && Objects.equals(getId(), that.getId());
    }

    @Override public int hashCode() {
        return Objects.hash(getCreationTimestamp(), getUpdatedTimestamp(), getId());
    }
}
