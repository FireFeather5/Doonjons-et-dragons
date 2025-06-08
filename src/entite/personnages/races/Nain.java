package entite.personnages.races;

import statistiques.Stats;

public class Nain implements Races {

    private static Stats _stat;

    public Nain()
    {
        _stat = new Stats();
        _stat.forc(6);
    }

    public Stats getStat()
    {
        return _stat;
    }

    public String getRace()
    {
        return "Nain";
    }
}
