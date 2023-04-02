package com.github.ahrooranr.util.populators;

import com.google.common.collect.ImmutableSetMultimap;

public class ImmutableSetMultimapPopulator_Worst extends AbstractPopulator<ImmutableSetMultimap> {
    public ImmutableSetMultimap construct(int entries) {
        ImmutableSetMultimap.Builder builder = ImmutableSetMultimap.builder();
        for (int i = 0; i < entries; i++) {
            builder.put(newEntry(), newEntry());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "ImmutableSetMultimap_Worst";
    }
}