package com.pivotenergy.domain;

public interface DomainEntity<T> {
    String getId();
    Boolean getDeleted();
    T setDeleted(boolean deleted);
}
