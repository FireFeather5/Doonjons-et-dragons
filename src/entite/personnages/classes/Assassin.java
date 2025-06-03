package entite.personnages.classes;

import entite.equipement.Equipement;
import entite.equipement.arme.courante.*;
import entite.equipement.arme.distance.Couteaux;
import entite.equipement.armure.legere.*;

import java.util.ArrayList;

public class Assassin implements Classe{

    private final static int _pv = 14;
    private static ArrayList<Equipement> _equiBase;

    public Assassin()
    {
        _equiBase = new ArrayList<>();
        _equiBase.add(new Cape());
        _equiBase.add(new Dague());
        _equiBase.add(new Couteaux());
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
