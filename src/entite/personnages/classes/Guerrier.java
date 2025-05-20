package entite.personnages.classes;

import entite.equipement.Equipement;
import entite.equipement.arme.courante.MasseArme;
import entite.equipement.arme.distance.ArbaleteLegere;
import entite.equipement.arme.guerre.EpeeLongue;
import entite.equipement.armure.legere.ArmureEcaille;
import entite.equipement.armure.lourde.CotteMaille;

import java.util.ArrayList;

public class Guerrier implements Classe {

    private static int _pv = 20;
    private static ArrayList<Equipement> _equiBase;

    public Guerrier()
    {
        _equiBase = new ArrayList<>();
        _equiBase.add(new CotteMaille());
        _equiBase.add(new EpeeLongue());
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
