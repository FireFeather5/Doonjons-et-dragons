package entite.personnages.classes;

import entite.equipement.Equipement;
import entite.equipement.arme.courante.MasseArme;
import entite.equipement.arme.distance.ArbaleteLegere;
import entite.equipement.arme.distance.ArcCourt;
import entite.equipement.arme.guerre.Rapiere;
import entite.equipement.armure.legere.ArmureEcaille;

import java.util.ArrayList;

public class Roublard implements Classe {

    private static int _pv = 16;
    private static ArrayList<Equipement> _equiBase;

    public Roublard()
    {
        _equiBase = new ArrayList<>();
        _equiBase.add(new Rapiere());
        _equiBase.add(new ArcCourt());
    }

    public int pv()
    {
        return _pv;
    }

    public ArrayList<Equipement> getEquiBase()
    {
        return _equiBase;
    }

    public String getCla()
    {
        return "Roublard";
    }

}
