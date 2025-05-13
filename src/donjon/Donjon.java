package donjon;

import monstres.Monstre;
import personnages.Personnage;

import java.util.ArrayList;

public class Donjon {
    private int _tc1;
    private int _tc2;
    private ArrayList<String[]> _pos;
    private String[][] _donjon;

    public Donjon()
    {
        _pos = new ArrayList<String[]>();
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
            if (_donjon[pc1 - 1][pc2 - 1].equals(" . "))
            {
                _donjon[pc1-1][pc2-1] = "[ ]";
                return true;
            }
        }
        return false;
    }

    public boolean posJ(int pc1, int pc2, Personnage perso)
    {

        if (((_tc1 >= pc1) && (pc1 >= 1)) && ((_tc2 >= pc2) && (pc2 >= 1)))
        {
            if (_donjon[pc1 - 1][pc2 - 1].equals(" . "))
            {
                _donjon[pc1 - 1][pc2 - 1] = perso.getN();
                return true;
            }
        }
        return false;
    }

    public boolean posM(int pc1, int pc2, Monstre mons)
    {
        if (((_tc1 >= pc1) && (pc1 >= 1)) && ((_tc2 >= pc2) && (pc2 >= 1)))
        {
            if (_donjon[pc1 - 1][pc2 - 1].equals(" . "))
            {
                /*_pos1[0] = mons.toString();
                _pos1[1] = Integer.toString(pc1);
                _pos1[2] = Integer.toString(pc2);
                _pos.add(_pos1);*/
                _donjon[pc1 - 1][pc2 - 1] = " Xv";
                return true;
            }
        }
        return false;
    }

    public int[] getPos(String nom)
    {
        int[] pos = new int[2];
        for (int i = 0; i < _pos.size(); i++)
        {
            if (_pos.get(i)[0].equals(nom))
            {
                pos[0] = Integer.parseInt(_pos.get(i)[1]);
                pos[1] = Integer.parseInt(_pos.get(i)[2]);
            }
        }
        return pos;
    }

    public void deplacer(String nom, int lonD)
    {
        int[] pos = this.getPos(nom);

        String[][] dep = new String[lonD*2][lonD*2];

        for (int i = -lonD; i < lonD; i++)
        {
            for (int j = -lonD; j < lonD; j++)
            {
                if ((i != 0) || (j!=0))
                {
                    dep[i + lonD][j + lonD] = " O ";
                }
                else
                {
                    dep[i + lonD][j + lonD] = " X ";
                }
                System.out.print(dep[i + lonD][j + lonD]);
                // il faut réussir à aligner dep sur _donjon, puis test si cases vides, puiis demander quelle case choisie
            }
            System.out.print("\n");
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
