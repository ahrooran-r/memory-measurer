package com.github.ahrooranr.object_measurer;

import com.github.ahrooranr.core.Chain;
import com.github.ahrooranr.core.ObjectVisitor;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Array;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class SerializingGraphVisitor implements ObjectVisitor<String> {

    private final Predicate<Chain> predicate;

    private final StringBuilder sb = new StringBuilder(32);

    public Traversal visit(Chain chain) {

        if (chain.isPrimitive()) {
            chain.toString(sb);
            sb.append(": ");
            sb.append(chain.getValue().toString());
            sb.append("\n");
            return Traversal.SKIP;

        } else if (predicate.test(chain)) {
            Object value = chain.getValue();
            chain.toString(sb);
            sb.append(": ");

            if (value != null) {
                sb.append(value.getClass().toString());
                if (value.getClass().isArray()) {
                    int arrayLength = Array.getLength(value);
                    sb.append(", length = ");
                    sb.append(arrayLength);
                }
                sb.append("\n");
                return Traversal.EXPLORE;

            } else {
                sb.append("null");
                sb.append("\n");
                return Traversal.SKIP;
            }

        } else {
            return Traversal.SKIP;
        }
    }

    public String result() {
        return sb.toString();
    }
}

