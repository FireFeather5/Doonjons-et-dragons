package sort;

import entite.equipement.arme.Arme;

public class ArmeMagique implements Sort {


    public String getNom() {
        return "Arme magique";
    }


    public String getDescription() {
        return "Améliore l'arme en lui donnant un bonus de 1 de touche et de dégat (cumulable).";
    }

    public void lancer(Arme arme) {
        arme.bonusMagique();
    }
}
