package equipement.arme.guerre;

import de.De;
import equipement.arme.Arme;

public abstract class Guerre extends Arme {
    public Guerre(String name, De degats) {
        super(name, degats, 1);
    }
}
