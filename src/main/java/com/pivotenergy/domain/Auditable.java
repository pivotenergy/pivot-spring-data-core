package com.pivotenergy.domain;

import java.util.Date;

public interface Auditable<T> {
    Date getCreatedAt();
    String getCreatedBy();
    Date getUpdatedAt();
    String getUpdatedBy();
}
