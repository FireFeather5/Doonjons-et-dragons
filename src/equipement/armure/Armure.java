package equipement.armure;

import equipement.Equipement;

public abstract class Armure extends Equipement {
    private final int armorClass;

    public Armure(int armor_class, String name) {
        this.armorClass = armor_class;
        this._name = name;
    }

    public int getArmorClass() {
        return this.armorClass;
    }

    @Override
    public String toString() {
        return this._name + ", classe d'armure : " + this.armorClass;
    }
}
