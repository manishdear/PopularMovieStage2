package com.manishdear.unofficialcoder.popularmovies.data.local.model;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;

    private Resource(@NonNull Status status, @Nullable T data,
                     @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resource)) return false;
        Resource<?> resource = (Resource<?>) o;
        return status == resource.status &&
                Objects.equals(data, resource.data) &&
                Objects.equals(message, resource.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(status, data, message);
    }

    public enum Status {
        SUCCESS, ERROR, LOADING
    }
}
