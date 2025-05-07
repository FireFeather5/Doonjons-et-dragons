package personnages;

import personnages.classes.Classe;
import personnages.races.Races;

public class Personnage {

    private String _nom;
    private Races _race;
    private Classe _classe;
    private int[] _stats = {0, 0, 0, 0, 0};
                        //pv, for, dex, vit, ini
    //private ArrayList<equipement> _stock;
    //private ArrayList<equipement> _porte;

    public Personnage(String nom, Races race, Classe classe)
    {
        _nom = nom;
        _race = race;
        _classe = classe;

        for (int i = 0; i < 5; i++)
        {
            System.out.println(_stats[i]);
        }

        for (int i = 0; i < 5; i++)
        {
            _stats[i] += _race.Augment()[i];
        }

        for (int i = 0; i < 5; i++)
        {
            System.out.println(_stats[i]);
        }

        _stats[0] += _classe.Pv();

        for (int i = 0; i < 5; i++)
        {
            System.out.println(_stats[i]);
        }

    }


}
