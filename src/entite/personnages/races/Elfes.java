package entite.personnages.races;

import statistiques.Stats;

public class Elfes implements Races {

    private static Stats _stat;

    public Elfes()
    {
        _stat = new Stats();
        _stat.dex(6);
    }

    public Stats stat()
    {
        return _stat;
    }
}
