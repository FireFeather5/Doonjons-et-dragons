package sort;

import donjon.Donjon;
import entite.Monstre;
import entite.personnages.Personnage;

public class BoogieWoogie implements Sort {

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
        int[] posTemp = perso1.getPos();
        DJ.posJ(ALPHABET[perso2.getPos()[0]-1] + perso2.getPos()[1], perso1);
        DJ.posJ(ALPHABET[posTemp[0]-1] + posTemp[1], perso2);
    }

    public void lancer(Monstre monstre1, Monstre monstre2, Donjon DJ) {
        int[] posTemp = monstre1.getPos();
        DJ.posM(ALPHABET[monstre2.getPos()[0]-1] + monstre2.getPos()[1], monstre1);
        DJ.posM(ALPHABET[posTemp[0]-1] + posTemp[1], monstre2);
    }

    public void lancer(Personnage perso, Monstre monstre, Donjon DJ) {
        int[] posTemp = perso.getPos();
        DJ.posM(ALPHABET[perso.getPos()[0]-1] + perso.getPos()[1], monstre);
        DJ.posJ(ALPHABET[posTemp[0]-1] + posTemp[1], perso);
    }

    public void lancer(Monstre monstre, Personnage perso, Donjon DJ) {
        int[] posTemp = perso.getPos();
        DJ.posM(ALPHABET[perso.getPos()[0]-1] + perso.getPos()[1], monstre);
        DJ.posJ(ALPHABET[posTemp[0]-1] + posTemp[1], perso);
    }
}
