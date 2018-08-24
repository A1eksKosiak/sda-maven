package week8;

import java.io.IOException;
import java.util.Scanner;

public class SparseArrays {
    // Complete the matchingStrings function below.
    static int[] matchingStrings(String[] strings, String[] queries) {

        int[] array = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            for (int j = 0; j < strings.length; j++) {
                try {
                    if (strings[j].equals(queries[i])) {
                        array[i]++;
                    }
                } catch (NullPointerException ignore) {
                    // ignoring on purpose
                }
            }
        }
        return array;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int stringsCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] strings = new String[stringsCount];

        for (int i = 0; i < stringsCount; i++) {
            String stringsItem = scanner.nextLine();
            strings[i] = stringsItem;
        }

        int queriesCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] queries = new String[queriesCount];

        for (int i = 0; i < queriesCount; i++) {
            String queriesItem = scanner.nextLine();
            queries[i] = queriesItem;
        }

        int[] res = matchingStrings(strings, queries);

        for (int i = 0; i < res.length; i++) {
            System.out.println(String.valueOf(res[i]));

            if (i != res.length - 1) {
                System.out.println();
            }
        }
        scanner.close();
    }
}
