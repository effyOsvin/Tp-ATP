import java.util.Comparator;

class UnitComporator implements Comparator<Unit> {
    @Override
    public int compare(Unit unit1, Unit unit2) {
        return Integer.compare(unit1.getHealth(), unit2.getHealth());
    }
}