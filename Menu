public class Menu {

    private Client createClient(int fractionCode, boolean artificialIntelligence, int difficultyLevel) {
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
        return client;
    }

}
