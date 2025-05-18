package entite;

import donjon.Donjon;
import statistiques.Position;

public class Obstacle implements Entite {
    private Position _pos;

    public Obstacle()
    {
        _pos = new Position();
    }

    public void position(int pos1, int pos2)
    {
        _pos.changPos(pos1, pos2);
    }

    public boolean addPos(String pos, Donjon DJ)
    {
        boolean test = DJ.addObst(pos, this);

        return test;
    }

    public String aff()
    {
        return "[ ]";
    }

    public int code()
    {
        return 2;
    }
}
