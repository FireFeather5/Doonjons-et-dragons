package entite.equipement.arme.distance;

import de.De;
import entite.equipement.arme.Arme;

public abstract class ArmeDistance extends Arme {
    public ArmeDistance(String nom, De degats, int portee) {
        super(nom, degats, portee);
    }
}
