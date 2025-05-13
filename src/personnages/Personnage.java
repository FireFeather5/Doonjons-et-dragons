package personnages;

import donjon.Donjon;
import personnages.races.*;
import personnages.classes.*;
import de.*;
import monstres.*;

import java.util.Scanner;

public class Personnage {

    private String _nom;
    private Races _race;
    private Classe _classe;
    private int[] _stats = {0, 0, 0, 0, 0};
                        //pv, for, dex, vit, ini
    private int[] _pos = {0, 0};

    Scanner sc = new Scanner(System.in);


    public Personnage(String nom, Races race, Classe classe)
    {
        _nom = nom;
        _race = race;
        _classe = classe;

        De deChar = new De(4, 4);
        for (int j = 1; j < 5; j++)
        {
            _stats[j] += deChar.roll() + 3;
        }

        for (int i = 0; i < 5; i++)
        {
            _stats[i] += _race.augment()[i];
        }

        _stats[0] += _classe.pv();

        /*for (int k = 0; k < 5; k++)
        {
            System.out.println(_stats[k]);
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
            boolean val = DJ.posJ(dep, this);
            if (val)
            {
                DJ.emptyCase(posOld);          //vide la case précédement utilisée par le monstre
                return true;
            }
        }
        return false;           //si faux, redemander une position
    }


    public void sEquiper()
    {
        // besoin des classes armements
    }


    public void attaquer(Monstre mons, Integer dist)
    {

    }

    public void ramasser()
    {
        // besoin de la classe qui gère le donjon et des classes armements
    }

    public String getN()
    {
        return _nom.substring(0, 3);
    }


    @Override
    public String toString() {
        return _nom;
    }

}
