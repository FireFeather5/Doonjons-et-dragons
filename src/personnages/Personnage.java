package personnages;

import personnages.races.*;
import personnages.classes.*;
import de.*;
import monstres.*;

public class Personnage {

    private String _nom;
    private Races _race;
    private Classe _classe;
    private int[] _stats = {0, 0, 0, 0, 0};
                        //pv, for, dex, vit, ini
    // a modifier
    //private ArrayList<equipement> _stock;
    //private ArrayList<equipement> _porte;

    public Personnage(String nom, Races race, Classe classe)
    {
        _nom = nom;
        _race = race;
        _classe = classe;

        De deChar = new De(4, 4);
        for (int j = 1; j < 5; j++)
        {
            _stats[j] += deChar.roll() + 3;
        }

        for (int i = 0; i < 5; i++)
        {
            _stats[i] += _race.augment()[i];
        }

        _stats[0] += _classe.pv();

        /*for (int k = 0; k < 5; k++)
        {
            System.out.println(_stats[k]);
        }*/

    }

    public void sEquiper()
    {
        // besoin des classes armements
    }

    public void seDeplacer()
    {
        // besoin de la classe qui gère le donjon
    }

    public void attaquer(Monstre mons, Integer dist)
    {

    }

    public void ramasser()
    {
        // besoin de la classe qui gère le donjon et des classes armements
    }

    public String getN()
    {
        return _nom.substring(0, 3);
    }


    @Override
    public String toString() {
        return _nom;
    }

}
