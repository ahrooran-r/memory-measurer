package com.github.ahrooranr.util.populators;

import com.github.ahrooranr.util.entry.EntryFactories;
import com.google.common.collect.ImmutableSortedMap;

public class ImmutableSortedMapPopulator extends AbstractPopulator<ImmutableSortedMap> {
    public ImmutableSortedMapPopulator() {
        super(EntryFactories.COMPARABLE);
    }

    public ImmutableSortedMap construct(int entries) {
        ImmutableSortedMap.Builder builder = ImmutableSortedMap.<Comparable, Object>naturalOrder();
        for (int i = 0; i < entries; i++) {
            builder.put(newEntry(), newEntry());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "ImmutableSortedMap";
    }
}