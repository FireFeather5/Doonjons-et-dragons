package entite.personnages;

import donjon.Donjon;
import entite.Entite;
import entite.Monstre;
import entite.equipement.arme.distance.ArmeDistance;
import entite.personnages.classes.Classe;
import entite.personnages.races.Races;
import entite.equipement.Equipement;
import entite.equipement.arme.Arme;
import entite.equipement.arme.guerre.ArmeGuerre;
import entite.equipement.armure.Armure;
import entite.equipement.armure.lourde.ArmureLourde;
import de.*;
import statistiques.Position;
import statistiques.Stats;

import java.util.Scanner;

import java.util.ArrayList;

public class Personnage implements Entite {

    private final String _nom;
    private final Races _race;
    private final Classe _classe;
    private final De _deChar = new De(4, 4);
    private final Stats _stats;
    //pv, for, dex, vit, ini
    private Position _pos;

    private final ArrayList<Equipement> _stock;
    private final ArrayList<Equipement> _equipee;

    Scanner sc = new Scanner(System.in);


    public Personnage(String nom, Races race, Classe classe)
    {
        _nom = nom;
        _race = race;
        _classe = classe;
        _stock = new ArrayList<>();
        _equipee = new ArrayList<>();
        _stats = new Stats();
        _pos = new Position();

        _stats.pv(_classe.pv());
        _stats.add(_race.stat());


        _stats.forc(_deChar.roll() + 3);
        _stats.dex(_deChar.roll() + 3);
        _stats.vit(_deChar.roll() + 3);
        _stats.ini(_deChar.roll() + 3);

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
            boolean val = DJ.posJ(dep, this);
            if (val)
            {
                DJ.emptyCase(posOld);          //vide la case précédement utilisée par le monstre
                return true;
            }
        }
        return false;           //si faux, redemander une position
    }


    public void recuperer(Equipement equipement) {
        this._stock.add(equipement);
    }

    public void seDesequiper(Equipement equipement) {
        if (this._equipee.contains(equipement)) {
            this._equipee.remove(equipement);
            if (equipement instanceof ArmeGuerre) {
                this._stats.vit(_stats.retVit() + ((ArmeGuerre) equipement).getSpeedMalus());
                this._stats.forc(_stats.retFor() - ((ArmeGuerre) equipement).getForceBonus());
            }
            else if (equipement instanceof ArmureLourde) {
                this._stats.vit(_stats.retVit() + ((ArmureLourde) equipement).getSpeedMalus());
            }
            this._stock.add(equipement);
        }
        else {
            System.out.println("ERREUR : l'equipement n'est pas équipée");
        }
    }

    public void sEquiper(Equipement equipement) {
        if (this._stock.contains(equipement)) {
            if (equipement instanceof ArmeGuerre) {
                this._stats.vit(_stats.retVit() - ((ArmeGuerre) equipement).getSpeedMalus());
                this._stats.forc(_stats.retFor() + ((ArmeGuerre) equipement).getForceBonus());
            }
            else if (equipement instanceof ArmureLourde) {
                this._stats.vit(_stats.retVit() - ((ArmureLourde) equipement).getSpeedMalus());
            }
            for (Equipement equip : this._equipee) {
                if (equip instanceof Arme && equipement instanceof Arme) {
                    this.seDesequiper((Arme) equip);
                    break;
                }
                else if (equip instanceof Armure && equipement instanceof Armure) {
                    this.seDesequiper((Armure) equip);
                    break;
                }
            }
            this._equipee.add(equipement);
            this._stock.remove(equipement);
        }
        else {
            System.out.println("ERREUR : l'equipement n'est pas dans l'inventaire");
        }
    }

    private Arme getArmeEquipe() {
        for (Equipement equip : this._equipee) {
            if (equip instanceof Arme) {
                return (Arme) equip;
            }
        }
        return null;
    }
    private Armure getArmureEquipe() {
        for (Equipement equip : this._equipee) {
            if (equip instanceof Armure) {
                return (Armure) equip;
            }
        }
        return null;
    }

    public void attaquer(Monstre mons, Integer dist) {
        Arme arme = getArmeEquipe();
        if (arme != null) {
            this._deChar.changeDe(1, 20);
            int touche = this._deChar.roll();
            if (arme instanceof ArmeDistance) {
                touche += this._stats.retDex();
            }
            else {
                touche += this._stats.retFor();
            }
            if (arme.getRange() >= dist) {
                if (touche > mons.getArmorClass()) {
                    this._deChar.changeDe(arme.getDegats()[0], arme.getDegats()[1]);
                    int atk = this._deChar.roll();
                    System.out.println(this._nom + " touche le monstre (jet de touche : " + touche + ")");
                    System.out.println(this._nom + " attaque a hauteur de " + atk + " dégats !");
                    mons.seFaitAttaquer(atk);
                } else
                    System.out.println(this._nom + " ne touche pas le monstre (jet de touche : " + touche + ")");
            }
            else {
                System.out.println(this._nom + " n'a pas la portee");
            }
        }
        else {
            System.out.println("Vous n'avez pas d'arme équipée");
        }
    }

    public int getArmorClass() {
        if (getArmureEquipe() != null) {
            return getArmureEquipe().getArmorClass();
        }
        return 0;
    }

    public void seFaitAttaquer(int degats) {
        int pv = _stats.retPv() - degats;
        _stats.pv(pv);
    }

    public void ramasser()
    {
        // besoin de la classe qui gère le donjon et des classes armements
    }

    public String getStat() {
        return "pv : " + _stats.retPv() + ", force : " + _stats.retFor() + ", dexterite : " + _stats.retDex() + ", vitesse : " + _stats.retVit() + ", initiative : " + _stats.retIni()  + ", classe d'armure : " + _stats.retArm();
    }

    public String getEquipee() {
        String porte = "";
        for (Equipement equip : this._equipee) {
            porte += equip.toString() + " ";
        }
        return porte;
    }

    public String getStock() {
        String porte = "";
        for (Equipement equip : this._stock) {
            porte += equip.toString() + " ";
        }
        return porte;
    }

    public String aff()
    {
        return _nom.substring(0, 3);
    }

    @Override
    public String toString() {
        return _nom;
    }

}
