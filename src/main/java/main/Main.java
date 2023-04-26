package main;

import java.util.HashSet;
import java.util.Random;

public class Main {
    public static final int num = 100_000;
    static Random random = new Random();
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private static void run() {
        MyHashTable<Integer> myHashTable = new MyHashTable();
        System.out.println("Моя таблица| Времени затрачено: " + countTimeMyHash(myHashTable)+"ms");
        HashSet<Integer> StdHashTable = new HashSet<>();
        System.out.println("Std таблица| Времени затрачено: " + countTimeStdHash(StdHashTable)+"ms");
    }

    private static long countTimeMyHash(MyHashTable<Integer> hashTable) {
        long start = System.currentTimeMillis();
        fillMyHash(hashTable);
        long finish = System.currentTimeMillis();
        return finish - start;
    }
    private static void fillMyHash(MyHashTable<Integer> hash) {
        for (int i = 0; i <= num; i++) {
            hash.add(random.nextInt(1, num));
        }
    }


    private static long countTimeStdHash(HashSet<Integer> hashTable) {
        long start = System.currentTimeMillis();
        fillStdHash(hashTable);
        long finish = System.currentTimeMillis();
        return finish - start;
    }
    private static void fillStdHash(HashSet<Integer> hash) {
        for (int i = 0; i <= num; i++) {
            hash.add(random.nextInt(1, num));
        }
    }

}