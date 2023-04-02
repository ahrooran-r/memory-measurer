package com.github.ahrooranr.util.populators;

import com.github.ahrooranr.util.entry.EntryFactories;
import com.github.ahrooranr.util.entry.EntryFactory;
import com.google.common.base.Supplier;

public abstract class MutablePopulator<C> extends AbstractPopulator<C> {

    private final Supplier<? extends C> factory;

    public MutablePopulator(Supplier<? extends C> factory) {
        this(factory, EntryFactories.REGULAR);
    }

    public MutablePopulator(Supplier<? extends C> factory, EntryFactory entryFactory) {
        super(entryFactory);
        this.factory = factory;
    }

    protected abstract void addEntry(C target);

    public C construct(int entries) {
        C collection = factory.get();
        for (int i = 0; i < entries; i++) {
            addEntry(collection);
        }
        return collection;
    }

    @Override
    public String toString() {
        return factory.toString();
    }
}
