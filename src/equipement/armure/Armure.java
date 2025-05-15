package equipement.armure;

import equipement.Equipement;

public abstract class Armure extends Equipement {
    private final int armorClass;

    public Armure(String name, int armorClass) {
        super(name);
        this.armorClass = armorClass;
    }

    public Armure(String name, int armorClass, int speedMalus) {
        super(name, speedMalus);
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
