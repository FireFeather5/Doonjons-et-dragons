package equipement.arme.guerre;

import equipement.arme.Arme;

public abstract class ArmeGuerre extends Arme {
    public ArmeGuerre(String name, int nbDe, int nbFacesDe) {
        super(name, nbDe, nbFacesDe, 1, 2, 4);
    }
}
