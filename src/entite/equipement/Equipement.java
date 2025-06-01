package entite.equipement;

import entite.Entite;
import statistiques.Position;

public abstract class Equipement implements Entite {
    private final String _name;
    private final int _speedMalus;
    private final int _forceBonus;
    private final Position _pos;

    public Equipement(String name) {
        this(name, 0, 0);
    }

    public Equipement(String name, int speedMalus) {
        this(name, speedMalus, 0);
    }

    public Equipement(String name, int speedMalus, int forceBonus) {
        this._name = name;
        this._speedMalus = speedMalus;
        this._forceBonus = forceBonus;
        _pos = new Position();
    }

    public String getName() {
        return this._name;
    }

    public int getSpeedMalus() {
        return this._speedMalus;
    }

    public int getForceBonus() {
        return this._forceBonus;
    }

    public void position(int pos1, int pos2)
    {
        _pos.changPos(pos1, pos2);
    }

    public String aff()
    {
        return " * ";
    }
}
