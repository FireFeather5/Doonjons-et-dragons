package equipement.arme.courante;

import de.De;
import equipement.arme.Arme;

public abstract class ArmeCourante extends Arme {
    public ArmeCourante(String name, De degats) {
        super(name, degats, 1);
    }
}
