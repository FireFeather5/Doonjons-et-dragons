package entite.personnages.classes;

import entite.equipement.Equipement;
import entite.equipement.arme.courante.Baton;
import entite.equipement.arme.distance.Fronde;

import java.util.ArrayList;

public class Magicien implements Classe {

    private final static int _pv = 12;
    private static ArrayList<Equipement> _equiBase;

    public Magicien()
    {
        _equiBase = new ArrayList<>();
        _equiBase.add(new Baton());
        _equiBase.add(new Fronde());
    }

    public int getPv()
    {
        return _pv;
    }

    public ArrayList<Equipement> getEquiBase()
    {
        return _equiBase;
    }

    public String getClasse()
    {
        return "Magicien";
    }

}
