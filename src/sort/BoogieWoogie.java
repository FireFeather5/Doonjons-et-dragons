package sort;

import entite.Vivant;
import utils.Inputs;
import donjon.Donjon;

public class BoogieWoogie implements Sort {

    private final Inputs _input = new Inputs();

    static private final String[] ALPHABET = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    @Override
    public String getNom() {
        return "Boogie Woogie";
    }

    @Override
    public String getDescription() {
        return "Choisissez une combinaison de 2 personnages et/ou monstres et echanger leur position.";
    }

    public void lancer(Vivant etreVivant1, Vivant etreVivant2, Donjon DJ) {
        int [] posTemp = etreVivant1.getPos().clone();
        DJ.emptyCase(etreVivant1.getPos());
        DJ.emptyCase(etreVivant2.getPos());
        DJ.positionVivant(_input.positionCase(ALPHABET[etreVivant2.getPos()[1]-1] + etreVivant2.getPos()[0]), etreVivant1);
        DJ.positionVivant(_input.positionCase(ALPHABET[posTemp[1]-1] + posTemp[0]), etreVivant2);

    }
}
