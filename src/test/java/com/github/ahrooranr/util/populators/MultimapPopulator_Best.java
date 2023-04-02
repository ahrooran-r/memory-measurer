package com.github.ahrooranr.util.populators;

import com.github.ahrooranr.util.entry.EntryFactory;
import com.google.common.base.Supplier;
import com.google.common.collect.Multimap;

public class MultimapPopulator_Best extends MutablePopulator<Multimap> {
    public MultimapPopulator_Best(Supplier<? extends Multimap> multimapFactory) {
        super(multimapFactory);
    }

    public MultimapPopulator_Best(Supplier<? extends Multimap> multimapFactory, EntryFactory entryFactory) {
        super(multimapFactory, entryFactory);
    }

    private final Object key = newEntry();

    public void addEntry(Multimap multimap) {
        multimap.put(key, newEntry());
    }
}
