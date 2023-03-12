package com.github.ahrooranr.object_measurer;

import com.github.ahrooranr.core.Chain;
import com.github.ahrooranr.core.ObjectVisitor;
import com.github.ahrooranr.util.MapAssist;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class MeasuringGraphVisitor implements ObjectVisitor<FootPrint> {
    private int objects;
    // -1 to account for the root, which has no reference leading to it
    private int references = -1;
    private final Map<Class<?>, Integer> primitives = new HashMap<>();

    private final Predicate<Chain> predicate;

    @Override
    public Traversal visit(Chain chain) {
        if (chain.isPrimitive()) {
            MapAssist.add(primitives, chain.getValueType());
            return Traversal.SKIP;
        } else {
            references++;
        }
        if (predicate.test(chain) && chain.getValue() != null) {
            objects++;
            return Traversal.EXPLORE;
        }
        return Traversal.SKIP;
    }

    @Override
    public FootPrint result() {
        return new FootPrint(objects, references, primitives);
    }
}

