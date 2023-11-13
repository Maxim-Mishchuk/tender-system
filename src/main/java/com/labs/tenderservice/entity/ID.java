package com.labs.tenderservice.entity;

import java.util.Objects;

public record ID (long value) {
    public static ID generateID() {
        return new ID(System.nanoTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ID value1 = (ID) o;
        return value == value1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
