package com.labs.tenderservice.entity;

import java.util.Objects;

public record ID (long id) {
    public static ID generateID() {
        return new ID(System.nanoTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ID id1 = (ID) o;
        return id == id1.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
