package com.github.ahrooranr.util.populators;

import com.github.ahrooranr.util.entry.EntryFactory;
import com.google.common.base.Supplier;

import java.util.Map;

public class MapPopulator extends MutablePopulator<Map> {
    public MapPopulator(Supplier<? extends Map> mapFactory) {
        super(mapFactory);
    }

    public MapPopulator(Supplier<? extends Map> mapFactory, EntryFactory entryFactory) {
        super(mapFactory, entryFactory);
    }

    public void addEntry(Map map) {
        map.put(newEntry(), newEntry());
    }
}
