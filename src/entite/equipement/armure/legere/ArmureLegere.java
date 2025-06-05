package entite.equipement.armure.legere;

import utils.TypeArmure;
import entite.equipement.armure.Armure;

public abstract class ArmureLegere extends Armure {
    public ArmureLegere(String name, int armorClass) {
        super(name, TypeArmure.LEGERE, armorClass);
    }
}
