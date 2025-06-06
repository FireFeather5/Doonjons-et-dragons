package utils;

import de.De;
import donjon.Donjon;
import entite.Obstacle;
import entite.equipement.Equipement;
import entite.Monstre;
import entite.personnages.Personnage;

import java.util.ArrayList;

public class MJ {

    private final ArrayList<String> _monstresCrees = new ArrayList<>();

    private final Affichage _affichage = new Affichage(SortieAffichage.CONSOLE);

    public MJ()
    {

    }

    public Donjon creationDonjon(int[] tailleDj)
    {
        Donjon DJ = new Donjon(tailleDj);
        _affichage.afficher(true, "Aperçu du donjon :");
        DJ.afficherDJ();

        /*_affichage.afficher(true, "\n\nLa taille vous convient-il (o/n) ?");
        if (sc.nextLine().equals("n"))
        {
            _input.creationDonjon(this);
        }*/

        return DJ;
    }

    public void addObst(Donjon DJ, int[] pos)
    {
        Obstacle obs = new Obstacle();
        boolean test = obs.addPos(pos, DJ);

        if (!test)
        {
            _affichage.afficherRouge(true, "Erreur dans la selection de la position");
            addObst(DJ, pos);
        }
    }

    public Monstre createM(String espece, String symb, int portee, De degAtt, De charac)
    {
        Monstre mons = new Monstre(espece, symb, portee, degAtt, charac);

        for (String monstre : this._monstresCrees) {
            if (monstre.equals(espece)) {
                mons.multiMonstre();
            }
        }
        this._monstresCrees.add(espece);

        return mons;
    }

    public boolean posJ(Donjon DJ, Personnage perso, int[] pos)
    {
        boolean test = DJ.positionVivant(pos, perso);

        if (!test)
        {
            _affichage.afficherRouge(true, "Erreur dans la selection de la position");
        }
        return test;
    }

    public void posM(Donjon DJ, Monstre mons, int[] pos)
    {
        boolean test = DJ.positionVivant(pos, mons);

        if (!test)
        {
            _affichage.afficherRouge(true, "Erreur dans la selection de la position");
            this.posM(DJ, mons, pos);
        }
    }

    public void posEquip(Donjon DJ, Equipement equip, int[] pos)
    {
        boolean test = DJ.positionEquipement(pos, equip);

        if (!test)
        {
            _affichage.afficherRouge(true, "Erreur dans la selection de la position");
            this.posEquip(DJ, equip, pos);
        }
    }

    public void presContext(String context)
    {
        _affichage.afficher(true, "MJ - ", context);
    }

    public void comAction(String commentaire)
    {
        _affichage.afficher(true, "MJ - ", commentaire);
    }


    //      A VOIR PLUS TARD

    public void depViv(Donjon DJ, int[] posD, int[] posF)
    {
        DJ.switchCase(posD, posF);
    }

    public StatusDonjon degatJoueur(Donjon DJ, int choix, int dgt) {

        _affichage.afficher(true, "Le Utils.MJ inflige ", dgt, " a ", DJ.getListePerso().get(choix));

        return DJ.getListePerso().get(choix).seFaitAttaquer(dgt, DJ);
    }

    public StatusDonjon degatMonstre(Donjon DJ, int choix, int dgt) {

        _affichage.afficher(true, "Le Utils.MJ inflige ", dgt, " a ", DJ.getListeMonstre().get(choix));

        return DJ.getListeMonstre().get(choix).seFaitAttaquer(dgt, DJ);
    }
}
