package donjon;

import monstres.Monstre;
import personnages.Personnage;

import java.util.ArrayList;

public class Donjon {
    private int _tc1;
    private int _tc2;
    private String[] _ord = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
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

    public int[] posInt(String pos)
    {
        String pos1 = pos.substring(0, 1);
        String pos2 = pos.substring(1);

        int[] posi = new int[2];

        for (int i = 0; i < 26; i++)
        {
            if (pos1.equals(_ord[i]))
            {
                posi[1] = i+1;
            }
        }
        posi[0] = Integer.parseInt(pos2);

        //System.out.println(posi[1] + "    " + posi[0]);

        return posi;
    }

    public boolean addObst(String pos)
    {
        int[] pc = posInt(pos);

        if (((_tc1 >= pc[0]) && (pc[0] >= 1)) && ((_tc2 >= pc[1]) && (pc[1] >= 1)))
        {
            if (_donjon[pc[0] - 1][pc[1] - 1].equals(" . "))
            {
                _donjon[pc[0]-1][pc[1]-1] = "[ ]";
                return true;
            }
        }
        return false;
    }

    public boolean posJ(String pos, Personnage perso)
    {
        int[] pc = posInt(pos);

        if (((_tc1 >= pc[0]) && (pc[0] >= 1)) && ((_tc2 >= pc[1]) && (pc[1] >= 1)))
        {
            if (_donjon[pc[0] - 1][pc[1] - 1].equals(" . "))
            {
                perso.position(pc[0], pc[1]);            //donne sa position au monstre
                _donjon[pc[0] - 1][pc[1] - 1] = perso.getN();
                return true;
            }
        }
        return false;
    }

    public boolean posM(String pos, Monstre mons)
    {
        int[] pc = posInt(pos);

        if (((_tc1 >= pc[0]) && (pc[0] >= 1)) && ((_tc2 >= pc[1]) && (pc[1] >= 1)))
        {
            if (_donjon[pc[0] - 1][pc[1] - 1].equals(" . "))
            {
                mons.position(pc[0], pc[1]);            //donne sa position au monstre
                _donjon[pc[0] - 1][pc[1] - 1] = " Xv";
                return true;
            }
        }
        return false;
    }

    public void emptyCase(int[] pc)
    {
        _donjon[pc[0]-1][pc[1]-1] = " . ";
    }


    public void afficherDJ()
    {
        System.out.print("    ");
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
    }

}
