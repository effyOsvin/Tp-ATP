class Game {

    void run() {

        //GameWorld gameWorld = GameWorld.getGameWorld();
        //GameObserver gameObserver = GameObserver.getGameObserver();
        Client client;
        Client bot;
        Menu menu = new Menu();

        menu.run();
        client = menu.getClient();
        bot = menu.getClient();

        client.setClientInterface();
        bot.setClientInterface();

        Integer count = client.countUnits();

        client.start(count);
        bot.start(count);
        boolean clientCapitulate = false, botsCapitulate = false;

        while (!clientCapitulate && !botsCapitulate) {
            clientCapitulate = client.giveOrder();
            botsCapitulate = client.giveOrderBot();
        }
        ClientInterface.showGameResult(clientCapitulate, botsCapitulate);
    }
}