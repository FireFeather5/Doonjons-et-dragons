package sort;

import entite.equipement.arme.Arme;

public class ArmeMagique implements Sort {

    @Override
    public String getNom() {
        return "Arme magique";
    }

    @Override
    public String getDescription() {
        return "Améliore l'arme en lui donnant un bonus de 1 de touche et de dégat (cumulable).";
    }

    public void lancer(Arme arme) {
        arme.bonusMagique();
    }
}
