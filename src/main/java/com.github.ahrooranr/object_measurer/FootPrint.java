package com.github.ahrooranr.object_measurer;

import lombok.Getter;

import java.util.*;

/**
 * The footprint of an object graph.
 */
public class FootPrint {

    /**
     * number of objects of this footprint.
     */
    @Getter
    private final int objects;

    /**
     * number of references of this footprint.
     */
    @Getter
    private final int references;

    /**
     * number of primitives of this footprint
     * (represented by the respective primitive classes,
     * {@literal e.g.} {@code int.class} etc).
     */
    @Getter
    private final Map<Class<?>, Integer> primitives;

    public static final Set<Class<?>> primitiveTypes = new HashSet<>(Arrays.asList(
            boolean.class,
            char.class,
            byte.class,
            short.class,
            int.class,
            long.class,
            float.class,
            double.class
    ));

    /**
     * Constructs a Footprint, by specifying the number of objects,
     * references, and primitives (represented as a {@code Multiset}).
     *
     * @param objects    the number of objects
     * @param references the number of references
     * @param primitives the number of primitives (represented by the
     *                   respective primitive classes, e.g. {@code int.class} etc)
     */
    public FootPrint(int objects, int references, Map<Class<?>, Integer> primitives) {

        if (objects < 0) throw new IllegalArgumentException("Negative number of objects");
        if (references < 0) throw new IllegalArgumentException("Negative number of references");
        if (!primitiveTypes.containsAll(primitives.keySet()))
            throw new IllegalArgumentException("Unexpected primitive type");

        this.objects = objects;
        this.references = references;
        this.primitives = Collections.unmodifiableMap(primitives);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FootPrint.class.getSimpleName() + "[", "]")
                .add("Objects=" + objects)
                .add("References=" + references)
                .add("Primitives=" + primitives)
                .toString();
    }
}
