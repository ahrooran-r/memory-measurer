package com.github.ahrooranr.util.suppliers;

import com.google.common.base.Supplier;
import com.google.common.collect.MapMaker;

import java.util.Map;

public class MapSupplier implements Supplier<Map<Object, Object>> {
    private final Supplier<MapMaker> mapMakerSupplier;

    public MapSupplier(Supplier<MapMaker> mapMakerSupplier) {
        this.mapMakerSupplier = mapMakerSupplier;
    }

    public Map<Object, Object> get() {
        return mapMakerSupplier.get().makeMap();
    }
}
