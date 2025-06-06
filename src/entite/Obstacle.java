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

    public boolean addPos(int[] pos, Donjon DJ)
    {
        return DJ.positionObstacle(pos, this);
    }

    public String aff()
    {
        return "[ ]";
    }
}
