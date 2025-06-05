package entite.equipement.arme.courante;

import utils.TypeArme;
import entite.equipement.arme.Arme;

public abstract class ArmeCourante extends Arme {
    public ArmeCourante(String name, int nbDe, int nbFacesDe) {
        super(name, TypeArme.COURANTE, nbDe, nbFacesDe, 1);
    }
}
