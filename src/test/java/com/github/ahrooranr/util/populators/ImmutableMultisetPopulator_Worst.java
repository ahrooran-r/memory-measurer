package com.github.ahrooranr.util.populators;

import com.google.common.collect.ImmutableMultiset;

public class ImmutableMultisetPopulator_Worst extends AbstractPopulator<ImmutableMultiset> {
    public ImmutableMultiset construct(int entries) {
        ImmutableMultiset.Builder builder = ImmutableMultiset.builder();
        for (int i = 0; i < entries; i++) {
            builder.add(newEntry());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "ImmutableMultiset_Worst";
    }
}