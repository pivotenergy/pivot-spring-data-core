package com.pivotenergy.domain;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Slf4j
public abstract class BaseDomainEntity<T> implements DomainEntity<T>, Auditable<T>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 128)
    protected String id;

    protected Boolean deleted = false;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    @CreatedBy
    protected String createdBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;

    @LastModifiedBy
    protected String updatedBy;

    @Version
    protected Long version = 0L;


    @Override
    public String getId() {
        return id;
    }

    public T setId(String id) {
        this.id = id;
        //noinspection unchecked
        return (T) this;
    }

    @Override
    public Boolean getDeleted() {
        return deleted;
    }

    @Override
    public T setDeleted(boolean deleted) {
        this.deleted = deleted;
        //noinspection unchecked
        return (T) this;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    public Long getVersion() {
        return version;
    }

    public BaseDomainEntity<T> setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public BaseDomainEntity<T> setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public BaseDomainEntity<T> setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public BaseDomainEntity<T> setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public BaseDomainEntity<T> setVersion(Long version) {
        this.version = version;
        return this;
    }

    private boolean identityEquals(BaseDomainEntity<T> other) {
        if (getId() == null) {
            return false;
        }
        return getId().equals(other.getId());
    }

    private int identityHashCode() {
        return new HashCodeBuilder().append(this.getId()).toHashCode();
    }

    @Override
    public final int hashCode() {
        return identityHashCode();
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        //noinspection unchecked
        return identityEquals((BaseDomainEntity<T>) o);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder
                .reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

    @PrePersist
    public void methodInvokedBeforePersist() {
        log.trace("Invoked before persisting {}", this.getClass().getSimpleName());
    }

    @PostPersist
    public void methodInvokedAfterPersist() {
        log.trace("Invoked after persisting {}", this.getClass().getSimpleName());
    }

    @PreUpdate
    public void methodInvokedBeforeUpdate() {
        log.trace("Invoked before updating {}", this.getClass().getSimpleName());
    }

    @PostUpdate
    public void methodInvokedAfterUpdate() {
        log.trace("Invoked after updating {}", this.getClass().getSimpleName());
    }

    @PreRemove
    public void methodInvokedBeforeRemove() {
        log.trace("Invoked before removing {}", this.getClass().getSimpleName());
    }

    @PostRemove
    public void methodInvokedAfterRemove() {
        log.trace("Invoked after removing {}", this.getClass().getSimpleName());
    }
}
