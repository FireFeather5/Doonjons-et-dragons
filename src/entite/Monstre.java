package monstres;

import de.De;
import personnages.*;
import donjon.Donjon;
import statistiques.Position;
import statistiques.Stats;

import java.util.Scanner;

public class Monstre {
    private String _espece;
    private int _numero;    //a voir plus tard
    private int _portAtt;
    private De _degAtt;
    private Stats _stats;
    //                  pv, for, dex, vit, ini, arm
    private Position _pos;

    private De _deChar;

    Scanner sc = new Scanner(System.in);

    public Monstre()
    {
        _pos = new Position();
        _stats = new Stats();
    }

    public void creaMonstre(String espece, int portAtt, De degAtt, De charac)
    {
        this._deChar = charac;
        _espece = espece;
        _portAtt = portAtt;
        _degAtt = degAtt;


        _stats.pv(_deChar.roll());
        _stats.vit(_deChar.roll());
        _stats.ini(_deChar.roll());
        _stats.arm(_deChar.roll());

        if (_portAtt == 1)
        {
            _stats.dex(0);
            _stats.forc(_deChar.roll());
        }
        else
        {
            _stats.forc(0);
            _stats.dex(_deChar.roll());
        }
    }

    public void position(int pos1, int pos2)
    {
        _pos.changPos(pos1, pos2);
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
        int distDep = _stats.retVit()/3;

        int[] pos = DJ.posInt(dep);

        int[] posOld = new int[2];
        posOld[0] = _pos.getAbscisse();
        posOld[1] = _pos.getOrdonnee();

        if (((pos[0] > _pos.getAbscisse() - distDep) && (pos[0] < _pos.getAbscisse() + distDep)) && ((pos[1] > _pos.getOrdonnee() - distDep) && (pos[1] < _pos.getOrdonnee() + distDep)))
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
        this._deChar.changeDe(1, 20);
        if (_portAtt >= dist)
        {
            int touche = this._deChar.roll() + _stats.retFor() + _stats.retDex();
            System.out.println("Touche : " + touche);
            if (touche > pers.getArmorClass()) {
                int atk = this._degAtt.roll();
                // un des deux est forcément à 0 donc on peut directement ajouter les deux
                // (évite un if else)
                pers.seFaitAttaquer(atk);
                System.out.println("Atk : " + atk);
            }
        }
        else
        {
            System.out.println("Cible trop loin");
        }
        // besoin des classes armement pour faire le reste
    }

    public int getArmorClass() {
        return this._stats[5];
    }

    public void seFaitAttaquer(int degats) {
        this._stats[0] -= degats;
    }

    public String getStat() {
        return "pv : " + _stats.retPv() + ", force : " + _stats.retFor() + ", dexterite : " + _stats.retDex() + ", vitesse : " + _stats.retVit() + ", initiative : " + _stats.retIni()  + ", classe d'armure : " + _stats.retArm();
    }

    public String aff()
    {
        return " Xv";
    }

    @Override
    public String toString() {
        return _espece + _numero;
    }

}
