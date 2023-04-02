package com.github.ahrooranr.util.populators;

import com.github.ahrooranr.util.entry.EntryFactories;
import com.github.ahrooranr.util.entry.EntryFactory;

public abstract class AbstractPopulator<C> implements Populator<C> {
    private final EntryFactory entryFactory;

    AbstractPopulator() {
        this(EntryFactories.REGULAR);
    }

    AbstractPopulator(EntryFactory entryFactory) {
        this.entryFactory = entryFactory;
    }

    protected Object newEntry() {
        return entryFactory.get();
    }

    public Class<?> getEntryType() {
        return entryFactory.getEntryType();
    }
}
