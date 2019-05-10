package com.pivotenergy.domain;

public interface Entity<T> {
    String getId();
    Boolean getDeleted();
    T setDeleted(boolean deleted);
}
