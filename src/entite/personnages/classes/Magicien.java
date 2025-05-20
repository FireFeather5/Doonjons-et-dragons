package entite.personnages.classes;

import entite.equipement.Equipement;
import entite.equipement.arme.courante.Baton;
import entite.equipement.arme.courante.MasseArme;
import entite.equipement.arme.distance.ArbaleteLegere;
import entite.equipement.arme.distance.Fronde;
import entite.equipement.armure.legere.ArmureEcaille;

import java.util.ArrayList;

public class Magicien implements Classe {

    private static int _pv = 12;
    private static ArrayList<Equipement> _equiBase;

    public Magicien()
    {
        _equiBase = new ArrayList<>();
        _equiBase.add(new Baton());
        _equiBase.add(new Fronde());
    }

    public int pv()
    {
        return _pv;
    }

    public ArrayList<Equipement> getEquiBase()
    {
        return _equiBase;
    }

}
