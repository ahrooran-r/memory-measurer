package com.github.ahrooranr.util;

import java.util.Map;

public class MapAssist {

    /**
     * Trying to emulate {@code com.google.common.collect.Multiset#add(Object)} with {@link java.util.HashMap}
     *
     * @param map   the map which will have the key and occurrences
     * @param key the new instance of key type
     * @param <K>   type of key
     * @return the number of occurrences of specific key
     */
    public static <K> Integer add(Map<K, Integer> map, K key) {
        return map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1);
    }
}
