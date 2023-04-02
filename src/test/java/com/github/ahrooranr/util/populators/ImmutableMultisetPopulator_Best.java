package com.github.ahrooranr.util.populators;

import com.google.common.collect.ImmutableMultiset;

public class ImmutableMultisetPopulator_Best extends AbstractPopulator<ImmutableMultiset> {
    public ImmutableMultiset construct(int entries) {
        ImmutableMultiset.Builder builder = ImmutableMultiset.builder();
        Object key = newEntry();
        for (int i = 0; i < entries; i++) {
            builder.add(key);
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "ImmutableMultiset_Best ";
    }
}
