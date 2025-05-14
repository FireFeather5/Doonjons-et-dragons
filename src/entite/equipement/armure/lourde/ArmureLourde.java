package entite.equipement.armure.lourde;

import entite.equipement.armure.Armure;

public abstract class ArmureLourde extends Armure {
    public ArmureLourde(String name, int armorClass) {
        super(name, armorClass);
    }

    public int getSpeedMalus() {
        return 4;
    }
}
