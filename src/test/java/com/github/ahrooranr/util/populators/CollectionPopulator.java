package com.github.ahrooranr.util.populators;

import com.github.ahrooranr.util.entry.EntryFactory;
import com.google.common.base.Supplier;

import java.util.Collection;

public class CollectionPopulator extends MutablePopulator<Collection> {
    public CollectionPopulator(Supplier<? extends Collection> collectionFactory) {
        super(collectionFactory);
    }

    public CollectionPopulator(Supplier<? extends Collection> collectionFactory, EntryFactory entryFactory) {
        super(collectionFactory, entryFactory);
    }

    public void addEntry(Collection collection) {
        collection.add(newEntry());
    }
}