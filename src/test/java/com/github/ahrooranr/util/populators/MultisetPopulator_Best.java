package com.github.ahrooranr.util.populators;

import com.github.ahrooranr.util.entry.EntryFactory;
import com.google.common.base.Supplier;
import com.google.common.collect.Multiset;

public class MultisetPopulator_Best extends MutablePopulator<Multiset> {
    public MultisetPopulator_Best(Supplier<? extends Multiset> multisetFactory) {
        super(multisetFactory);
    }

    public MultisetPopulator_Best(Supplier<? extends Multiset> multisetFactory, EntryFactory entryFactory) {
        super(multisetFactory, entryFactory);
    }

    private final Object key = newEntry();

    public void addEntry(Multiset multiset) {
        multiset.add(key);
    }
}