package com.github.ahrooranr.chain;

import com.github.ahrooranr.core.Chain;
import lombok.Getter;

public class ArrayIndexChain extends Chain {

    @Getter
    private final int arrayIndex;

    public ArrayIndexChain(Chain parent, Object value, int arrayIndex) {
        super(parent, value);
        this.arrayIndex = arrayIndex;
    }

    @Override
    public boolean isThroughField() {
        return false;
    }

    @Override
    public boolean isThroughArrayIndex() {
        return true;
    }

    @Override
    public Class<?> getValueType() {
        return getParent().getValue().getClass().getComponentType();
    }
}
