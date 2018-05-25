public class Menu {

    public Menu() {}

    private Client createClient(int fractionCode, boolean artificialIntelligence) {
        ArtificialIntelligence client;
        switch (fractionCode) {
            case 1:
                client = new ArtificialIntelligence(new FairyFactory());
                break;
            case 2:
                client = new ArtificialIntelligence(new MermaidFactory());
                break;
            case 3:
                client = new ArtificialIntelligence(new WitchFactory());
                break;
            default:
                return null;
        }
        client.setStrategy(new Strategy());
        return client;
    }

    public Client[] run(){

        System.out.println("Hello, my friend!\n" +
                "We have a war now.\n" +
                "Forces are equal..\n" +
                "You are our last reliable.\n" +
                "Command the army and win.");

        boolean requestSuccess = false;

        System.out.println("Choose your race and race your enemy:\n" +
                "> type '1' for Fairy\n" +
                "> type '2' for Witch\n" +
                "> type '3' for Mermaid");

        Client[] clients = new Client[2];
        for (int i = 0; i < 2; i++) {
            requestSuccess = false;
            int fractionCode;
            do {
                fractionCode = ConsoleInterface.getInput();
                if (fractionCode > 0 && fractionCode < 4) {
                    requestSuccess = true;
                } else {
                    ConsoleInterface.inputMismatchWarning();
                }
            } while (!requestSuccess);
            clients[i] = createClient(fractionCode, i != 0);
        }

        return clients;
    }
}
