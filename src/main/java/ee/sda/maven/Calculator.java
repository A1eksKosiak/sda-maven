package ee.sda.maven;

public class Calculator {

    public int sum() {
        return 0;
    }

    public int sum(String input) {
        if (input == null) {
            return 0;
        }
        String[] array = input.split("\\+");
        int sum = 0;
        for (String arr : array) {
            try {
                sum += Integer.parseInt(arr);
            } catch (NumberFormatException ignored) {
                //ignored on purpose
            }
        }
        return sum;
    }

    public boolean sum(boolean a, boolean b) {
        return false;
    }
}
