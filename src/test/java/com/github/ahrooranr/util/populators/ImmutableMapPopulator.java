package com.github.ahrooranr.util.populators;

import com.google.common.collect.ImmutableMap;

public class ImmutableMapPopulator extends AbstractPopulator<ImmutableMap> {
    public ImmutableMap construct(int entries) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (int i = 0; i < entries; i++) {
            builder.put(newEntry(), newEntry());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "ImmutableMap";
    }
}