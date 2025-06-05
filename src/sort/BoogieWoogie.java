package sort;

import utils.Inputs;
import donjon.Donjon;
import entite.Monstre;
import entite.personnages.Personnage;

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

    public void lancer(Personnage perso1, Personnage perso2, Donjon DJ) {
        System.out.println("Positions avant changement :\nperso1 : " + perso1.getPos()[0] + " " + perso1.getPos()[1] + "\nperso2 : "  + perso2.getPos()[0] + " " + perso2.getPos()[1]);
        int[] posTemp = perso1.getPos().clone();
        DJ.emptyCase(perso1.getPos());
        DJ.emptyCase(perso2.getPos());
        DJ.positionVivant(_input.positionCase(ALPHABET[perso2.getPos()[1]-1] + perso2.getPos()[0]), perso1);
        DJ.positionVivant(_input.positionCase(ALPHABET[posTemp[1]-1] + posTemp[0]), perso2);
        System.out.println("Positions apres changement :\nperso1 : " + perso1.getPos()[0] + " " + perso1.getPos()[1] + "\nperso2 : "  + perso2.getPos()[0] + " " + perso2.getPos()[1]);
    }

    public void lancer(Monstre monstre1, Monstre monstre2, Donjon DJ) {
        System.out.println("Positions avant changement :\nmonstre1 : " + monstre1.getPos()[0] + " " + monstre1.getPos()[1] + "\nmonstre2 : "  + monstre2.getPos()[0] + " " + monstre2.getPos()[1]);
        int[] posTemp = monstre1.getPos().clone();
        DJ.emptyCase(monstre1.getPos());
        DJ.emptyCase(monstre2.getPos());
        DJ.positionVivant(_input.positionCase(ALPHABET[monstre2.getPos()[1]-1] + monstre2.getPos()[0]), monstre1);
        DJ.positionVivant(_input.positionCase(ALPHABET[posTemp[1]-1] + posTemp[0]), monstre2);
        System.out.println("Positions apres changement :\nmonstre1 : " + monstre1.getPos()[0] + " " + monstre1.getPos()[1] + "\nmobstre2 : "  + monstre2.getPos()[0] + " " + monstre2.getPos()[1]);

    }

    public void lancer(Personnage perso, Monstre monstre, Donjon DJ) {
        System.out.println("Positions avant changement :\nperso : " + perso.getPos()[0] + " " + perso.getPos()[1] + "\nmonstre : "  + monstre.getPos()[0] + " " + monstre.getPos()[1]);
        int[] posTemp = perso.getPos().clone();
        DJ.emptyCase(perso.getPos());
        DJ.emptyCase(monstre.getPos());
        DJ.positionVivant(_input.positionCase(ALPHABET[monstre.getPos()[1]-1] + monstre.getPos()[0]), perso);
        DJ.positionVivant(_input.positionCase(ALPHABET[posTemp[1]-1] + posTemp[0]), monstre);
        System.out.println("Positions apres changement :\nperso : " + perso.getPos()[0] + " " + perso.getPos()[1] + "\nmonstre : "  + monstre.getPos()[0] + " " + monstre.getPos()[1]);

    }

    public void lancer(Monstre monstre, Personnage perso, Donjon DJ) {
        lancer(perso, monstre, DJ);
    }
}
