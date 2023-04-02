package com.github.ahrooranr.util.populators;

import com.google.common.collect.ImmutableMultimap;

public class ImmutableMultimapPopulator_Worst extends AbstractPopulator<ImmutableMultimap> {
    public ImmutableMultimap construct(int entries) {
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        for (int i = 0; i < entries; i++) {
            builder.put(newEntry(), newEntry());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "ImmutableMultimap_Worst";
    }
}