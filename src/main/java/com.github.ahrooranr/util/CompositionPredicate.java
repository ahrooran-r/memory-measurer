package com.github.ahrooranr.util;


import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * I am trying to move away from Guava constructs of {@code com.google.common.base.Predicate}
 * and {@code com.google.common.base.Function} now that Java officially
 * supports {@link Predicate} and {@link Function}.
 * <p>
 * This class simply mimics {@code com.google.common.base.Predicates.CompositionPredicate} returned by
 * {@code com.google.common.base.Predicates#compose(com.google.common.base.Predicate, com.google.common.base.Function)}
 */
public class CompositionPredicate<A, B>
        implements Predicate<A>, Serializable {

    private final Predicate<? super B> predicate;

    private final Function<A, ? extends B> function;

    public CompositionPredicate(Predicate<B> predicate, Function<A, ? extends B> function) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(function);
        this.predicate = predicate;
        this.function = function;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CompositionPredicate<?, ?>) {
            CompositionPredicate<?, ?> that = (CompositionPredicate<?, ?>) obj;
            return function.equals(that.function) && predicate.equals(that.predicate);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return function.hashCode() ^ predicate.hashCode();
    }

    @Override
    public String toString() {
        return predicate + "(" + function + ")";
    }

    @Override
    public boolean test(A a) {
        return predicate.test(function.apply(a));
    }
}
