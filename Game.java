import java.util.LinkedList;

class Game {

    void run() {

        LinkedList<Client> clients = new LinkedList<>();
        Menu menu = new Menu();

        for (Client client : menu.run()) {
            clients.addLast(client);
        }

        clients.get(0).setClientInterface();
        boolean clientCapitulate = false, botsCapitulate = false;

        while (!clientCapitulate && !botsCapitulate) {
            clientCapitulate = clients.get(0).giveOrder();
            botsCapitulate = clients.get(1).giveOrder();
        }
        ClientInterface.showGameResult(clientCapitulate, botsCapitulate);
    }
}
