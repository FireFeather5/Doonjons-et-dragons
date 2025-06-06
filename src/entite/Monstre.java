package entite;

import utils.*;
import de.De;
import entite.personnages.*;
import donjon.Donjon;
import statistiques.Position;
import statistiques.Stats;

public class Monstre implements Vivant {

    private final Couleurs _cl = new Couleurs();

    private final String _espece;
    private final String _symb;
    private int _numero = 1;
    private final int _portAtt;
    private final De _degAtt;
    private final Stats _stats;
    private final Position _pos;
    private final De _deChar;

    private final Affichage _affichage = new Affichage(SortieAffichage.CONSOLE);

    public Monstre(String espece, String symb, int portAtt, De degAtt, De charac)
    {
        _deChar = charac;
        _espece = espece;
        _symb = symb;
        _portAtt = portAtt;
        _degAtt = degAtt;

        _pos = new Position();
        _stats = new Stats();

        _affichage.afficherJaune(true, "\n\n===== initialisation monstre " + _espece + " =====");
        _affichage.afficher(true, "\nLancement d'un dé pour les points de vie.");
        _stats.pvt(_deChar.roll());
        _affichage.afficher(true, "\nLancement d'un dé pour la caractéristique de vitesse.");
        _stats.vit(_deChar.roll());
        _affichage.afficher(true, "\nLancement d'un dé pour la caractéristique d'initiative.");
        _stats.ini(_deChar.roll());
        _affichage.afficher(true, "\nLancement d'un dé pour la caractéristique d'armure.");
        _stats.arm(_deChar.roll());

        if (_portAtt == 1)
        {
            _stats.dex(0);
            _affichage.afficher(true, "\nLancement d'un dé pour la caractéristique de force.");
            _stats.forc(_deChar.roll());
        }
        else
        {
            _stats.forc(0);
            _affichage.afficher(true, "\nLancement d'un dé pour la caractéristique de dextérité.");
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

    public boolean seDeplacer(Donjon DJ, int[] pos)
    {
        int distDep = _stats.retVit() / 3;

        int[] posOld = getPos();

        if (((pos[0] >= _pos.getAbscisse() - distDep) && (pos[0] <= _pos.getAbscisse() + distDep)) && ((pos[1] >= _pos.getOrdonnee() - distDep) && (pos[1] <= _pos.getOrdonnee() + distDep))) {
            if (DJ.positionVivant(pos, this)) {
                DJ.emptyCase(posOld);          //vide la case précédement utilisée par le monstre
                _affichage.afficher(true, "Déplacement effectué");
                return true;
            }
        }
        _affichage.afficherRouge(true, "Problème dans le choix de la case");
        return false;
    }

    public StatusDonjon attaquer(Donjon DJ, int[] posAtt)
    {
        StatusDonjon val = StatusDonjon.NORMAL;

        _affichage.afficher(true);
        this._deChar.changeDe(1, 20);

        try {
            Personnage pers = DJ.getPers(posAtt);
            if (pers != null) {
                if (((posAtt[0] >= _pos.getAbscisse() - _portAtt) && (posAtt[0] <= _pos.getAbscisse() + _portAtt)) && ((posAtt[1] >= _pos.getOrdonnee() - _portAtt) && (posAtt[1] <= _pos.getOrdonnee() + _portAtt))) {
                    // un des deux est forcément à 0 donc on peut directement ajouter les deux
                    // (évite un if else)
                    int detou = this._deChar.roll();
                    int touche = detou + _stats.retFor() + _stats.retDex();
                    _affichage.afficher(true, this, " perce l'armure de ", pers, " (jet de touche : ", (_stats.retFor() + _stats.retDex()), " + ", detou, " = ", touche + ").");
                    if (touche > pers.getArmorClass()) {
                        int atk = this._degAtt.roll();
                        _affichage.afficher(true, this, " fait ", atk, " dégats à ", pers, " !");
                        val = pers.seFaitAttaquer(atk, DJ);
                    } else {
                        _affichage.afficher(true, this, " ne perce pas l'armure de ", pers, " (jet de touche : ", (_stats.retFor() + _stats.retDex()), " + ", detou, " = ", touche, ")");
                    }
                } else {
                    _affichage.afficher(true, this, " n'a pas une portée suffisante");
                }
            } else {
                _affichage.afficherRouge(true, "Il n'y a pas de personnage à attaquer sur cette case.");
            }
        }
        catch (ArrayIndexOutOfBoundsException erreur)
        {
            _affichage.afficherRouge(true, "\nAttaquer en dehors du donjon n'est pas la chose la plus utile...");
            return StatusDonjon.ERREUR_CHOIX_CASE;
        }
        return val;
    }

    public StatusDonjon seFaitAttaquer(int degats, Donjon DJ) {
        StatusDonjon val = StatusDonjon.NORMAL;
        int pv = _stats.retPv() - degats;
        _stats.pv(pv);
        if (pv <= 0)
        {
            _affichage.afficherRouge(true, "\n", this, " à été achevé.");
            val = DJ.tuerMonstre(this);
        }
        else
        {
            _affichage.afficher(true, "\n", this, " n'a plus que ", _stats.retPv(), "/", _stats.retPvT(), " PV.");
        }
        return val;
    }


    public int getArmorClass() {
        return this._stats.retArm();
    }

    public String getStat() {
        return _cl.jaune("\n\n===== " + this + " =====\n") + "\nPv : " + _stats.retPv() + "/" + _stats.retPvT() + "\nForce : " + _stats.retFor() + "\nDexterite : " + _stats.retDex() + "\nVitesse : " + _stats.retVit() + "\nInitiative : " + _stats.retIni()  + "\nClasse d'armure : " + _stats.retArm();
    }

    public String getInfos() {
        return _cl.jaune("\n\n===== " + this + " =====\n") + "\nPv : " + _stats.retPv() + "/" + _stats.retPvT() + "\nForce : " + _stats.retFor() + "\nDexterite : " + _stats.retDex() + "\nVitesse : " + _stats.retVit() + "\nInitiative : " + _stats.retIni()  + "\nClasse d'armure : " + _stats.retArm();
    }

    public String getLilInfos()
    {
        return (aff() + "    " + this + " (" + _stats.retPv() + "/" + _stats.retPvT() + ")" + "\n");
    }

    public int[] getPos()
    {
        return _pos.getPosition();
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
