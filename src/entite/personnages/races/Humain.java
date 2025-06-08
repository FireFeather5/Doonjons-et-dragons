package entite.personnages.races;

import statistiques.Stats;

public class Humain implements Races {

    private static Stats _stat;

    public Humain()
    {
        _stat = new Stats();
        _stat.forc(2);
        _stat.dex(2);
        _stat.vit(2);
        _stat.ini(2);
    }

    public Stats getStat()
    {
        return _stat;
    }

    public String getRace()
    {
        return "Humain";
    }

}
