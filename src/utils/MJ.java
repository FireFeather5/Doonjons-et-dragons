package utils;

import de.De;
import donjon.Donjon;
import entite.Obstacle;
import entite.Vivant;
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

    public boolean ajoutObstacle(Donjon DJ, int[] pos)
    {
        Obstacle obs = new Obstacle();
        boolean test = obs.addPositionDonjon(pos, DJ);

        if (!test)
        {
            System.out.println(_cl.rouge() + "Erreur dans la selection de la position" + _cl.reset());
        }
        return test;
    }

    public Monstre creationMonstre(String espece, String symb, int portee, De degAtt, De charac)
    {
        Monstre mons = new Monstre(espece, symb, portee, degAtt, charac);

        for (String monstre : this._monstresCrees) {
            if (monstre.equals(espece)) {
                mons.numeroMonstre();
            }
        }
        this._monstresCrees.add(espece);

        return mons;
    }

    public boolean setPositionPerso(Donjon DJ, Personnage perso, int[] pos)
    {
        boolean test = DJ.positionVivant(pos, perso);

        if (!test)
        {
            System.out.println(_cl.rouge() + "Erreur dans la selection de la position" + _cl.reset());
        }
        return test;
    }

    public boolean setPositionMonstre(Donjon DJ, Monstre mons, int[] pos)
    {
        boolean test = DJ.positionVivant(pos, mons);

        if (!test)
        {
            System.out.println(_cl.rouge() + "Erreur dans la selection de la position" + _cl.reset());
        }
        return test;
    }

    public boolean setPositionEquipement(Donjon DJ, Equipement equip, int[] pos)
    {
        boolean test = DJ.positionEquipement(pos, equip);

        if (!test)
        {
            System.out.println(_cl.rouge() + "Erreur dans la selection de la position" + _cl.reset());
        }
        return test;
    }

    public void presContext(String context)
    {
        System.out.println("MJ - " + context);
    }

    public void commentaireAction(String commentaire)
    {
        System.out.println("\nMJ - " + commentaire);
    }



    public void deplacementVivant(Donjon DJ, Vivant viv, int[] posFinale)
    {
        DJ.emptyCase(viv.getPosition());
        DJ.positionVivant(posFinale, viv);
    }

    public StatusDonjon degatVivant(Donjon DJ, Vivant viv, int degats)
    {
        System.out.println("Le MJ inflige " + degats + " dégats a " + viv);

        return viv.seFaitAttaquer(degats, DJ);
    }
}