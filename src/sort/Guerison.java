package sort;

import de.De;
import entite.personnages.Personnage;

public class Guerison implements Sort {


    public String getNom() {
        return "Guérison";
    }


    public String getDescription() {
        return "Lancer 1d10 pour soigner un équipier.";
    }

    public int lancer(Personnage perso) {
        int soin = new De(1, 10).roll();
        perso.seSoigner(soin);
        return soin;
    }
}
