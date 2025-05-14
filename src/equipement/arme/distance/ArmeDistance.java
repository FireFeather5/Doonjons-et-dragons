package equipement.arme.distance;

import equipement.arme.Arme;

public abstract class ArmeDistance extends Arme {
    public ArmeDistance(String nom, int nbDe, int nbFacesDe, int portee) {
        super(nom, nbDe, nbFacesDe, portee);
    }
}
