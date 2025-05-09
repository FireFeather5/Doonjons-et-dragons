package equipement.arme.courante;

import de.De;
import equipement.arme.Arme;

public abstract class Courante extends Arme {
    public Courante(String name, De degats) {
        super(name, degats, 1);
    }
}
