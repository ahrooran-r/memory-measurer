package com.github.ahrooranr.memory_measurer;

import static com.github.ahrooranr.util.Analyze.*;
import static com.github.ahrooranr.util.Util.caption;
import static com.github.ahrooranr.util.suppliers.DefaultConstructorSupplier.defaultSupplierFor;

import com.github.ahrooranr.util.entry.EntryFactories;
import com.github.ahrooranr.util.populators.*;
import com.google.common.collect.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ElementCostOfDataStructuresTest {

    @Test
    public void noExceptionThrownTest() throws Exception {
        caption(String.format("    %2s-bit architecture   ", System.getProperty("sun.arch.data.model")));
        caption("  Basic Lists, Sets, Maps ");

        analyze(new CollectionPopulator(defaultSupplierFor(ArrayList.class)));
        analyze(new ImmutableListPopulator());

        analyze(new CollectionPopulator(defaultSupplierFor(HashSet.class)));
        analyze(new ImmutableSetPopulator());

        analyze(new CollectionPopulator(defaultSupplierFor(TreeSet.class), EntryFactories.COMPARABLE));
        analyze(new ImmutableSortedSetPopulator());

        analyze(new MapPopulator(defaultSupplierFor(HashMap.class)));
        analyze(new ImmutableMapPopulator());
        analyze(new MapPopulator(defaultSupplierFor(LinkedHashMap.class)));

        analyze(new MapPopulator(defaultSupplierFor(TreeMap.class), EntryFactories.COMPARABLE));
        analyze(new ImmutableSortedMapPopulator());

        caption("ConcurrentHashMap/MapMaker");

        analyze(new MapPopulator(defaultSupplierFor(ConcurrentHashMap.class)));
        analyzeMapMaker("MapMaker", MapMaker::new);
        analyze("MapMaker_SoftKeys", new MapPopulator(new MapMaker().weakKeys()::makeMap));
        analyze("MapMaker_SoftValues", new MapPopulator(new MapMaker().weakValues()::makeMap));
        analyze("MapMaker_SoftKeysValues", new MapPopulator(new MapMaker().weakKeys().weakValues()::makeMap));

        caption("        Multisets         ");

        analyze("HashMultiset_Worst", new MultisetPopulator_Worst(HashMultiset::create));
        analyze("LinkedHashMultiset_Worst", new MultisetPopulator_Worst(LinkedHashMultiset::create));
        analyze("TreeMultiset_Worst", new MultisetPopulator_Worst(TreeMultiset::create, EntryFactories.COMPARABLE));
        analyze("ConcurrentHashMultiset_Worst", new MultisetPopulator_Worst(ConcurrentHashMultiset::create));

        System.out.println();

        analyze("HashMultiset_Best ", new MultisetPopulator_Best(HashMultiset::create));
        analyze("LinkedHashMultiset_Best ", new MultisetPopulator_Best(LinkedHashMultiset::create));
        analyze("TreeMultiset_Best ", new MultisetPopulator_Best(TreeMultiset::create, EntryFactories.COMPARABLE));
        analyze("ConcurrentHashMultiset_Best ", new MultisetPopulator_Best(ConcurrentHashMultiset::create));

        caption("        Multimaps         ");

        analyze("HashMultimap_Worst", new MultimapPopulator_Worst(HashMultimap::create));
        analyze("LinkedHashMultimap_Worst", new MultimapPopulator_Worst(LinkedHashMultimap::create));
        analyze("TreeMultimap_Worst", new MultimapPopulator_Worst(TreeMultimap::create, EntryFactories.COMPARABLE));
        analyze("ArrayListMultimap_Worst", new MultimapPopulator_Worst(ArrayListMultimap::create));
        analyze("LinkedListMultimap_Worst", new MultimapPopulator_Worst(LinkedListMultimap::create));
        analyze(new ImmutableMultimapPopulator_Worst());
        analyze(new ImmutableListMultimapPopulator_Worst());
        analyze(new ImmutableSetMultimapPopulator_Worst());

        System.out.println();

        analyze("HashMultimap_Best ", new MultimapPopulator_Best(HashMultimap::create));
        analyze("LinkedHashMultimap_Best ", new MultimapPopulator_Best(LinkedHashMultimap::create));
        analyze("TreeMultimap_Best ", new MultimapPopulator_Best(TreeMultimap::create, EntryFactories.COMPARABLE));
        analyze("ArrayListMultimap_Best ", new MultimapPopulator_Best(ArrayListMultimap::create));
        analyze("LinkedListMultimap_Best ", new MultimapPopulator_Best(LinkedListMultimap::create));
        analyze(new ImmutableMultimapPopulator_Best());
        analyze(new ImmutableListMultimapPopulator_Best());
        analyze(new ImmutableSetMultimapPopulator_Best());

        caption("          Tables          ");

        analyze("HashBasedTable", new TablePopulator_Worst(HashBasedTable::create));
        analyze("TreeBasedTable", new TablePopulator_Worst(TreeBasedTable::create, EntryFactories.COMPARABLE));

        caption("          BiMaps          ");

        analyze("HashBiMap", new MapPopulator(HashBiMap::create));
        analyze(new ImmutableBiMapPopulator());

        caption("           Misc           ");

        analyze(new MapPopulator(defaultSupplierFor(WeakHashMap.class)));
        analyze(new CollectionPopulator(defaultSupplierFor(LinkedList.class)));
        analyze(new CollectionPopulator(defaultSupplierFor(ArrayDeque.class)));
        analyze(new CollectionPopulator(defaultSupplierFor(LinkedHashSet.class)));
        analyze(new CollectionPopulator(defaultSupplierFor(PriorityQueue.class), EntryFactories.COMPARABLE));
        analyze(new CollectionPopulator(defaultSupplierFor(PriorityBlockingQueue.class), EntryFactories.COMPARABLE));
        analyze(new CollectionPopulator(defaultSupplierFor(ConcurrentSkipListSet.class), EntryFactories.COMPARABLE));
        analyze(new CollectionPopulator(defaultSupplierFor(CopyOnWriteArrayList.class)));
        analyze(new CollectionPopulator(defaultSupplierFor(CopyOnWriteArraySet.class)));
        analyze(new CollectionPopulator(defaultSupplierFor(DelayQueue.class), EntryFactories.DELAYED));
        analyze(new CollectionPopulator(defaultSupplierFor(LinkedBlockingQueue.class)));
        analyze(new CollectionPopulator(defaultSupplierFor(LinkedBlockingDeque.class)));

        caption("  Synchronization Structures");

        analyzeOneOff("ReentrantLock", new ReentrantLock(true));
        analyzeOneOff("Semaphore", new Semaphore(1, true));
        analyzeOneOff("ReadWriteLock", new ReentrantReadWriteLock(true));
    }
}




