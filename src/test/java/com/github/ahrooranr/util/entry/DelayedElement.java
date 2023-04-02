package com.github.ahrooranr.util.entry;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedElement extends Element implements Delayed {
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    public int compareTo(Delayed o) {
        if (this == o) return 0;
        else return 1;
    }
}
