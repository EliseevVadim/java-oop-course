package test.sixth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите максимальное число, до которого нужно вычислить простые: ");
        try {
            int highBound = in.nextInt();
            ArrayList<Integer> simpleNumbers = calculateSimpleNumbersByEratosthenes(highBound);
            for (Integer i : simpleNumbers) {
                System.out.println(i);
            }
        }
        catch (Throwable t) {
            System.out.println("Это не валидное число");
        }
    }

    public static ArrayList<Integer> calculateSimpleNumbersByEratosthenes(int highBound) {
        ArrayList<Integer> simpleNumbers = new ArrayList<>();
        boolean[] isPrimes = new boolean[highBound + 1];
        Arrays.fill(isPrimes, true);
        isPrimes[0] = false;
        isPrimes[1] = false;
        for (int i = 2; i < isPrimes.length; i++) {
            if (isPrimes[i]) {
                for (int j = 2; i * j < isPrimes.length; j++) {
                    isPrimes[i * j] = false;
                }
            }
        }
        for (int i = 0; i < isPrimes.length; i++) {
            if (isPrimes[i])
                simpleNumbers.add(i);
        }
        return simpleNumbers;
    }
}
