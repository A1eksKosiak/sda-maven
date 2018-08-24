package week8;

import java.util.Scanner;


public class DiagonalDifference {


    // Complete the diagonalDifference function below.
    public static int diagonalDifference(int[][] arr) {

        int firstDiagonalSum = 0;
        int secondDiagonalSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != arr.length) {
                return 0;
            }
            for (int j = 0; j < arr[i].length; j++) {
                if (i == j) {
                    firstDiagonalSum += arr[i][j];
                }
                if (j == arr[i].length - i - 1) {
                    secondDiagonalSum += arr[i][j];
                }
            }
        }
        return Math.abs(firstDiagonalSum - secondDiagonalSum);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < n; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = diagonalDifference(arr);

        System.out.println(result);

        scanner.close();
    }
}
