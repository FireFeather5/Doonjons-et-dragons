package entite;

import Utils.Inputs;
import Utils.StatusDonjon;
import Utils.Couleurs;
import Utils.TypeVivant;
import de.De;
import entite.personnages.*;
import donjon.Donjon;
import statistiques.Position;
import statistiques.Stats;

import java.util.Scanner;

public class Monstre implements Vivant {

    private Couleurs _cl = new Couleurs();
    private final Inputs _input = new Inputs();

    private String _espece;
    private String _symb;
    private int _numero = 1;
    private int _portAtt;
    private De _degAtt;
    private final Stats _stats;
    private final Position _pos;
    private De _deChar;

    public Monstre(String espece, String symb, int portAtt, De degAtt, De charac)
    {
        _deChar = charac;
        _espece = espece;
        _symb = symb;
        _portAtt = portAtt;
        _degAtt = degAtt;

        _pos = new Position();
        _stats = new Stats();

        System.out.println(_cl.jaune() + "\n\n===== initialisation monstre " + _espece + " =====" + _cl.reset());
        System.out.println("\nLancement d'un dé pour les points de vie.");
        _stats.pvt(_deChar.roll());
        System.out.println("\nLancement d'un dé pour la caractéristique de vitesse.");
        _stats.vit(_deChar.roll());
        System.out.println("\nLancement d'un dé pour la caractéristique d'initiative.");
        _stats.ini(_deChar.roll());
        System.out.println("\nLancement d'un dé pour la caractéristique d'armure.");
        _stats.arm(_deChar.roll());

        if (_portAtt == 1)
        {
            _stats.dex(0);
            System.out.println("\nLancement d'un dé pour la caractéristique de force.");
            _stats.forc(_deChar.roll());
        }
        else
        {
            _stats.forc(0);
            System.out.println("\nLancement d'un dé pour la caractéristique de dextérité.");
            _stats.dex(_deChar.roll());
        }
    }

    public void multiMonstre() {
        this._numero ++;
    }

    public void position(int pos1, int pos2)
    {
        _pos.changPos(pos1, pos2);
    }

    public void seDeplacer(Donjon DJ)
    {
        int distDep = _stats.retVit() / 3;

        int[] pos = _input.choixCase("où se déplacer");

        int[] posOld = getPos();

        if (((pos[0] >= _pos.getAbscisse() - distDep) && (pos[0] <= _pos.getAbscisse() + distDep)) && ((pos[1] >= _pos.getOrdonnee() - distDep) && (pos[1] <= _pos.getOrdonnee() + distDep))) {
            boolean val = DJ.positionMonstre(pos, this);
            if (val) {
                DJ.emptyCase(posOld);          //vide la case précédement utilisée par le monstre
                System.out.println("Déplacement effectué");
            } else {
                System.out.println(_cl.rouge() + "Problème dans le choix de la case" + _cl.reset());
                seDeplacer(DJ);
            }
        } else {
            System.out.println(_cl.rouge() + "Problème dans le choix de la case" + _cl.reset());
            seDeplacer(DJ);
        }
    }

    public StatusDonjon attaquer(Donjon DJ)
    {
        StatusDonjon val = StatusDonjon.NORMAL;

        System.out.print("\n");
        this._deChar.changeDe(1, 20);

        try {
            int[] posAtt = _input.choixCase("à attaquer");
            Personnage pers = DJ.getPers(posAtt);
            if (pers != null) {
                if (((posAtt[0] >= _pos.getAbscisse() - _portAtt) && (posAtt[0] <= _pos.getAbscisse() + _portAtt)) && ((posAtt[1] >= _pos.getOrdonnee() - _portAtt) && (posAtt[1] <= _pos.getOrdonnee() + _portAtt))) {
                    // un des deux est forcément à 0 donc on peut directement ajouter les deux
                    // (évite un if else)
                    int detou = this._deChar.roll();
                    int touche = detou + _stats.retFor() + _stats.retDex();
                    System.out.println(this + " perce l'armure de " + pers + " (jet de touche : " + (_stats.retFor() + _stats.retDex()) + " + " + detou + " = " + touche + ").");
                    if (touche > pers.getArmorClass()) {
                        int atk = this._degAtt.roll();
                        System.out.println(this + " fait " + atk + " dégats à " + pers + " !");
                        val = pers.seFaitAttaquer(atk, DJ);
                    } else {
                        System.out.println(this + " ne perce pas l'armure de " + pers + " (jet de touche : " + (_stats.retFor() + _stats.retDex()) + " + " + detou + " = " + touche + ")");
                    }
                } else {
                    System.out.println(this + " n'a pas une portée suffisante");
                }
            } else {
                System.out.println(_cl.rouge() + "Il n'y a pas de personnage à attaquer sur cette case." + _cl.reset());
            }
        }
        catch (ArrayIndexOutOfBoundsException erreur)
        {
            System.out.println(_cl.rouge() + "\nLes cases sont dans le format suivant : [lettre][nombre]" + _cl.reset());
            attaquer(DJ);
        }
        return val;
    }

    public StatusDonjon seFaitAttaquer(int degats, Donjon DJ) {
        StatusDonjon val = StatusDonjon.NORMAL;
        int pv = _stats.retPv() - degats;
        _stats.pv(pv);
        if (pv <= 0)
        {
            System.out.println(_cl.rouge() + "\n" + this + " à été achevé." + _cl.reset());
            val = DJ.tuerMonstre(this);
        }
        else
        {
            System.out.println("\n" + this + " n'a plus que " + _stats.retPv() + "/" + _stats.retPvT() + " PV.");
        }
        return val;
    }

    public void comAction()
    {
        System.out.println(this + _input.persoCommenteAction());
    }



    public int getArmorClass() {
        return this._stats.retArm();
    }

    public String getStat() {
        return _cl.jaune() + "\n\n===== " + this + " =====\n" + _cl.reset() + "\nPv : " + _stats.retPv() + "/" + _stats.retPvT() + "\nForce : " + _stats.retFor() + "\nDexterite : " + _stats.retDex() + "\nVitesse : " + _stats.retVit() + "\nInitiative : " + _stats.retIni()  + "\nClasse d'armure : " + _stats.retArm();
    }

    public String getInfos() {
        return _cl.jaune() + "\n\n===== " + this + " =====\n" + _cl.reset() + "\nPv : " + _stats.retPv() + "/" + _stats.retPvT() + "\nForce : " + _stats.retFor() + "\nDexterite : " + _stats.retDex() + "\nVitesse : " + _stats.retVit() + "\nInitiative : " + _stats.retIni()  + "\nClasse d'armure : " + _stats.retArm();
    }

    public String getLilInfos()
    {
        return (aff() + "    " + this + " (" + _stats.retPv() + "/" + _stats.retPvT() + ")" + "\n");
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

    public String getNom() {
        return this._espece;
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

    public TypeVivant getTypeVivant()
    {
        return TypeVivant.MONSTRE;
    }

    @Override
    public String toString() {
        return this._espece + " " + this._numero;
    }
}
