package com.github.ahrooranr.util.entry;

import com.google.common.base.Supplier;

public interface EntryFactory extends Supplier<Object> {
    Class<?> getEntryType();
}
