public class ArtificialIntelligence extends Client {

    private Strategy strategy;

    public ArtificialIntelligence(Factory factory) {
        super(factory);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    
    public boolean giveOrder() {
        return strategy.giveOrder(this);
    }
}
