package entite.equipement.arme.courante;

import entite.equipement.arme.Arme;

public abstract class ArmeCourante extends Arme {
    public ArmeCourante(String name, int nbDe, int nbFacesDe) {
        super(name, nbDe, nbFacesDe, 1);
    }
}
