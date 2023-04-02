package com.github.ahrooranr.util.populators;

import com.github.ahrooranr.util.entry.EntryFactories;
import com.google.common.collect.ImmutableSortedSet;

import java.util.concurrent.ThreadLocalRandom;

public class ImmutableSortedSetPopulator extends AbstractPopulator<ImmutableSortedSet> {
    public ImmutableSortedSetPopulator() {
        super(EntryFactories.COMPARABLE);
    }

    public ImmutableSortedSet construct(int entries) {
        ImmutableSortedSet.Builder builder = ImmutableSortedSet.<Comparable>naturalOrder();
        for (int i = 0; i < entries; i++) {
            builder.add(ThreadLocalRandom.current().nextInt(entries));
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "ImmutableSortedSet";
    }
}