package com.github.ahrooranr.util;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * I am trying to move away from Guava constructs of {@code com.google.common.base.Predicate}
 * now that Java officially supports {@link Predicate}.
 * <p>
 * This class simply mimics {@code com.google.common.base.Predicates.AndPredicate} returned by
 * {@code com.google.common.base.Predicates#and(Iterable)}
 */
@AllArgsConstructor
public class AndPredicate<T> implements Predicate<T>, Serializable {

    private final Collection<? extends Predicate<? super T>> predicates;

    @Override
    public boolean test(T t) {
        for (Predicate<? super T> predicate : predicates) {
            if (!predicate.test(t)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return predicates.hashCode() + 0x12472c2c;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AndPredicate<?>) {
            AndPredicate<?> that = (AndPredicate<?>) obj;
            return predicates.equals(that.predicates);
        }
        return false;
    }

    /**
     * Guava's {@code Predicates#toStringHelper(String, Iterable)}.
     * Copied to remove dependency.
     */
    @SneakyThrows
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("PredicateAssist#").append("and").append('(');
        boolean first = true;
        for (Object o : predicates) {
            if (!first) {
                builder.append(',');
            }
            builder.append(o);
            first = false;
        }
        return builder.append(')').toString();
    }
}
