package personnages;

import personnages.races.*;
import personnages.classes.*;
import de.*;

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
            _stats[i] += _race.Augment()[i];
        }

        _stats[0] += _classe.Pv();

        for (int i = 0; i < 5; i++)
        {
            System.out.println(_stats[i]);
        }

    }


}
