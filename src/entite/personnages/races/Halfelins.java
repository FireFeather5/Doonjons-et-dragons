package entite.personnages.races;

import statistiques.Stats;

public class Halfelins implements Races {

    private static Stats _stat;

    public Halfelins()
    {
        _stat = new Stats();
        _stat.dex(4);
        _stat.vit(2);
    }

    public Stats getStat()
    {
        return _stat;
    }

    public String getRace()
    {
        return "Halfelin";
    }
}
