package com.github.ahrooranr.util.populators;

import com.google.common.collect.ImmutableBiMap;

public class ImmutableBiMapPopulator extends AbstractPopulator<ImmutableBiMap> {
    public ImmutableBiMap construct(int entries) {
        ImmutableBiMap.Builder builder = ImmutableBiMap.builder();
        for (int i = 0; i < entries; i++) {
            builder.put(newEntry(), newEntry());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "ImmutableBiMap";
    }
}