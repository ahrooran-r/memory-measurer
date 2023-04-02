package com.github.ahrooranr.util.populators;

import com.github.ahrooranr.util.entry.EntryFactory;
import com.google.common.base.Supplier;
import com.google.common.collect.Multimap;

public class MultimapPopulator_Worst extends MutablePopulator<Multimap> {
    public MultimapPopulator_Worst(Supplier<? extends Multimap> multimapFactory) {
        super(multimapFactory);
    }

    public MultimapPopulator_Worst(Supplier<? extends Multimap> multimapFactory, EntryFactory entryFactory) {
        super(multimapFactory, entryFactory);
    }

    public void addEntry(Multimap multimap) {
        multimap.put(newEntry(), newEntry());
    }
}