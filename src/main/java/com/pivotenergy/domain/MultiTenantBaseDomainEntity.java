package com.pivotenergy.domain;

import com.pivotenergy.security.JWTAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Slf4j
public abstract class MultiTenantBaseDomainEntity<T> extends BaseDomainEntity<T> implements MultiTenant<T> {
    @NotNull
    @Column(insertable = false, updatable = false)
    protected String groupId;

    @Override
    public String getGroupId() {
        return groupId;
    }

    @Override
    public T setGroupId(String groupId) {
        this.groupId = groupId;
        //noinspection unchecked
        return (T) this;
    }

    @PrePersist
    public void prePersist() {
        log.trace("Invoked before persisting {}", this.getClass().getSimpleName());
        setTenantGroupId();
    }

    @PreUpdate
    public void preUpdate() {
        log.trace("Invoked before updating {}", this.getClass().getSimpleName());
        setTenantGroupId();
    }

    private void setTenantGroupId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth instanceof JWTAuthentication)) {
            this.groupId = ((JWTAuthentication) auth).getTenantId();
            log.trace("Setting groupId={}", this.groupId);
        }
    }
}
