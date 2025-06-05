package entite.equipement.armure.lourde;

import utils.TypeArmure;
import entite.equipement.armure.Armure;

public abstract class ArmureLourde extends Armure {
    public ArmureLourde(String name, int armorClass) {
        super(name, TypeArmure.LOURDE, armorClass, 4);
    }
}
