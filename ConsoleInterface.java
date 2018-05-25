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

    static void help() {
        System.out.println("Possible commands:" +
                "\n\t> /help - shows this hint" +
                "\n\t> /capitulation - leave this war" +
                "\n\t> /my_info - gives all information about your alive forces" +
                "\n\t> /enemies_info - gives all information about enemies' alive forces" +
                "\n\t> /set_unit [:x: :y:] - set command for unit" +
                "\n\t\t :x: - 'x' coordinate (integer)" +
                "\n\t\t :y: - 'y' coordinate (integer)" +
                "\n\t\t after than available commands:" +
                "\n\t\t /cancel" +
                "\n\t\t /attack [:x: :y:]" +
                "\n\t\t /march_up - moves unit up" +
                "\n\t\t /march_down - moves unit down" +
                "\n\t\t /march_right - moves unit right" +
                "\n\t\t /march_left - moves unit left"
        );
    }
}