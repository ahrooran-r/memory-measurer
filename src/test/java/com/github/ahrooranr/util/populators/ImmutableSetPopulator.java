package com.github.ahrooranr.util.populators;

import com.google.common.collect.ImmutableSet;

public class ImmutableSetPopulator extends AbstractPopulator<ImmutableSet> {
    public ImmutableSet construct(int entries) {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        for (int i = 0; i < entries; i++) {
            builder.add(newEntry());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "ImmutableSet";
    }
}