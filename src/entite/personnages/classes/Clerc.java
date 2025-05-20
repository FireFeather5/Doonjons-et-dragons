package entite.personnages.classes;

import entite.equipement.Equipement;
import entite.equipement.arme.courante.MasseArme;
import entite.equipement.arme.distance.ArbaleteLegere;
import entite.equipement.armure.legere.ArmureEcaille;

import java.util.ArrayList;

public class Clerc implements Classe {

    private static int _pv = 16;
    private static ArrayList<Equipement> _equiBase;

    public Clerc()
    {
        _equiBase = new ArrayList<>();
        _equiBase.add(new MasseArme());
        _equiBase.add(new ArmureEcaille());
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

}
