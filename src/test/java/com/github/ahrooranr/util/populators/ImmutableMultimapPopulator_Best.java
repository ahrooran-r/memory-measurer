package com.github.ahrooranr.util.populators;

import com.google.common.collect.ImmutableMultimap;

public class ImmutableMultimapPopulator_Best extends AbstractPopulator<ImmutableMultimap> {
    public ImmutableMultimap construct(int entries) {
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        Object key = newEntry();
        for (int i = 0; i < entries; i++) {
            builder.put(key, newEntry());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "ImmutableMultimap_Best ";
    }
}
