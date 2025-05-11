package equipement.armure.lourde;

import equipement.armure.Armure;

public abstract class ArmureLourde extends Armure {
    public ArmureLourde(int armor_class, String name) {
        super(armor_class, name);
    }

    public int getSpeedMalus() {
        return 4;
    }
}
