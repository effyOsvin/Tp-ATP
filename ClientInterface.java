import java.util.List;
import java.util.Scanner;

public class ClientInterface {

    private boolean invitated = false;

    private Client client;
    private Scanner in;

    public ClientInterface(Client client) {
        this.client = client;
        in = new Scanner(System.in);
    }

    public boolean run() {
        if (!invitated) {
            ConsoleInterface.help();
            invitated = true;
        }
        int commandCode = 0;
        do {
            System.out.flush();
            System.out.print("$> ");
            String command = this.in.next().toLowerCase();
            switch (command) {
                case "/my_info":
                    showForcesInfo(client.getUnits(true));
                    break;
                case "/enemies_info":
                    showForcesInfo(client.getUnits(false));
                    break;
                case "/set_unit":
                    commandCode = setUnit();
                    break;
                case "/help":
                    ConsoleInterface.help();
                    break;
                case "/capitulation":
                    if (capitulate()) {
                        System.out.println("The militia forces took command of themselves.");
                        commandCode = -1;
                    }
                    break;
                default:
                    ConsoleInterface.inputMismatchWarning();
                    break;
            }
        } while (commandCode == 0);
        return commandCode == -1;
    }

    private int setUnit() {
        int x, y;
        x = ConsoleInterface.getInput();
        y = ConsoleInterface.getInput();
        if (x == -1 || y == -1 || !client.properUnit(x, y, true)) {
            System.out.println("Invalid coordinates or it's not ally force!");
            return 0;
        }
        int commandCode = 0;
        do {
            System.out.flush();
            System.out.print("$>> ");
            String command = this.in.next().toLowerCase();
            switch (command) {
                case "/cancel":
                    return 0;
                case "/attack":
                    int enemieX, enemieY;
                    enemieX = ConsoleInterface.getInput();
                    enemieY = ConsoleInterface.getInput();
                    if (enemieX == -1 || enemieY == -1 || !client.properUnit(enemieX, enemieY, false)) {
                        System.out.println("Invalid coordinates or it's not enemy force!");
                    } else {
                        if (client.makeAttack(x, y, enemieX, enemieY)) {
                            System.out.println("Successful attack!");
                        } else {
                            System.out.println("Unsuccessful attack!");
                        }
                    }
                    commandCode = 1;
                    break;
                case "/march_up":
                    commandCode = 1;
                    if (client.makeMarch(x, y, 0, 1)) {
                        System.out.println("Can't make move!");
                    }
                    break;
                case "/march_down":
                    commandCode = 1;
                    if (client.makeMarch(x, y, 0, -1)) {
                        System.out.println("Can't make move!");
                    }
                    break;
                case "/march_right":
                    commandCode = 1;
                    if (client.makeMarch(x, y, 1, 0)) {
                        System.out.println("Can't make move!");
                    }
                    break;
                case "/march_left":
                    commandCode = 1;
                    if (client.makeMarch(x, y, -1, 0)) {
                        System.out.println("Can't make move!");
                    }
                    break;
                case "/help":
                    ConsoleInterface.help();
                    break;
                case "/capitulation":
                    if (capitulate()) {
                        System.out.println("The militia forces took command of themselves.");
                        commandCode = -1;
                    }
                    break;
                default:
                    ConsoleInterface.inputMismatchWarning();
                    break;
            }
        } while (commandCode == 0);
        return commandCode;
    }

    private boolean capitulate() {
        int capitulationCode = -1;
        System.out.println("My General, do you really want to capitulate: y[es]/n[o]?");
        do {
            String response = in.next().toLowerCase();
            if (response.equals("y") || response.equals("yes")) {
                capitulationCode = 1;
            } else if (response.equals("n") || response.equals("no")) {
                capitulationCode = 0;
            }
            if (capitulationCode == -1) {
                ConsoleInterface.inputMismatchWarning();
            }
        } while (capitulationCode == -1);
        return capitulationCode == 1;
    }

    private void showForcesInfo(List<Unit> units) {
        for (Unit unit : units) {
            String unitType = unit.getUnitForcee();
            String unitFraction = unit.getUnitRace();
            System.out.println("Race " + unitFraction + " " + unitType +
                    "\n\t # health: " + unit.getHealth() +
                    "\n\t # attack range: " + unit.getAttackRange() +
                    "\n\t # coords:" + "(" + unit.getCoords().getKey() + "; " + unit.getCoords().getValue() + ")"
            );
        }
    }

    public static void showGameResult(boolean clientCapitulate, boolean botsCapitulate) {
        if (botsCapitulate && clientCapitulate) {
            System.out.println("Draw!");
        } else if (botsCapitulate) {
            System.out.println("Victory!");
        } else {
            System.out.println("Defeat!");
        }
    }
}