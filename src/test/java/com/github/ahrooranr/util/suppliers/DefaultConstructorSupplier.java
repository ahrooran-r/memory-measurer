package com.github.ahrooranr.util.suppliers;

import com.google.common.base.Supplier;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

public class DefaultConstructorSupplier<C> implements Supplier<C> {
    private final Constructor<C> constructor;

    public DefaultConstructorSupplier(Class<C> clazz) throws NoSuchMethodException {
        this.constructor = clazz.getConstructor();
    }

    @SneakyThrows
    public C get() {
        return constructor.newInstance();
    }

    @Override
    public String toString() {
        return constructor.getDeclaringClass().getSimpleName();
    }

    public static <C> DefaultConstructorSupplier<C> defaultSupplierFor(Class<C> clazz) throws NoSuchMethodException {
        return new DefaultConstructorSupplier<>(clazz);
    }
}
