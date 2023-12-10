package com.food.ordering.system.valueObject;

public abstract class BaseId<T> {
    private final T value;

    protected BaseId(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BaseId)) {
            return false;
        }
        BaseId<?> other = (BaseId<?>) obj;
        if (this.value == null || other.value == null) {
            return false;
        }
        return this.value.equals(other.value);
    }

    @Override

    public int hashCode() {
        return value == null ? 0 : value.hashCode();
    }

    


}
