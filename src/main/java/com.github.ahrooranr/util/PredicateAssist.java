package com.github.ahrooranr.util;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateAssist {

    /**
     * An alternative to Guava's {@code Predicates#compose(com.google.common.base.Predicate, com.google.common.base.Function)}.
     * <p>
     * Returns the composition of a function and a predicate. For every {@code x}, the generated
     * predicate returns {@code predicate(function(x))}.
     *
     * @return the composition of the provided function and predicate
     */
    public static <A, B> Predicate<A> compose(
            Predicate<? super B> predicate,
            Function<A, ? extends B> function
    ) {
        return new CompositionPredicate<>(predicate, function);
    }

    /**
     * An alternative to Guava's {@code Predicates#and(Iterable)}
     * <p>
     * Returns a predicate that evaluates to {@code true} if each of its components evaluates to
     * {@code true}. The components are evaluated in order, and evaluation will be "short-circuited"
     * as soon as a false predicate is found. It defensively copies the iterable passed in, so future
     * changes to it won't alter the behavior of this predicate. If {@code components} is empty, the
     * returned predicate will always evaluate to {@code true}.
     */
    public static <T> Predicate<T> and(Collection<? extends Predicate<? super T>> predicates) {
        Collection<? extends Predicate<? super T>> collection =
                predicates.stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
        return new AndPredicate<>(collection);
    }
}
