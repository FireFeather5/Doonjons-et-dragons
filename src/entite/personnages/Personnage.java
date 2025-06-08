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

        System.out.println(_cl.jaune() + "\n===== caractéristiques perso =====" + _cl.reset());
        System.out.println("\nLancement d'un dé pour la caractéristique de force.");
        _stats.forc(_deChar.roll() + 3);
        System.out.println("\nLancement d'un dé pour la caractéristique de dextérité.");
        _stats.dex(_deChar.roll() + 3);
        System.out.println("\nLancement d'un dé pour la caractéristique de vitesse.");
        _stats.vit(_deChar.roll() + 3);
        System.out.println("\nLancement d'un dé pour la caractéristique d'initiative.");
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

    public void setPosition(int pos1, int pos2)
    {
        _pos.changPos(pos1, pos2);
    }



    public Erreurs seDeplacer(Donjon DJ, int[] pos)
    {
        if (_stats.retVit() < 3)
        {
            return Erreurs.VITESSE_SOUS_3;
        }

        try {
            int distDep = _stats.retVit() / 3;

            int[] posOld = getPos();

            if (((pos[0] >= _pos.getAbscisse() - distDep) && (pos[0] <= _pos.getAbscisse() + distDep)) && ((pos[1] >= _pos.getOrdonnee() - distDep) && (pos[1] <= _pos.getOrdonnee() + distDep))) {

                //if (!_peutRamasser) {
                    boolean val = DJ.positionVivant(pos, this);
                    if (val) {
                        DJ.emptyCase(posOld);          //vide la case précédement utilisée par le perso
                        return Erreurs.TOUT_OK;
                    } else {
                        return Erreurs.PROBLEME_CASE;
                    }
                /*} else {
                    boolean val = DJ.positionVivant(pos, this);
                    if (val) {
                        DJ.emptyCase(posOld);          //vide la case précédement utilisée par le perso
                        return Erreurs.TOUT_OK;
                    } else {
                        return Erreurs.PROBLEME_CASE;
                    }
                }*/
            } else {
                return Erreurs.PROBLEME_CASE;
            }
        }
        catch (NullPointerException erreur) {
            return Erreurs.PROBLEME_CASE;
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
    }

    public boolean sEquiper(Equipement equipement)
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
                return true;
            } else {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public StatusDonjon attaquer(Donjon DJ, Monstre mons)
    {
        StatusDonjon val = StatusDonjon.NORMAL;

        Arme arme = getArmeEquipe();

        if (arme != null)
        {
            this._deChar.changeDe(1, 20);
            int touche;
            int tou;

            if (arme.getTypeArm().isPresent() && arme.getTypeArm().get().equals(TypeArme.DISTANCE))
            {
                tou = this._stats.retDex();
            }
            else
            {
                tou = this._stats.retFor();
            }
            touche = tou;

            int detou = this._deChar.roll() + arme.getBonusMagique();
            touche += detou;

            if (((mons.getPos()[0] >= _pos.getAbscisse() - arme.getRange()) && (mons.getPos()[0] <= _pos.getAbscisse() + arme.getRange()) && ((mons.getPos()[1] >= _pos.getOrdonnee() - arme.getRange()) && (mons.getPos()[1] <= _pos.getOrdonnee() + arme.getRange()))))
            {
                if (touche > mons.getArmorClass())
                {
                    System.out.println(_nom + " perce l'armure de " + mons + " (jet de touche : " + tou + " + " + detou + " = " + touche + ").");

                    this._deChar.changeDe(arme.getDegats()[0], arme.getDegats()[1]);
                    int atk = this._deChar.roll() + arme.getBonusMagique();
                    int bonus = arme.getBonusMagique();
                    System.out.println(_nom + " fait " + atk + " dégats à " + mons + " !");

                    val = mons.seFaitAttaquer(atk, DJ);
                }
                else
                {
                    System.out.println(_nom + " ne perce pas l'armure de " + mons + " (jet de touche : " + tou + " + " + detou + " = " + touche + ").");
                }
            }
            else
            {
                System.out.println(_nom + " n'a pas une arme à la portée suffisante.");
            }
        }
        else
        {
            System.out.println(_cl.rouge() + _nom + " n'a pas d'arme équipée." + _cl.reset());
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
            val = DJ.tuerPerso(this);
        }
        else
        {
            System.out.println("\n" + this + " n'a plus que " + _stats.retPv() + "/" + _stats.retPvT() + " PV.");
        }
        return val;
    }

    public void seSoigner(int soin) {
        this._stats.pv(Math.min(this._stats.retPv() + soin, this._stats.retPvT()));
    }

    public void peutRamasser(Equipement equip)
    {
        _peutRamEqu = equip;
        _peutRamasser = true;
    }

    public void ramasser(Donjon DJ)
    {
        _stock.add(_peutRamEqu);
        DJ.ramasserEquipement(_peutRamEqu);
        System.out.println(_peutRamEqu.getName() + " à été ramassé");
        _peutRamasser = false;
        _peutRamEqu = null;
    }

    public void reinilisation()
    {
        _peutRamasser = false;
        _peutRamEqu = null;
    }

    public void comAction(String comAct)
    {
        System.out.println(this + " - " + comAct);
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

    public Equipement getPeutRamEqu()
    {
        return _peutRamEqu;
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
        return _cl.jaune() + "\n\n===== " + this + " =====\n" + _cl.reset() + "\nPv : " + _stats.retPv() + "/" + _stats.retPvT() + "\nForce : " + _stats.retFor() + "\nDexterite : " + _stats.retDex() + "\nVitesse : " + _stats.retVit() + "\nInitiative : " + _stats.retIni()  + "\nClasse d'armure : " + _stats.retArm();
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
