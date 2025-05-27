package entite.equipement.arme;

import entite.equipement.Equipement;

public abstract class Arme extends Equipement {

    private final int _nbDe;
    private final int _nbFacesDe;
    private final int _range;

    private int _bonusMagique = 0;

    public Arme(String name, int nbDe, int nbFacesDe, int range) {
        this(name, nbDe, nbFacesDe, range, 0, 0);
    }

    public Arme(String name, int nbDe, int nbFacesDe, int range, int speedMalus, int forceBonus) {
        super(name, speedMalus, forceBonus);
        this._nbDe = nbDe;
        this._nbFacesDe = nbFacesDe;
        this._range = range;
    }

    public int[] getDegats() {
        return new int[]{_nbDe, _nbFacesDe};
    }

    public void bonusMagique() {
        _bonusMagique++;
    }

    public int getBonusMagique() {
        return this._bonusMagique;
    }

    public int getRange() {
        return _range;
    }

    @Override
    public String toString() {
        return this.getName() + " [dégats : " + this._nbDe + "d" + this._nbFacesDe + ", portée : " + this._range + ", bonus magique : " + this._bonusMagique + "] ";
    }
}
