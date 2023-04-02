package com.github.ahrooranr.util;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class AvgEntryCost {

    final double objects;

    final double refs;

    final ImmutableMap<Class<?>, Double> primitives;

    final double bytes;

    AvgEntryCost(double objects, double refs, Map<Class<?>, Double> primitives, double bytes) {
        this.objects = objects;
        this.refs = refs;
        this.primitives = ImmutableMap.copyOf(primitives);
        this.bytes = bytes;
    }
}
