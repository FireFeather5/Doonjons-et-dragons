package entite;

import donjon.Donjon;
import statistiques.Position;

public class Obstacle implements Entite {
    private final Position _pos;

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
        return DJ.addObst(pos, this);
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
