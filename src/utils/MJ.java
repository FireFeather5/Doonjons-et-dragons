package utils;

import de.De;
import donjon.Donjon;
import entite.Obstacle;
import entite.equipement.Equipement;
import entite.Monstre;
import entite.personnages.Personnage;

import java.util.ArrayList;

public class MJ {

    private final Couleurs _cl = new Couleurs();

    private final ArrayList<String> _monstresCrees = new ArrayList<>();

    public MJ()
    {

    }

    public Donjon creationDonjon(int[] tailleDj)
    {
        Donjon DJ = new Donjon(tailleDj);
        System.out.println("Aperçu du donjon :");
        DJ.afficherDJ();

        /*System.out.println("\n\nLa taille vous convient-il (o/n) ?");
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
            System.out.println(_cl.rouge() + "Erreur dans la selection de la position" + _cl.reset());
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

    public void posJ(Donjon DJ, Personnage perso, int[] pos)
    {
        boolean test = DJ.positionVivant(pos, perso);

        if (!test)
        {
            System.out.println(_cl.rouge() + "Erreur dans la selection de la position" + _cl.reset());
        }
    }

    public void posM(Donjon DJ, Monstre mons, int[] pos)
    {
        boolean test = DJ.positionVivant(pos, mons);

        if (!test)
        {
            System.out.println(_cl.rouge() + "Erreur dans la selection de la position" + _cl.reset());
            this.posM(DJ, mons, pos);
        }
    }

    public void posEquip(Donjon DJ, Equipement equip, int[] pos)
    {
        boolean test = DJ.positionEquipement(pos, equip);

        if (!test)
        {
            System.out.println(_cl.rouge() + "Erreur dans la selection de la position" + _cl.reset());
            this.posEquip(DJ, equip, pos);
        }
    }

    public void presContext(String context)
    {
        System.out.println("MJ - " + context);
    }

    public void comAction(String commentaire)
    {
        System.out.println("MJ - " + commentaire);
    }


    //      A VOIR PLUS TARD

    public void depViv(Donjon DJ, int[] posD, int[] posF)
    {
        DJ.switchCase(posD, posF);
    }

    public StatusDonjon degatJoueur(Donjon DJ, int choix, int dgt) {

        System.out.println("Le Utils.MJ inflige " + dgt + " a " + DJ.getListePerso().get(choix));

        return DJ.getListePerso().get(choix).seFaitAttaquer(dgt, DJ);
    }

    public StatusDonjon degatMonstre(Donjon DJ, int choix, int dgt) {

        System.out.println("Le Utils.MJ inflige " + dgt + " a " + DJ.getListeMonstre().get(choix));

        return DJ.getListeMonstre().get(choix).seFaitAttaquer(dgt, DJ);
    }
}
