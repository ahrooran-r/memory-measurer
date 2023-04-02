package com.github.ahrooranr.util.populators;

public interface Populator<C> {
    Class<?> getEntryType();

    C construct(int entries);
}
