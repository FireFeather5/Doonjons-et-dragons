package donjon;

import entite.Entite;

import java.util.ArrayList;

public class AffichDJ {
    private int _tc1;
    private int _tc2;
    private String[][] _donjon;
    private static String[] _ord = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private ArrayList<Entite> _lstEnt;

    public AffichDJ(int tc1, int tc2)
    {
        _tc1 = tc1;
        _tc2 = tc2;
        _donjon = new String[_tc1][_tc2];
        _lstEnt = new ArrayList<>();
    }

    public void rempDJ(Entite[][] dj)
    {
        for (int i = 0; i < _tc1; i++)
        {
            for (int j = 0; j < _tc2; j++)
            {
                if (dj[i][j]!=null)
                {
                    _donjon[i][j] = dj[i][j].aff();
                    _lstEnt.add(dj[i][j]);
                }
                else
                {
                    _donjon[i][j] = " . ";
                }
            }
        }
    }

    public void afficherDJ(Entite[][] dj)
    {
        rempDJ(dj);

        System.out.print("\n    ");
        for (int k = 1; k <= _tc2; k++)
        {
            System.out.print(" " + _ord[k-1] + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < _tc1; i++)
        {
            if (i < 9) {
                System.out.print(" " + (i+1) + "  ");
            }
            else
            {
                System.out.print(" " + (i+1) + " ");
            }

            for (int j = 0; j < _tc2; j++)
            {
                System.out.print(_donjon[i][j]);
            }
            System.out.print("\n");
        }

        System.out.print("            ");
        System.out.print("* = equipement     ");
        for(Entite ent : _lstEnt)
        {
            if (ent.code() == 1)
            {
                System.out.print(ent.aff() + " : " + ent.toString() + "     ");
            }
        }
        _lstEnt.clear();

    }

}
