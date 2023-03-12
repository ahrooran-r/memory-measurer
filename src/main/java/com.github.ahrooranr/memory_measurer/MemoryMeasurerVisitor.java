package com.github.ahrooranr.memory_measurer;

import com.github.ahrooranr.core.Chain;
import com.github.ahrooranr.core.InstrumentationGrabber;
import com.github.ahrooranr.core.ObjectVisitor;
import com.github.ahrooranr.object_measurer.Traversal;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

@RequiredArgsConstructor
public class MemoryMeasurerVisitor extends MemoryMeasurer implements ObjectVisitor<Long> {

    private long memory;

    private final Predicate<Chain> predicate;

    public Traversal visit(Chain chain) {
        if (predicate.test(chain)) {
            Object o = chain.getValue();
            memory += InstrumentationGrabber.instrumentation().getObjectSize(o);
            if (Enum.class.isAssignableFrom(o.getClass())) {
                memory -= costOfBareEnumConstant;
            }
            return Traversal.EXPLORE;
        }
        return Traversal.SKIP;
    }

    @Override
    public Long result() {
        return memory;
    }
}
