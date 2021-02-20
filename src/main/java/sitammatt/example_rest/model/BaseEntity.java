package sitammatt.example_rest.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @NotNull
    @Column(name = "createdDate", nullable = false, updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "modificationDate", nullable = true, updatable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "guid", unique = true, nullable = false, updatable = false)
    private UUID guid;

    @PrePersist
    public void initCreationDate() {
        createdDate = new Date();
    }

    @PreUpdate
    public void initModificationDate() {
        modifiedDate = new Date();
    }

    public BaseEntity() {
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }
}
