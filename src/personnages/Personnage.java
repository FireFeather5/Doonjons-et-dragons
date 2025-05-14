package personnages;

import equipement.Equipement;
import equipement.arme.Arme;
import equipement.arme.distance.ArmeDistance;
import equipement.arme.guerre.ArmeGuerre;
import equipement.armure.Armure;
import equipement.armure.lourde.ArmureLourde;
import personnages.races.*;
import personnages.classes.*;
import de.*;
import monstres.*;

import java.util.ArrayList;

public class Personnage {

    private final String _nom;
    private final Races _race;
    private final Classe _classe;
    private final int[] _stats = {0, 0, 0, 0, 0};
                             //pv, for, dex, vit, ini

    private final ArrayList<Equipement> _stock;
    private final ArrayList<Equipement> _equipee;

    public Personnage(String nom, Races race, Classe classe)
    {
        _nom = nom;
        _race = race;
        _classe = classe;
        _stock = new ArrayList<>();
        _equipee = new ArrayList<>();

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

    public void recuperer(Equipement equipement) {
        this._stock.add(equipement);
    }

    public void seDesequiper(Equipement equipement) {
        if (this._equipee.contains(equipement)) {
            this._equipee.remove(equipement);
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

    public void seDeplacer()
    {
        // besoin de la classe qui gère le donjon
    }

    private Arme getArmeEquipe() {
        for (Equipement equip : this._equipee) {
            if (equip instanceof Arme) {
                return (Arme) equip;
            }
        }
        return null;
    }

    public void attaquer(Monstre mons, Integer dist) {
        Arme arme = getArmeEquipe();
        if (arme != null) {
            int touche = new De(20).roll();
            if (arme instanceof ArmeDistance) {
                touche += this._stats[2];
            }
            else {
                touche += this._stats[1];
            }
            if (arme.getRange() >= dist) {
                if (touche > mons.getArmorClass()) {
                    int atk = arme.getDegats().roll();
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

    public void seFaitAttaquer(int degats) {
        this._stats[0] -= degats;
    }

    public void ramasser()
    {
        // besoin de la classe qui gère le donjon et des classes armements
    }

    public String getStat() {
        return "pv : " + this._stats[0] + ", force : " + this._stats[1] + ", dexterite : " + this._stats[2] + ", vitesse : " + this._stats[3] + ", initiative : " + this._stats[4];
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

    @Override
    public String toString() {
        return _nom;
    }

}
