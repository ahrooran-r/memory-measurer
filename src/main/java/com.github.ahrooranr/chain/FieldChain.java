package com.github.ahrooranr.chain;

import com.github.ahrooranr.core.Chain;
import lombok.Getter;

import java.lang.reflect.Field;

public class FieldChain extends Chain {

    @Getter
    private final Field field;

    public FieldChain(Chain parent, Object value, Field referringField) {
        super(parent, value);
        this.field = referringField;
    }

    @Override
    public boolean isThroughField() {
        return true;
    }

    @Override
    public boolean isThroughArrayIndex() {
        return false;
    }

    @Override
    public Class<?> getValueType() {
        return field.getType();
    }
}
