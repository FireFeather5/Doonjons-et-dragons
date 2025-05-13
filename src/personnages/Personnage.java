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
    private final ArrayList<Equipement> _porte;

    public Personnage(String nom, Races race, Classe classe)
    {
        _nom = nom;
        _race = race;
        _classe = classe;
        _stock = new ArrayList<>();
        _porte = new ArrayList<>();

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
    }

    public void seDeplacer()
    {
        // besoin de la classe qui gère le donjon
    }

    private Arme getArmeEquipe() {
        for (Equipement equip : this._porte) {
            if (equip instanceof Arme) {
                return (Arme) equip;
            }
        }
        return null;
    }

    public void attaquer(Monstre mons, Integer dist) {
        if (this.getArmeEquipe() != null) {
            Arme arme = getArmeEquipe();
            for (Equipement equip : this._porte) {
                if (equip instanceof Arme) {
                    arme = (Arme) equip;
                }
            }
            int atk = arme.getDegats().roll();
            if (arme instanceof ArmeDistance) {
                atk += this._stats[2];
            }
            else {
                atk += this._stats[1];
            }
            System.out.println(this._nom + " attaque a hauteur de " + atk + " dégats !");
        }
        else {
            System.out.println("Vous n'avez pas d'arme équipée");
        }
    }

    public void ramasser()
    {
        // besoin de la classe qui gère le donjon et des classes armements
    }

    public String getStat() {
        return "pv : " + this._stats[0] + ", force : " + this._stats[1] + ", dexterite : " + this._stats[2] + ", vitesse : " + this._stats[3] + ", initiative : " + this._stats[4];
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

    @Override
    public String toString() {
        return _nom;
    }

}
