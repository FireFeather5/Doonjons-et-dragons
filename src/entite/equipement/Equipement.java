package entite.equipement;

public abstract class Equipement {
    private final String _name;
    private final int _speedMalus;
    private final int _forceBonus;

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
}
