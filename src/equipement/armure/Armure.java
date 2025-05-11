package equipement.armure;

import equipement.Equipement;

public abstract class Armure extends Equipement {
    private final int armorClass;

    public Armure(String name, int armorClass) {
        this.armorClass = armorClass;
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
