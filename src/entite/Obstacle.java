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

    public void addPos(String pos, Donjon DJ)
    {
        boolean test = DJ.addObst(pos, this);

        if (!test)
        {
            System.out.println("Erreur dans la selection de la position");
            this.addPos(pos, DJ);
        }
    }

    public String aff()
    {
        return "[ ]";
    }
}
