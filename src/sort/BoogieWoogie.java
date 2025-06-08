package sort;

import entite.Vivant;
import utils.Inputs;
import donjon.Donjon;

public class BoogieWoogie implements Sort {


    public String getNom() {
        return "Boogie Woogie";
    }


    public String getDescription() {
        return "Choisissez une combinaison de 2 personnages et/ou monstres et echanger leur position.";
    }

    public void lancer(Vivant viv1, Vivant viv2, Donjon DJ) {
        DJ.switchCase(viv1, viv2);
    }
}
