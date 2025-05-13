package monstres;

import de.De;
import personnages.*;
import donjon.Donjon;

import java.util.Scanner;

public class Monstre {
    private String _espece;
    private int _numero;    //a voir plus tard
    private int _portAtt;
    private De _degAtt;
    private int[] _stats = {0, 0, 0, 0, 0, 0};
    //                  pv, for, dex, vit, ini, arm
    private int[] _pos = {0, 0};

    Scanner sc = new Scanner(System.in);

    public Monstre()
    {

    }

    public void creaMonstre(String espece, int portAtt, De degAtt, De charac)
    {
        _espece = espece;
        _portAtt = portAtt;
        _degAtt = degAtt;

        for (int i = 0; i < 6; i++)
        {
            _stats[i] += charac.roll() + 3;
        }
        if (_portAtt == 1)
        {
            _stats[2] = 0;
        }
        else
        {
            _stats[1] = 0;
        }

        /*for (int i = 0; i < 6; i++)
        {
            System.out.println("Mo " + _stats[i]);
        }*/
    }

    public void position(int pos1, int pos2)
    {
        _pos[0] = pos1;
        _pos[1] = pos2;
    }

    public void action(Donjon DJ)
    {
        System.out.println("choisir une case où se déplacer");
        String pc = sc.nextLine();

        boolean val = seDeplacer(pc, DJ);

        if (val)
        {
            System.out.println("Déplacement effectué");
        }
        else {
            action(DJ);     //a modifier (ne fonctionnera pas quand les autre fonctions seront implémentées
        }
    }

    public boolean seDeplacer(String dep, Donjon DJ)
    {
        int distDep = _stats[4]/3;

        int[] pos = DJ.posInt(dep);

        int[] posOld = new int[2];
        posOld[0] = _pos[0];
        posOld[1] = _pos[1];

        if (((pos[0] > _pos[0] - distDep) && (pos[0] < _pos[0] + distDep)) && ((pos[1] > _pos[1] - distDep) && (pos[1] < _pos[1] + distDep)))
        {
            boolean val = DJ.posM(dep, this);
            if (val)
            {
                DJ.emptyCase(posOld);          //vide la case précédement utilisée par le monstre
                return true;
            }
        }
        return false;           //si faux, redemander une position
    }

    public void attaquer(Personnage pers, int dist)
    {
        De deAtt = new De(1, 20);
        if (_portAtt >= dist)
        {
            int atk = deAtt.roll() + _stats[1] + _stats[2];
            // un des deux est forcément à 0 donc on peut directement ajouter les deux
            // (évite un if else)
            System.out.println("atk : " + atk);
        }
        else
        {
            System.out.println("Cible trop loin");
        }
        // besoin des classes armement pour faire le reste
    }

    @Override
    public String toString() {
        return _espece + _numero;
    }

}
