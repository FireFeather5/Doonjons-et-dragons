package sort;

import entite.Entite;
import entite.Monstre;
import entite.personnages.Personnage;

public class BoogieWoogie implements Sort {

    @Override
    public String getNom() {
        return "Boogie Woogie";
    }

    @Override
    public String getDescription() {
        return "Choisissez une combinaison de 2 personnages et/ou monstres et echanger leur position.";
    }

    public void lancer(Personnage perso1, Personnage perso2) {
        int[] posTemp = perso1.getPos();
        perso1.position(perso2.getPos()[0], perso2.getPos()[1]);
        perso2.position(posTemp[0], posTemp[1]);
    }

    public void lancer(Monstre monstre1, Monstre monstre2) {
        int[] posTemp = monstre1.getPos();
        monstre1.position(monstre2.getPos()[0], monstre2.getPos()[1]);
        monstre2.position(posTemp[0], posTemp[1]);
    }

    public void lancer(Personnage perso, Monstre monstre) {
        int[] posTemp = perso.getPos();
        perso.position(monstre.getPos()[0], monstre.getPos()[1]);
        monstre.position(posTemp[0], posTemp[1]);
    }

    public void lancer(Monstre monstre, Personnage perso) {
        int[] posTemp = perso.getPos();
        perso.position(monstre.getPos()[0], monstre.getPos()[1]);
        monstre.position(posTemp[0], posTemp[1]);
    }
}
