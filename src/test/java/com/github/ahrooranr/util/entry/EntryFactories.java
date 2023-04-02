package com.github.ahrooranr.util.entry;

public enum EntryFactories implements EntryFactory {
    REGULAR {
        public Class<?> getEntryType() {
            return Element.class;
        }

        public Object get() {
            return new Element();
        }
    },
    COMPARABLE {
        public Class<?> getEntryType() {
            return ComparableElement.class;
        }

        public Object get() {
            return new ComparableElement();
        }
    },
    DELAYED {
        public Class<?> getEntryType() {
            return DelayedElement.class;
        }

        public Object get() {
            return new DelayedElement();
        }
    };
}
