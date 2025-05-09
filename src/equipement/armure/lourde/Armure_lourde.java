package equipement.armure.lourde;

import equipement.armure.Armure;

public abstract class Armure_lourde extends Armure {
    public Armure_lourde(int armor_class, String name) {
        super(armor_class, name);
    }

    public int get_speed_malus() {
        return 4;
    }
}
