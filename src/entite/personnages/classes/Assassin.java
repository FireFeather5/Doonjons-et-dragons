package entite.personnages.classes;

import entite.equipement.Equipement;
import entite.equipement.arme.distance.ArbaleteLegere;
import entite.equipement.arme.courante.Rapiere;
import entite.equipement.armure.legere.DemiPlate;

import java.util.ArrayList;

public class Assassin implements Classe{

    private final static int _pv = 14;
    private static ArrayList<Equipement> _equiBase;

    public Assassin()
    {
        _equiBase = new ArrayList<>();
        _equiBase.add(new DemiPlate());
        _equiBase.add(new Rapiere());
        _equiBase.add(new ArbaleteLegere());
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
        return "Assassin";
    }

}
