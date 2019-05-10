package com.pivotenergy.domain;

public interface MultiTenant<T> {
    String getGroupId();
    T setGroupId(String groupId);
}
