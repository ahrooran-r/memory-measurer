package com.github.ahrooranr.util.populators;

import com.github.ahrooranr.util.entry.EntryFactory;
import com.google.common.base.Supplier;
import com.google.common.collect.Table;

public class TablePopulator_Worst extends MutablePopulator<Table> {
    public TablePopulator_Worst(Supplier<? extends Table> tableFactory) {
        super(tableFactory);
    }

    public TablePopulator_Worst(Supplier<? extends Table> tableFactory, EntryFactory entryFactory) {
        super(tableFactory, entryFactory);
    }

    public void addEntry(Table table) {
        table.put(newEntry(), newEntry(), newEntry());
    }
}