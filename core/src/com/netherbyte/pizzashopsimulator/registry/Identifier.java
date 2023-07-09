package com.netherbyte.pizzashopsimulator.registry;

public class Identifier {
    public final String group;
    public final String key;

    public Identifier(String group, String key) {
        this.group = group;
        this.key = key;
    }

    public String toString() {
        return group + ":" + key;
    }

    public static Identifier of(String x) {
        String[] segments = x.split(":");
        return new Identifier(segments[0], segments[1]);
    }
}
