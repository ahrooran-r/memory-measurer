package com.github.ahrooranr.object_measurer;

import com.github.ahrooranr.core.Chain;
import com.github.ahrooranr.util.PredicateAssist;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * A tool that can qualitatively measure the footprint ({@literal e.g.}, number
 * of objects, references, primitives) of a graph structure.
 */
public class ObjectSerializer {

    /**
     * Measures the footprint of the specified object graph. The object graph is
     * defined by a root object and whatever object can be reached through that,
     * excluding static fields, {@code Class} objects, and fields defined in
     * {@code enum}s (all these are considered shared values, which should not
     * contribute to the cost of any single object graph).
     * <p>
     * Equivalent to {@code measure(rootObject, Predicates.alwaysTrue())}.
     *
     * @param rootObject the root object of the object graph
     * @return the footprint of the object graph
     */
    public static String measure(Object rootObject) {
        return measure(rootObject, (ignored) -> true);
    }

    /**
     * Measures the footprint of the specified object graph. The object graph is
     * defined by a root object and whatever object can be reached through that,
     * excluding static fields, {@code Class} objects, and fields defined in
     * {@code enum}s (all these are considered shared values, which should not
     * contribute to the cost of any single object graph), and any object for
     * which the user-provided predicate returns {@code false}.
     *
     * @param rootObject     the root object of the object graph
     * @param objectAcceptor a predicate that returns {@code true} for objects to be
     *                       explored (and treated as part of the footprint), or
     *                       {@code false} to forbid the traversal to traverse the given
     *                       object
     * @return the footprint of the object graph
     */
    public static String measure(Object rootObject, Predicate<Object> objectAcceptor) {
        Objects.requireNonNull(objectAcceptor, "predicate");

        Predicate<Chain> completePredicate = PredicateAssist.and(Arrays.asList(
                ObjectExplorer.notEnumFieldsOrClasses,
                PredicateAssist.compose(objectAcceptor, ObjectExplorer.chainToObject),
                new ObjectExplorer.AtMostOncePredicate()));

        return ObjectExplorer.exploreObject(
                rootObject,
                new SerializingGraphVisitor(completePredicate),
                EnumSet.of(Feature.VISIT_PRIMITIVES, Feature.VISIT_NULL)
        );
    }
}
