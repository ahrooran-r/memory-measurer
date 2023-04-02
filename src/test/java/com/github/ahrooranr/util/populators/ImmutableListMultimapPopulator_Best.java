package com.github.ahrooranr.util.populators;

import com.google.common.collect.ImmutableListMultimap;

public class ImmutableListMultimapPopulator_Best extends AbstractPopulator<ImmutableListMultimap> {
    public ImmutableListMultimap construct(int entries) {
        ImmutableListMultimap.Builder builder = ImmutableListMultimap.builder();
        Object key = newEntry();
        for (int i = 0; i < entries; i++) {
            builder.put(key, newEntry());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "ImmutableListMultimap_Best ";
    }
}