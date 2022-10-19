package test.fourth;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> fibonacciNumbers = calculateFibonacciSequence();
        for (Integer i : fibonacciNumbers) {
            System.out.println(i);
        }
    }

    public static ArrayList<Integer> calculateFibonacciSequence() {
        ArrayList<Integer> fibonacciNumbers = new ArrayList<>();
        int current = 0;
        int prev = -1;
        while (current >= 0) {
            prev = current - prev;
            current += prev;
            fibonacciNumbers.add(current);
        }
        fibonacciNumbers.remove(fibonacciNumbers.size() - 1);
        return fibonacciNumbers;
    }
}
