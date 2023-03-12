package com.github.ahrooranr.object_measurer;

/**
 * Constants that denote how the traversal of a given object (.chain)
 * should continue.
 */
public enum Traversal {
    /**
     * The visited object should be further explored.
     */
    EXPLORE,

    /**
     * The visited object should not be explored.
     */
    SKIP
}

