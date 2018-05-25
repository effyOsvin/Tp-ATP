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
                "\n\t> /allies_forces_info - gives all information about your alive forces" +
                "\n\t> /enemies_forces_info - gives all information about enemies' alive forces" +
                "\n\t> /create_[:force_name:] - creates units, with one of following types:" +
                "\n\t\t :air_force:" +
                "\n\t\t :ground_force:" +
                "\n\t\t :water_force:" +
                "\n\t> /set_unit [:x: :y:] - set command for unit" +
                "\n\t\t :x: - 'x' coordinate (integer)" +
                "\n\t\t :y: - 'y' coordinate (integer)" +
                "\n\t\t after than available commands:" +
                "\n\t\t /cancel" +
                "\n\t\t /attack [:x: :y:]" +
                "\n\t\t /march_up - moves unit up" +
                "\n\t\t /march_down - moves unit down" +
                "\n\t\t /march_right - moves unit right" +
                "\n\t\t /march_left - moves unit left" +
                "\n\t\t /update_[:wrapper:] - improve unit" +
                "\n\t\t\t :attack:" +
                "\n\t\t\t :protection:" +
                "\n\t> /create_squad - make squad with allies" +
                "\n\t\t after than available commands:" +
                "\n\t\t /set_unit [:x: :y:] - join certain unit to squad" +
                "\n\t\t /done - complete creating squad" +
                "\n\t\t /cancel" +
                "\n\t> /set_squad [:x: :y:] - set command for squad" +
                "\n\t\t after than shows list of own squads, next - available commands:" +
                "\n\t\t /cancel" +
                "\n\t\t /drop_squad [:squad_number:] - disband squad" +
                "\n\t\t /attack [:x: :y:]" +
                "\n\t\t /march [:x: :y:]"
        );
    }
}
