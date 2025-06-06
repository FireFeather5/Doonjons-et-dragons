package entite.personnages;

import utils.*;
import donjon.Donjon;
import entite.Monstre;
import entite.Vivant;
import entite.personnages.classes.*;
import entite.personnages.genre.Genre;
import entite.personnages.races.Races;
import entite.equipement.Equipement;
import entite.equipement.arme.Arme;
import entite.equipement.armure.Armure;
import de.*;
import sort.*;
import statistiques.Position;
import statistiques.Stats;

import java.util.ArrayList;

public class Personnage implements Vivant {

    private final Couleurs _cl = new Couleurs();

    private final String _nom;
    private final Races _race;
    private final Classe _classe;
    private final Genre _gre;

    private final De _deChar = new De(4, 4);
    private final Stats _stats;
    private final Position _pos;

    private Equipement _peutRamEqu = null;
    private boolean _peutRamasser;

    private final ArrayList<Equipement> _stock;
    private final ArrayList<Equipement> _equipee;
    private final ArrayList<Sort> _sorts;
    private final Affichage _affichage = new Affichage(SortieAffichage.CONSOLE);


    public Personnage(String nom, Races race, Classe classe, Genre gre)
    {
        _stock = new ArrayList<>();
        _equipee = new ArrayList<>();
        _stats = new Stats();
        _pos = new Position();
        _sorts = new ArrayList<>();


        _nom = nom;
        _race = race;
        _classe = classe;
        _gre = gre;


        _stats.pvt(_classe.pv());
        _stats.add(_race.stat());

        _affichage.afficher(true, "\n===== caractéristiques perso =====");
        _affichage.afficher(true, "\nLancement d'un dé pour la caractéristique de force.");
        _stats.forc(_deChar.roll() + 3);
        _affichage.afficher(true, "\nLancement d'un dé pour la caractéristique de dextérité.");
        _stats.dex(_deChar.roll() + 3);
        _affichage.afficher(true, "\nLancement d'un dé pour la caractéristique de vitesse.");
        _stats.vit(_deChar.roll() + 3);
        _affichage.afficher(true, "\nLancement d'un dé pour la caractéristique d'initiative.");
        _stats.ini(_deChar.roll() + 3);

        _stock.addAll(_classe.getEquiBase());

        _peutRamasser = false;


        if (_classe.getCla().equals("Clerc")) {
            this._sorts.add(new Guerison());
        }
        else if (_classe.getCla().equals("Magicien")) {
            this._sorts.add(new Guerison());
            this._sorts.add(new BoogieWoogie());
            this._sorts.add(new ArmeMagique());
        }
    }

    public void position(int pos1, int pos2)
    {
        _pos.changPos(pos1, pos2);
    }



    public boolean seDeplacer(Donjon DJ, int[] pos)
    {
        try {
            int distDep = _stats.retVit() / 3;

            int[] posOld = getPos();

            if (((pos[0] >= _pos.getAbscisse() - distDep) && (pos[0] <= _pos.getAbscisse() + distDep)) && ((pos[1] >= _pos.getOrdonnee() - distDep) && (pos[1] <= _pos.getOrdonnee() + distDep))) {

                boolean val = DJ.positionVivant(pos, this);
                if (!_peutRamasser) {
                    if (val) {
                        DJ.emptyCase(posOld);          //vide la case précédement utilisée par le perso
                        _affichage.afficher(true, "Déplacement effectué");
                        return true;
                    } else {
                        _affichage.afficherRouge(true, "Problème dans le choix de la case");
                        return false;
                    }
                } else {
                    if (val) {
                        DJ.emptyCase(posOld);          //vide la case précédement utilisée par le perso
                        DJ.positionEquipement(posOld, _peutRamEqu);       //remet l'objet dans la case
                        _affichage.afficher(true, "Déplacement effectué");
                        _peutRamasser = false;
                        _peutRamEqu = null;
                        return true;
                    } else {
                        _affichage.afficherRouge(true, "Problème dans le choix de la case");
                        return false;
                    }
                }
            } else {
                _affichage.afficherRouge(true, "Problème dans le choix de la case");
                return false;
            }
        }
        catch (NullPointerException erreur) {
            return false;
        }
    }

    public void seDesequiper(Equipement equipement) {
        if (this._equipee.contains(equipement)) {
            this._equipee.remove(equipement);
            if (equipement.getTypeArm().isPresent() && equipement.getTypeArm().get().equals(TypeArme.GUERRE)) {
                this._stats.vit(_stats.retVit() + equipement.getSpeedMalus());
                this._stats.forc(_stats.retFor() - equipement.getForceBonus());
            }
            else if (equipement.getTypeArmur().isPresent() && equipement.getTypeArmur().get().equals(TypeArmure.LOURDE)) {
                this._stats.vit(_stats.retVit() + equipement.getSpeedMalus());
            }
            this._stock.add(equipement);
        }
        else {
            _affichage.afficherRouge(true, "ERREUR : l'equipement n'est pas équipée");
        }
    }

    public void sEquiper(Equipement equipement)
    {
        if (equipement != null)
        {
            if (this._stock.contains(equipement))
            {
                if (equipement.getTypeArm().isPresent() && equipement.getTypeArm().get().equals(TypeArme.GUERRE))
                {
                    this._stats.vit(_stats.retVit() - equipement.getSpeedMalus());
                    this._stats.forc(_stats.retFor() + equipement.getForceBonus());
                }
                else if (equipement.getTypeEquip().equals(TypeEquipement.ARMURE))
                {
                    _stats.arm(((Armure) equipement).getArmorClass());
                    if (equipement.getTypeArmur().isPresent() && equipement.getTypeArmur().get().equals(TypeArmure.LOURDE))
                    {
                        this._stats.vit(_stats.retVit() - equipement.getSpeedMalus());
                    }
                }
                for (Equipement equip : this._equipee)
                {
                    if (equip.getTypeEquip().equals(TypeEquipement.ARME) && equipement.getTypeEquip().equals(TypeEquipement.ARME)) {
                        this.seDesequiper(equip);
                        break;
                    } else if (equip.getTypeEquip().equals(TypeEquipement.ARMURE) && equipement.getTypeEquip().equals(TypeEquipement.ARMURE)) {
                        this.seDesequiper(equip);
                        break;
                    }
                }
                this._equipee.add(equipement);
                this._stock.remove(equipement);
                _affichage.afficher(true, equipement.getName(), " à bien été équipé");
            } else {
                _affichage.afficherRouge(true, "ERREUR : l'equipement n'est pas dans l'inventaire");
            }
        }
        else
        {
            _affichage.afficherRouge(true, "\nIl n'y a pas d'équipement à equiper.");
        }
    }

    public StatusDonjon attaquer(Donjon DJ, int[] posAtt)
    {
        StatusDonjon val = StatusDonjon.NORMAL;

        Arme arme = getArmeEquipe();

        if (arme != null) {
            _affichage.afficher(true);
                    this._deChar.changeDe(1, 20);
            int touche;
            int tou;

            if (arme.getTypeArm().isPresent() && arme.getTypeArm().get().equals(TypeArme.DISTANCE)) {
                tou = this._stats.retDex();
            } else {
                tou = this._stats.retFor();
            }
            touche = tou;

            try {
                int detou = this._deChar.roll() + arme.getBonusMagique();
                touche += detou;
                Monstre mons = DJ.getMons(posAtt);
                if (mons != null) {
                    if (((posAtt[0] >= _pos.getAbscisse() - arme.getRange()) && (posAtt[0] <= _pos.getAbscisse() + arme.getRange()) && ((posAtt[1] >= _pos.getOrdonnee() - arme.getRange()) && (posAtt[1] <= _pos.getOrdonnee() + arme.getRange())))) {
                        if (touche > mons.getArmorClass()) {
                            _affichage.afficher(true, this._nom, " perce l'armure de ", mons, " (jet de touche : ", tou, " + ", detou, " = ", touche, ").");
                            this._deChar.changeDe(arme.getDegats()[0], arme.getDegats()[1]);
                            int atk = this._deChar.roll() + arme.getBonusMagique();
                            int bonus = arme.getBonusMagique();
                            _affichage.afficher(true, this._nom, " fait ", (atk - bonus), " + ", bonus, " = ", atk, " dégats à ", mons, " !");
                            val = mons.seFaitAttaquer(atk, DJ);
                        } else {
                            _affichage.afficher(true, this._nom, " ne perce pas l'armure de ", mons, " (jet de touche : ", tou, " + ", detou, " = ", touche, ").");
                        }
                    } else {
                        _affichage.afficher(true, this._nom, " n'a pas une arme à la portée suffisante.");
                    }
                } else {
                    _affichage.afficherRouge(true, "Il n'y a pas de monstre à attaquer sur cette case.");
                }
            }
            catch (ArrayIndexOutOfBoundsException erreur)
            {
                _affichage.afficherRouge(true, "\nLes cases sont dans le format suivant : [lettre][nombre]");
                return StatusDonjon.ERREUR_CHOIX_CASE;
            }
        } else {
            _affichage.afficherRouge(true, this._nom, " n'a pas d'arme équipée.");
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
            val = DJ.tuerPerso(this);
        }
        else
        {
            _affichage.afficher(true, "\n" + this + " n'a plus que " + _stats.retPv() + "/" + _stats.retPvT() + " PV.");
        }
        return val;
    }

    public void seSoigner(int soin) {
        this._stats.pv(Math.min(this._stats.retPv() + soin, this._stats.retPvT()));
    }



    public void peutRamasser(Equipement equip)
    {
        _affichage.afficher(true, "Je peux ramasser un objet!");
        _peutRamEqu = equip;
        _peutRamasser = true;
    }

    public void ramasser(Donjon DJ)
    {
        _stock.add(_peutRamEqu);
        DJ.ramasserEquipement(_peutRamEqu);
        _affichage.afficher(true, _peutRamEqu.getName(), " à été ramassé");
        _peutRamasser = false;
        _peutRamEqu = null;
    }

    public void comAction(String comAct)
    {
        _affichage.afficher(true, this, " - ", comAct);
    }

    public void regePV()
    {
        _stats.pv(_stats.retPvT());
    }



    public Arme getArmeEquipe() {
        for (Equipement equip : this._equipee) {
            if (equip.getTypeEquip().equals(TypeEquipement.ARME)) {
                return (Arme) equip;
            }
        }
        return null;
    }

    public Armure getArmureEquipe() {
        for (Equipement equip : this._equipee) {
            if (equip.getTypeEquip().equals(TypeEquipement.ARMURE)) {
                return (Armure) equip;
            }
        }
        return null;
    }

    public ArrayList<Equipement> getStock()
    {
        return _stock;
    }

    public ArrayList<Equipement> getEquipees()
    {
        return _equipee;
    }

    public int getArmorClass() {
        if (getArmureEquipe() != null) {
            return getArmureEquipe().getArmorClass();
        }
        return 0;
    }

    public String getEquipeeString() {
        StringBuilder porte = new StringBuilder();
        for (Equipement equip : this._equipee) {
            porte.append(equip.toString()).append("\n");
        }
        return porte.toString();
    }

    public String getStockString() {
        StringBuilder porte = new StringBuilder();
        for (Equipement equip : this._stock) {
            porte.append(equip.toString()).append("\n");
        }
        return porte.toString();
    }


    public ArrayList<Sort> getSorts() {
        return _sorts;
    }

    public String getStat() {
        return _cl.jaune("\n\n===== " + this + " =====\n") + "\nPv : " + _stats.retPv() + "/" + _stats.retPvT() + "\nForce : " + _stats.retFor() + "\nDexterite : " + _stats.retDex() + "\nVitesse : " + _stats.retVit() + "\nInitiative : " + _stats.retIni()  + "\nClasse d'armure : " + _stats.retArm();
    }

    public String getInfos()
    {
        return getStat() + "\n\nEquipement :\n" + getEquipeeString() + "\nInventaire :\n" + getStockString();
    }

    public String getLilInfos()
    {
        return (aff() + "    " + _nom + " (" + _gre.genrer(_classe.getCla()) + " " + _gre.genrer(_race.getRa()) + " " + _stats.retPv() + "/" + _stats.retPvT() + ")" + "\n");
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

    public String getClasse()
    {
        return _classe.getCla();
    }

    public boolean peutRam()
    {
        return _peutRamasser;
    }

    public TypeVivant getTypeVivant()
    {
        return TypeVivant.PERSONNAGE;
    }


    public String aff()
    {
        if (_nom.length() >= 3)
        {
            return _nom.substring(0, 3);
        }
        else if (_nom.length() == 2)
        {
            return _nom + " ";
        }
        return " " + _nom + " ";
    }

    @Override
    public String toString() {
        return _nom;
    }

}
