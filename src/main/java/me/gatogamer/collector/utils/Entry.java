package me.gatogamer.collector.utils;

import java.util.Map;

/**
 * This code has been created by
 * gatogamer#1111 A.K.A gatogamer.
 * If you want to use my code, please
 * don't remove this message, and give
 * me the credits. Arigato! n.n
 */

public class Entry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        this.value = value;
        return value;
    }
}