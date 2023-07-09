package com.netherbyte.pizzashopsimulator.registry;

import java.util.ArrayList;
import java.util.List;

public class SimpleRegistry <T> {
    private final List<Identifier> identifierList = new ArrayList<>();
    private final List<T> objectList = new ArrayList<>();

    public void add(Identifier identifier, T object) {
        this.identifierList.add(identifier);
        this.objectList.add(object);
    }

    public List<Identifier> getIdentifierList() { return this.identifierList; }
    public List<T> getObjectList() { return this.objectList; }

    public T getObject(Identifier identifier) {
        for (int i = 0; i < identifierList.size(); i++) {
            Identifier v = identifierList.get(i);
            if (v.group.equals(identifier.group) && v.key.equals(identifier.key)) {
                return objectList.get(i);
            }
        }
        return null;
    }

    public Identifier getIdentifier(T object) {
        for (int i = 0; i < objectList.size(); i++) {
            T v = objectList.get(i);
            if (v == object) {
                return identifierList.get(i);
            }
        }
        return null;
    }
}
