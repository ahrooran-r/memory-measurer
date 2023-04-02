package com.github.ahrooranr.util.populators;

import com.google.common.collect.ImmutableList;

public class ImmutableListPopulator extends AbstractPopulator<ImmutableList> {
    public ImmutableList construct(int entries) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i = 0; i < entries; i++) {
            builder.add(newEntry());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "ImmutableList";
    }
}