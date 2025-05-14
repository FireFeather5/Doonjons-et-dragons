package entite.personnages;

import donjon.Donjon;
import entite.Entite;
import entite.Monstre;
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
    private final Stats _stats;
                        //pv, for, dex, vit, ini
    private Position _pos;
    private final ArrayList<Equipement> _stock;
    private final ArrayList<Equipement> _porte;

    Scanner sc = new Scanner(System.in);


    public Personnage(String nom, Races race, Classe classe)
    {
        _nom = nom;
        _race = race;
        _classe = classe;
        _stock = new ArrayList<>();
        _porte = new ArrayList<>();
        _stats = new Stats();
        _pos = new Position();

        _stats.pv(_classe.pv());
        _stats.add(_race.stat());

        De deChar = new De(4, 4);

        _stats.forc(deChar.roll() + 3);
        _stats.dex(deChar.roll() + 3);
        _stats.vit(deChar.roll() + 3);
        _stats.ini(deChar.roll() + 3);

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

    /*public void seDesequiper(Equipement equipement) {
        if (this._porte.contains(equipement)) {
            this._porte.remove(equipement);
            if (equipement instanceof ArmeGuerre) {
                this._stats[3] += ((ArmeGuerre) equipement).getSpeedMalus();
                this._stats[1] -= ((ArmeGuerre) equipement).getForceBonus();
            }
            else if (equipement instanceof ArmureLourde) {
                this._stats[3] += ((ArmureLourde) equipement).getSpeedMalus();
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
                this._stats[3] -= ((ArmeGuerre) equipement).getSpeedMalus();
                this._stats[1] += ((ArmeGuerre) equipement).getForceBonus();
            }
            else if (equipement instanceof ArmureLourde) {
                this._stats[3] -= ((ArmureLourde) equipement).getSpeedMalus();
            }
            for (Equipement equip : this._porte) {
                if (equip instanceof Arme && equipement instanceof Arme) {
                    this.seDesequiper((Arme) equip);
                    break;
                }
                else if (equip instanceof Armure && equipement instanceof Armure) {
                    this.seDesequiper((Armure) equip);
                    break;
                }
            }
            this._porte.add(equipement);
            this._stock.remove(equipement);
        }
        else {
            System.out.println("ERREUR : l'equipement n'est pas dans l'inventaire");
        }
    }*/

    public void attaquer(Monstre mons, Integer dist)
    {

    }

    public void ramasser()
    {
        // besoin de la classe qui gère le donjon et des classes armements
    }

    public String getStat() {
        return "pv : " + _stats.retPv() + ", force : " + _stats.retFor() + ", dexterite : " + _stats.retDex() + ", vitesse : " + _stats.retVit() + ", initiative : " + _stats.retIni()  + ", classe d'armure : " + _stats.retArm();
    }

    public String getPorte() {
        String porte = "";
        for (Equipement equip : this._porte) {
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
