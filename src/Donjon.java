import monstres.Monstre;
import personnages.Personnage;

public class Donjon {
    private int _tc1;
    private int _tc2;
    private String[][] _donjon;

    public Donjon()
    {

    }

    public void creaDonjon(int tc1, int tc2)
    {
        _tc1 = tc1;
        _tc2 = tc2;
        _donjon = new String[_tc1][_tc2];

        for (int i = 0; i < _tc1; i++)
        {
            for (int j = 0; j < _tc2; j++)
            {
                _donjon[i][j] = " . ";
            }
        }
    }

    public boolean addObst(int pc1, int pc2)
    {
        if (((_tc1 >= pc1) && (pc1 >= 1)) && ((_tc2 >= pc2) && (pc2 >= 1)))
        {
            _donjon[pc1-1][pc2-1] = "[ ]";
            return true;
        }
        else
        {
            return false;
        }

    }

    public boolean posJ(int pc1, int pc2, Personnage perso)
    {

        if (((_tc1 >= pc1) && (pc1 >= 1)) && ((_tc2 >= pc2) && (pc2 >= 1)))
        {
            _donjon[pc1-1][pc2-1] = perso.getN();
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean posM(int pc1, int pc2, Monstre mons)
    {

        if (((_tc1 >= pc1) && (pc1 >= 1)) && ((_tc2 >= pc2) && (pc2 >= 1)))
        {
            _donjon[pc1-1][pc2-1] = " Xv";
            return true;
        }
        else
        {
            return false;
        }
    }

    public void afficherDJ()
    {
        System.out.print("    ");
        for (int k = 1; k <= _tc2; k++)
        {
            if (k < 10) {
                System.out.print(" " + k + " ");
            }
            else
            {
                System.out.print(" " + k);
            }
        }
        System.out.print("\n");
        for (int i = 0; i < _tc1; i++)
        {
            if (i+1 < 10) {
                System.out.print(" " + (i + 1) + "  ");
            }
            else
            {
                System.out.print(" " + (i + 1) + " ");
            }
            for (int j = 0; j < _tc2; j++)
            {
                System.out.print(_donjon[i][j]);
            }
            System.out.print("\n");
        }
    }

}
