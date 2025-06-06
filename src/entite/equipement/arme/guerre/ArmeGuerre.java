package entite.equipement.arme.guerre;

import utils.TypeArme;
import entite.equipement.arme.Arme;

public abstract class ArmeGuerre extends Arme {
    public ArmeGuerre(String name, int nbDe, int nbFacesDe) {
        super(name, TypeArme.GUERRE, nbDe, nbFacesDe, 1, 2, 4);
    }
}
