package entite.equipement.arme.courante;

import de.De;
import entite.equipement.arme.Arme;

public abstract class ArmeCourante extends Arme {
    public ArmeCourante(String name, De degats) {
        super(name, degats, 1);
    }
}
