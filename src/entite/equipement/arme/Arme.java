package entite.equipement.arme;

import de.De;
import entite.equipement.Equipement;

public abstract class Arme extends Equipement {

    private final De _degats;
    private final int _range;

    public Arme(String name, De degats, int range) {
        _name = name;
        _degats = degats;
        _range = range;
    }

    public De getDegats() {
        return _degats;
    }

    public int getRange() {
        return _range;
    }

    @Override
    public String toString() {
        return this._name + " [dégats : " + this._degats + ", portée : " + this._range + "] ";
    }
}
