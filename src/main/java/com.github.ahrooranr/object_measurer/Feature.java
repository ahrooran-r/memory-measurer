package com.github.ahrooranr.object_measurer;

import com.github.ahrooranr.core.ObjectVisitor;

import java.util.EnumSet;

/**
 * Enumeration of features that may be optionally requested for an object
 * traversal.
 *
 * @see ObjectExplorer#exploreObject(Object, ObjectVisitor, EnumSet)
 */
public enum Feature {
    /**
     * Null references should be visited.
     */
    VISIT_NULL,

    /**
     * Primitive values should be visited.
     */
    VISIT_PRIMITIVES
}
