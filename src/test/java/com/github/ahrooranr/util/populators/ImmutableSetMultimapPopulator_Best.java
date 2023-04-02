package com.github.ahrooranr.util.populators;

import com.google.common.collect.ImmutableSetMultimap;

public class ImmutableSetMultimapPopulator_Best extends AbstractPopulator<ImmutableSetMultimap> {
    public ImmutableSetMultimap construct(int entries) {
        ImmutableSetMultimap.Builder builder = ImmutableSetMultimap.builder();
        Object key = newEntry();
        for (int i = 0; i < entries; i++) {
            builder.put(key, newEntry());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "ImmutableSetMultimap_Best ";
    }
}