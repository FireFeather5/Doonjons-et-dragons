package equipement.arme.distance;

import de.De;
import equipement.arme.Arme;

public abstract class Distance extends Arme {
    public Distance(String nom, De degats, int portee) {
        super(nom, degats, portee);
    }
}
