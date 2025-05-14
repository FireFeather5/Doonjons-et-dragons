package entite.equipement.arme.guerre;

import de.De;
import entite.equipement.arme.Arme;

public abstract class ArmeGuerre extends Arme {
    public ArmeGuerre(String name, De degats) {
        super(name, degats, 1);
    }

    public int getSpeedMalus() {
        return 2;
    }

    public int getForceBonus() {
        return 4;
    }
}
