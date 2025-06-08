package entite;

import donjon.Donjon;
import statistiques.Position;

public class Obstacle implements Entite {


    private final Position _pos;

    public Obstacle()
    {
        _pos = new Position();
    }

    public void setPosition(int pos1, int pos2)
    {
        _pos.changPos(pos1, pos2);
    }

    public boolean addPositionDonjon(int[] pos, Donjon DJ)
    {
        return DJ.positionObstacle(pos, this);
    }

    public String affichage()
    {
        return "[ ]";
    }
}
