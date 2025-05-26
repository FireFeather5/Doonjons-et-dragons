package entite;

import de.De;
import entite.equipement.Equipement;
import entite.personnages.*;
import donjon.Donjon;
import statistiques.Position;
import statistiques.Stats;

import java.util.Scanner;

public class Monstre implements Vivant {
    private String _espece;
    private String _symb;
    private int _numero = 1;    //a voir plus tard
    private int _portAtt;
    private De _degAtt;
    private Stats _stats;
    private Position _pos;
    private De _deChar;

    Scanner sc = new Scanner(System.in);

    public Monstre()
    {
        _pos = new Position();
        _stats = new Stats();
    }

    public void creaMonstre(String espece, String symb, int portAtt, De degAtt, De charac)
    {
        _deChar = charac;
        _espece = espece + " " + this._numero;
        _symb = symb;
        _portAtt = portAtt;
        _degAtt = degAtt;

        System.out.println("\n\n===== initialisation monstre =====");
        _stats.pvt(_deChar.roll());
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

    public void multiMonstre() {
        this._numero ++;
        String[] nom = this._espece.split(" ");
        nom[nom.length-1] = String.valueOf(this._numero);
        this._espece = "";
        for (String s : nom) {
            this._espece += s + " ";
        }
    }

    public void position(int pos1, int pos2)
    {
        _pos.changPos(pos1, pos2);
    }

    public int action(Donjon DJ)
    {
        int val = 0;
        System.out.println("\n\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1");
        try {
            int choix = Integer.parseInt(sc.nextLine());

            switch (choix) {
                case 0:
                    seDeplacer(DJ);
                    break;
                case 1:
                    val = attaquer(DJ);
                    break;
                default:
                    System.out.println("Mauvais choix d'action");
                    action(DJ);
            }
        }
        catch (NumberFormatException erreur)
        {
            System.out.println("Mauvais choix d'action");
            action(DJ);
        }
        catch (NullPointerException erreur)
        {
            System.out.println("Mauvais choix d'action");
            action(DJ);
        }
        return val;
    }

    public void seDeplacer(Donjon DJ)
    {
        System.out.println("Choisir une case où se déplacer");
        String dep = sc.nextLine();

        try {
            int distDep = _stats.retVit() / 3;

            int[] pos = DJ.posInt(dep);

            int[] posOld = getPos();

            if (((pos[0] >= _pos.getAbscisse() - distDep) && (pos[0] <= _pos.getAbscisse() + distDep)) && ((pos[1] >= _pos.getOrdonnee() - distDep) && (pos[1] <= _pos.getOrdonnee() + distDep))) {
                boolean val = DJ.posM(dep, this);
                if (val) {
                    DJ.emptyCase(posOld);          //vide la case précédement utilisée par le monstre
                    System.out.println("Déplacement effectué");
                } else {
                    System.out.println("Problème dans le choix de la case");
                    seDeplacer(DJ);
                }
            } else {
                System.out.println("Problème dans le choix de la case");
                seDeplacer(DJ);
            }
        }
        catch (NullPointerException erreur) {
            seDeplacer(DJ);
        }
    }

    public int attaquer(Donjon DJ)
    {
        int val = 0;
        System.out.println("Choisir la case à attaquer");

        try {
            String cas = sc.nextLine();
            System.out.print("\n");
            this._deChar.changeDe(1, 20);

            try {
                int[] posAtt = DJ.posInt(cas);
                Personnage pers = DJ.getPers(posAtt);
                if (pers != null) {
                    if (((posAtt[0] >= _pos.getAbscisse() - _portAtt) && (posAtt[0] <= _pos.getAbscisse() + _portAtt)) && ((posAtt[1] >= _pos.getOrdonnee() - _portAtt) && (posAtt[1] <= _pos.getOrdonnee() + _portAtt))) {
                        // un des deux est forcément à 0 donc on peut directement ajouter les deux
                        // (évite un if else)
                        int touche = this._deChar.roll() + _stats.retFor() + _stats.retDex();
                        System.out.println(toString() + " perce l'armure de " + pers.toString() + " (jet de touche : " + touche + ").");
                        if (touche > pers.getArmorClass()) {
                            int atk = this._degAtt.roll();
                            System.out.println(toString() + " fait " + atk + " dégats à " + pers.toString() + " !");
                            val = pers.seFaitAttaquer(atk, DJ);
                        } else {
                            System.out.println(toString() + " ne perce pas l'armure de " + pers.toString() + " (jet de touche : " + touche + ")");
                        }
                    } else {
                        System.out.println(toString() + " n'a pas une portée suffisante");
                    }
                } else {
                    System.out.println("Il n'y a pas de personnage à attaquer sur cette case.");
                }
            }
            catch (ArrayIndexOutOfBoundsException erreur)
            {
                System.out.println("\nLes cases sont dans le format suivant : [lettre][nombre]");
                attaquer(DJ);
            }
        }
        catch (NullPointerException erreur)
        {
            System.out.println("\nLes cases sont dans le format suivant : [lettre][nombre]");
            attaquer(DJ);
        }
        return val;
    }

    public int getArmorClass() {
        return this._stats.retArm();
    }

    public int seFaitAttaquer(int degats, Donjon DJ) {
        int val = 0;
        int pv = _stats.retPv() - degats;
        _stats.pv(pv);
        if (pv <= 0)
        {
            System.out.println("\n" + toString() + " à été achevé.");
            val = DJ.tuerMonstre(this);
        }
        else
        {
            System.out.println("\n" + toString() + " n'a plus que " + _stats.retPv() + "/" + _stats.retPvT() + " PV.");
        }
        return val;
    }

    public String comAction()
    {
        System.out.println(toString() + " commente l'action effectuée");
        return toString() + " - " + sc.nextLine();
    }

    public String getStat() {
        return "\n\n===== " + toString() + " =====\nPv : " + _stats.retPv() + "/" + _stats.retPvT() + "\nForce : " + _stats.retFor() + "\nDexterite : " + _stats.retDex() + "\nVitesse : " + _stats.retVit() + "\nInitiative : " + _stats.retIni()  + "\nClasse d'armure : " + _stats.retArm();
    }

    public String getInfos() {
        return "\n\n===== " + toString() + " =====\nPv : " + _stats.retPv() + "/" + _stats.retPvT() + "\nForce : " + _stats.retFor() + "\nDexterite : " + _stats.retDex() + "\nVitesse : " + _stats.retVit() + "\nInitiative : " + _stats.retIni()  + "\nClasse d'armure : " + _stats.retArm();
    }

    public String getLilInfos()
    {
        return (aff() + "    " + toString() + " (" + _stats.retPv() + "/" + _stats.retPvT() + ")" + "\n");
    }

    public int[] getPos()
    {
        int[] pos = new int[2];
        pos[0] = _pos.getAbscisse();
        pos[1] = _pos.getOrdonnee();
        return pos;
    }

    public int getIni()
    {
        return _stats.retIni();
    }

    public int getPV()
    {
        return _stats.retPv();
    }

    public String aff()
    {
        if (_symb.length() == 3) {
            return _symb;
        }
        else if (_symb.length() == 2) {
            return " " + _symb;
        }
        else if (_symb.length() == 1) {
            return " " + _symb + " ";
        }
        return " X)";
    }

    public int code()
    {
        return 1;
    }

    @Override
    public String toString() {
        return this._espece;
    }
}
