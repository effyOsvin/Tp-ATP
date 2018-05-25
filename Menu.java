public class Menu {

    public Menu() {}

    private Client createClient(int fractionCode) {
        Client client;
        switch (fractionCode) {
            case 1:
                client = new Client(new FairyFactory());
                break;
            case 2:
                client = new Client(new MermaidFactory());
                break;
            case 3:
                client = new Client(new WitchFactory());
                break;
            default:
                return null;
        }
        return client;
    }

    public void run() {

        System.out.println("Hello, my friend!\n" +
                "We have a war now.\n" +
                "Forces are equal..\n" +
                "You are our last reliable.\n" +
                "Command the army and win.");

        System.out.println("Choose your race and race your enemy:\n" +
                "> type '1' for Fairy\n" +
                "> type '2' for Witch\n" +
                "> type '3' for Mermaid");
    }
    public Client getClient(){
        Client client;
        boolean requestSuccess = false;

        int fractionCode;
        do {
            fractionCode = ConsoleInterface.getInput();
            if (fractionCode > 0 && fractionCode < 4) {
                requestSuccess = true;
            } else {
                ConsoleInterface.inputMismatchWarning();
            }
        } while (!requestSuccess);
        client = createClient(fractionCode);

        return client;
    }
}
