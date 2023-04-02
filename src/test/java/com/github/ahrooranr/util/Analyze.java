package com.github.ahrooranr.util;

import static com.github.ahrooranr.object_measurer.FootPrint.primitiveTypes;

import com.github.ahrooranr.memory_measurer.MemoryMeasurer;
import com.github.ahrooranr.object_measurer.FootPrint;
import com.github.ahrooranr.object_measurer.ObjectGraphMeasurer;
import com.github.ahrooranr.util.populators.MapPopulator;
import com.github.ahrooranr.util.populators.Populator;
import com.github.ahrooranr.util.suppliers.MapSupplier;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;

import java.util.Map;

public class Analyze {
    private Analyze() {
    }

    public static void analyze(Populator<?> populator) {
        analyze(populator.toString(), populator);
    }

    public static void analyze(String caption, Populator<?> populator) {
        AvgEntryCost cost = averageEntryCost(populator, 16, 256 * 31);
        System.out.printf(
                "%40s :: Bytes = %6.2f, Objects = %5.2f Refs = %5.2f Primitives = %s%n",
                caption,
                cost.bytes,
                cost.objects,
                cost.refs,
                cost.primitives
        );
    }

    public static void analyzeOneOff(String caption, Object o) {
        FootPrint footprint = ObjectGraphMeasurer.measure(o);
        long bytes = MemoryMeasurer.measureBytes(o);
        System.out.printf(
                "%40s :: Bytes = %6d, Objects = %5d Refs = %5d Primitives = %s%n",
                caption,
                bytes,
                footprint.getObjects(),
                footprint.getReferences(),
                footprint.getPrimitives()
        );
    }

    public static AvgEntryCost averageEntryCost(Populator<?> populator, int initialEntries, int entriesToAdd) {
        Preconditions.checkArgument(initialEntries >= 0, "initialEntries negative");
        Preconditions.checkArgument(entriesToAdd > 0, "entriesToAdd negative or zero");

        Predicate<Object> predicate = Predicates.not(Predicates.instanceOf(
                populator.getEntryType()));

        Object collection1 = populator.construct(initialEntries);
        FootPrint footprint1 = ObjectGraphMeasurer.measure(collection1, predicate);
        long bytes1 = MemoryMeasurer.measureBytes(collection1, predicate);

        Object collection2 = populator.construct(initialEntries + entriesToAdd);
        FootPrint footprint2 = ObjectGraphMeasurer.measure(collection2, predicate);
        long bytes2 = MemoryMeasurer.measureBytes(collection2, predicate);

        double objects = (footprint2.getObjects() - footprint1.getObjects()) / (double) entriesToAdd;
        double refs = (footprint2.getReferences() - footprint1.getReferences()) / (double) entriesToAdd;
        double bytes = (bytes2 - bytes1) / (double) entriesToAdd;

        Map<Class<?>, Double> primitives = Maps.newHashMap();
        for (Class<?> primitiveType : primitiveTypes) {
            try {
                int initial = footprint1.getPrimitives().get(primitiveType);
                int ending = footprint2.getPrimitives().get(primitiveType);
                if (initial != ending) {
                    primitives.put(primitiveType, (ending - initial) / (double) entriesToAdd);
                }
            } catch (NullPointerException ignored) {
            }
        }

        return new AvgEntryCost(objects, refs, primitives, bytes);
    }

    public static void analyzeMapMaker(String caption, Supplier<MapMaker> supplier) {
        analyze(caption, new MapPopulator(new MapSupplier(supplier)));
    }
}
