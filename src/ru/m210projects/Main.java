package ru.m210projects;

public class Main {

    public static void main(String[] args) {
        IntArrayList list = new IntArrayList(generateRandomArray(100_000));

        System.out.println(list.contains(list.get(12)));
    }

    public static Integer[] generateRandomArray(int size) {
        java.util.Random random = new java.util.Random();
        Integer[] arr = new Integer[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000) + 100_000;
        }
        return arr;
    }
}
