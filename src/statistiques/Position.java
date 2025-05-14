package statistiques;

public class Position {
    private int _abscisse;
    private int _ordonnee;

    public Position()
    {

    }

    public void changPos(int pos1, int pos2)
    {
        _abscisse = pos1;
        _ordonnee = pos2;
    }

    public int getAbscisse()
    {
        return _abscisse;
    }

    public int getOrdonnee()
    {
        return _ordonnee;
    }
}
