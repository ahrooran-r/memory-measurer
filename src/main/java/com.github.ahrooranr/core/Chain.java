package com.github.ahrooranr.core;

import com.github.ahrooranr.chain.ArrayIndexChain;
import com.github.ahrooranr.chain.FieldChain;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;

/**
 * A chain of references, which starts at a root object and leads to a
 * particular value (either an object or a primitive).
 *
 * @author andreou
 */
@AllArgsConstructor
public abstract class Chain {

    private final Chain parent;

    /**
     * Returns the value that this Chain leads to. If the value is a primitive,
     * a wrapper object is returned instead.
     */
    @Getter
    private final Object value;

    public static Chain root(Object value) {
        return new Chain(null, Objects.requireNonNull(value)) {
            @Override
            public Class<?> getValueType() {
                return getValue().getClass();
            }
        };
    }

    public FieldChain appendField(Field field, Object value) {
        return new FieldChain(this, value, Objects.requireNonNull(field));
    }

    public ArrayIndexChain appendArrayIndex(int arrayIndex, Object value) {
        return new ArrayIndexChain(this, value, arrayIndex);
    }

    /**
     * Returns whether this chain has a parent. This returns false only when
     * this chain represents the root object itself.
     */
    public boolean hasParent() {
        return parent != null;
    }

    /**
     * Returns the parent .chain, from which this .chain was created.
     *
     * @throws IllegalStateException if {@code !hasParent()}, then an
     */
    public Chain getParent() {
        if (null == parent) {
            throw new IllegalStateException("This is the root value, it has no parent");
        }
        return parent;
    }

    public abstract Class<?> getValueType();

    /**
     * Returns whether the connection of the parent .chain and this .chain is
     * through a field (of the getParent().getValue().getClass() class).
     */
    public boolean isThroughField() {
        return false;
    }

    /**
     * Returns whether the connection of the parent .chain and this .chain is
     * through an array index, i.e. the parent leads to an array, and this
     * .chain leads to an element of that array.
     */
    public boolean isThroughArrayIndex() {
        return false;
    }

    /**
     * Returns whether the value of this .chain represents a primitive.
     */
    public boolean isPrimitive() {
        return getValueType().isPrimitive();
    }

    /**
     * Returns the root object of this .chain.
     */
    public Object getRoot() {
        Chain current = this;
        while (current.hasParent()) {
            current = current.getParent();
        }
        return current.getValue();
    }

    Deque<Chain> reverse() {
        Deque<Chain> reverseChain = new ArrayDeque<>(8);
        Chain current = this;
        reverseChain.addFirst(current);
        while (current.hasParent()) {
            current = current.getParent();
            reverseChain.addFirst(current);
        }
        return reverseChain;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(32);

        Iterator<Chain> it = reverse().iterator();
        it.next();
        sb.append("rootObject");
        while (it.hasNext()) {
            sb.append("->");
            Chain current = it.next();
            if (current.isThroughField()) {
                sb.append(((FieldChain) current).getField().getName());
            } else if (current.isThroughArrayIndex()) {
                sb.append("[").append(((ArrayIndexChain) current).getArrayIndex()).append("]");
            }
        }
        return sb.toString();
    }

    /*
     * Does reuse an existing StringBuffer instead of allocating a new one
     */
    public void toString(StringBuilder sb) {
        Iterator<Chain> it = reverse().iterator();
        it.next();
        sb.append("rootObject");
        while (it.hasNext()) {
            sb.append("->");
            Chain current = it.next();
            if (current.isThroughField()) {
                sb.append(((FieldChain) current).getField().getName());
            } else if (current.isThroughArrayIndex()) {
                sb.append("[").append(((ArrayIndexChain) current).getArrayIndex()).append("]");
            }
        }
    }
}
