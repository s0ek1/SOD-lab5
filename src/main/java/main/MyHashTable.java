package main;

import java.util.Arrays;
import java.util.LinkedList;

public class MyHashTable<T> {
    private static final double LOAD_FACTOR = 0.75;

    private class Bucket<T> {
        private LinkedList<T> data = new LinkedList<>();

        public boolean add(T value) {
            if(data.contains(value)) return false;
            else return data.add(value);
        }

        public boolean contains(T value) {
            return data.contains(value);
        }
    }

    private Bucket<T>[] buckets;
    private int size;

    public MyHashTable() {
        buckets = new Bucket[8];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket<>();
        }
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean contains(T value) {
        return Arrays.stream(buckets).anyMatch(b -> b.contains(value));
    }

    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }
        ensureCapacity(size + 1);
        int pos = value.hashCode() % buckets.length;
        size++;
        return buckets[pos].add(value);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Bucket<T> bucket : buckets) {
            for (T t : bucket.data) {
                sb.append(t).append(", ");
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        return "[" + sb.toString() + "]";
    }

    private void ensureCapacity(int newSize) {
        if ((double) newSize / buckets.length > LOAD_FACTOR) {
            increaseSize();
        }
    }

    private void increaseSize() {
        Bucket<T>[] newArr = Arrays.copyOf(buckets, buckets.length * 2);
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = new Bucket<>();
        }
        for (Bucket<T> bucket : buckets) {
            for (T t : bucket.data) {
                int p = t.hashCode() % newArr.length;
                newArr[p].data.add(t);
            }
        }
        buckets = newArr;
    }
}
