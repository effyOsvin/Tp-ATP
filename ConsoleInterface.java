import java.io.IOException;
import java.util.Scanner;

class ConsoleInterface {

    private static Scanner in = new Scanner(System.in);

    static void inputMismatchWarning() {
        System.out.println("Please, try again!");
    }

    static int getInput() {
        int response;
        try {
            System.in.reset();
        } catch (IOException ignored) {
        }
        System.out.print("[INPUT]: ");
        String input = ConsoleInterface.in.next();
        try {
            response = Integer.valueOf(input);
        } catch (Exception e) {
            return -1;
        }
        return response;
    }
}
