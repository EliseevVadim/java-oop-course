package test.fifth;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число: ");
        try {
            int input = in.nextInt();
            int output = factorial(input);
            System.out.println(output);
        }
        catch (Throwable t) {
            System.out.println("Это не валидное число");
        }
    }

    private static int factorial(int number) {
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }
}
