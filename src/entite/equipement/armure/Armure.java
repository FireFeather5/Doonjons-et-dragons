package entite.equipement.armure;

import Utils.TypeArmure;
import Utils.TypeEquipement;
import entite.equipement.Equipement;

public abstract class Armure extends Equipement {
    private final int armorClass;

    public Armure(String name, TypeArmure typeArmur, int armorClass) {
        super(name, TypeEquipement.ARMURE, null, typeArmur);
        this.armorClass = armorClass;
    }

    public Armure(String name, TypeArmure typeArmur, int armorClass, int speedMalus) {
        super(name, TypeEquipement.ARMURE, null, typeArmur, speedMalus);
        this.armorClass = armorClass;
    }

    public int getArmorClass() {
        return this.armorClass;
    }

    @Override
    public String toString() {
        return this.getName() + " [classe d'armure : " + this.armorClass + "]";
    }
}
