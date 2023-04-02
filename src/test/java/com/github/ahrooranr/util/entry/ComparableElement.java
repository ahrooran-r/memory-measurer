package com.github.ahrooranr.util.entry;

public class ComparableElement extends Element implements Comparable<Object> {
    public int compareTo(Object o) {
        if (this == o) return 0;
        else return 1;
    }
}