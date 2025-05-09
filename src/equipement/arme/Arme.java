package equipement.arme;

import de.De;

public abstract class Arme {

    private String _name;
    private De _degats;
    private int _range;

    public Arme(String name, De degats, int range) {
        _name = name;
        _degats = degats;
        _range = range;
    }

    public String getName() {
        return _name;
    };

    public De getDegats() {
        return _degats;
    };

    public int getRange() {
        return _range;
    };
}
