package entite.personnages;

import donjon.Donjon;
import entite.Entite;
import entite.Monstre;
import entite.equipement.arme.distance.ArmeDistance;
import entite.personnages.classes.*;
import entite.personnages.genre.Genre;
import entite.personnages.races.Races;
import entite.equipement.Equipement;
import entite.equipement.arme.Arme;
import entite.equipement.arme.guerre.ArmeGuerre;
import entite.equipement.armure.Armure;
import entite.equipement.armure.lourde.ArmureLourde;
import de.*;
import sort.*;
import statistiques.Position;
import statistiques.Stats;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Personnage implements Vivant {

    private String _nom;
    private Races _race;
    private Classe _classe;
    private Genre _gre;
    private final De _deChar = new De(4, 4);
    private final Stats _stats;
    //pv, for, dex, vit, ini
    private Position _pos;

    private Equipement _peutRamEqu = null;
    private boolean _peutRamasser = false;

    private final ArrayList<Equipement> _stock;
    private final ArrayList<Equipement> _equipee;
    private final ArrayList<Sort> _sorts;

    Scanner sc = new Scanner(System.in);

    public Personnage()
    {
        _stock = new ArrayList<>();
        _equipee = new ArrayList<>();
        _stats = new Stats();
        _pos = new Position();
        _sorts = new ArrayList<>();
    }

    public void CreaPers(String nom, Races race, Classe classe, Genre gre)
    {
        _nom = nom;
        _race = race;
        _classe = classe;
        _gre = gre;


        _stats.pvt(_classe.pv());
        _stats.add(_race.stat());

        System.out.println("===== caractéristiques perso =====");
        _stats.forc(_deChar.roll() + 3);
        _stats.dex(_deChar.roll() + 3);
        _stats.vit(_deChar.roll() + 3);
        _stats.ini(_deChar.roll() + 3);

        _stock.addAll(_classe.getEquiBase());


        if (classe instanceof Clerc) {
            this._sorts.add(new Guerison());
        }
        else if (classe instanceof Magicien) {
            this._sorts.add(new Guerison());
            this._sorts.add(new BoogieWoogie());
            this._sorts.add(new ArmeMagique());
        }
    }

    public void position(int pos1, int pos2)
    {
        _pos.changPos(pos1, pos2);
    }

    public int action(Donjon DJ)
    {
        int val = 0;
        //doit pouvoir etre amélioré mais fonctionne pour le moment
        if (_peutRamasser)
        {
            System.out.println("\n\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1\nS'équiper : 2\nSorts : 3\nRamasser : 4");
            try {
                int choix = Integer.parseInt(sc.nextLine());

                    switch (choix) {
                        case 0:
                            seDeplacer(DJ);
                            break;
                        case 1:
                            val = attaquer(DJ);
                            break;
                        case 2:
                            sEquiper();
                            break;
                        case 3:
                            ramasser(DJ);
                            break;
                        case 4:
                            lancerSort(DJ);
                            break;
                        default:
                            System.out.println("Mauvais choix d'action");
                            action(DJ);
                    }
                } catch (NumberFormatException erreur) {
                    System.out.println("Mauvais choix d'action");
                    action(DJ);
                } catch (NullPointerException erreur) {
                    System.out.println("Mauvais choix d'action");
                    action(DJ);
                }
            }
            else {
                System.out.println("\n\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1\nS'équiper : 2\nRamasser : 3");
                try {
                    int choix = Integer.parseInt(sc.nextLine());

                    switch (choix) {
                        case 0:
                            seDeplacer(DJ);
                            break;
                        case 1:
                            val = attaquer(DJ);
                            break;
                        case 2:
                            sEquiper();
                            break;
                        case 3:
                            ramasser(DJ);
                            break;
                        default:
                            System.out.println("Mauvais choix d'action");
                            action(DJ);
                    }
                } catch (NumberFormatException erreur) {
                    System.out.println("Mauvais choix d'action");
                    action(DJ);
                } catch (NullPointerException erreur) {
                    System.out.println("Mauvais choix d'action");
                    action(DJ);
                }
            }
        }
        else
        {
            if (this._classe instanceof Clerc || this._classe instanceof Magicien) {
                System.out.println("\n\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1\nS'équiper : 2\nSorts : 3");

                try {
                    int choix = Integer.parseInt(sc.nextLine());
                    switch (choix) {
                        case 0:
                            seDeplacer(DJ);
                            break;
                        case 1:
                            val = attaquer(DJ);
                            break;
                        case 2:
                            sEquiper();
                            break;
                        case 3:
                            lancerSort(DJ);
                            break;
                        default:
                            System.out.println("Mauvais choix d'action");
                            action(DJ);
                    }
                } catch (NumberFormatException erreur) {
                    System.out.println("Mauvais choix d'action");
                    action(DJ);
                } catch (NullPointerException erreur) {
                    System.out.println("Mauvais choix d'action");
                    action(DJ);
                }
            }
            else {
                System.out.println("\n\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1\nS'équiper : 2");

                try {
                    int choix = Integer.parseInt(sc.nextLine());
                    switch (choix) {
                        case 0:
                            seDeplacer(DJ);
                            break;
                        case 1:
                            val = attaquer(DJ);
                            break;
                        case 2:
                            sEquiper();
                            break;
                        default:
                            System.out.println("Mauvais choix d'action");
                            action(DJ);
                    }
                } catch (NumberFormatException erreur) {
                    System.out.println("Mauvais choix d'action");
                    action(DJ);
                } catch (NullPointerException erreur) {
                    System.out.println("Mauvais choix d'action");
                    action(DJ);
                }
            }
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
                if (!_peutRamasser) {
                    boolean val = DJ.posJ(dep, this);
                    if (val) {
                        DJ.emptyCase(posOld);          //vide la case précédement utilisée par le perso
                        System.out.println("Déplacement effectué");
                    } else {
                        System.out.println("Problème dans le choix de la case");
                        seDeplacer(DJ);
                    }
                } else {
                    boolean val = DJ.posJ(dep, this);
                    if (val) {
                        DJ.emptyCase(posOld);          //vide la case précédement utilisée par le perso
                        DJ.posE(posOld, _peutRamEqu);       //remet l'objet dans la case
                        System.out.println("Déplacement effectué");
                        _peutRamasser = false;
                        _peutRamEqu = null;
                    } else {
                        System.out.println("Problème dans le choix de la case");
                        seDeplacer(DJ);
                    }
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

    public void sEquiper() {
        if (!_stock.isEmpty()) {
            System.out.println("Quel équipement équiper ?");

            int i = 0;
            for (Equipement eqi : _stock) {
                System.out.println(i + " : " + eqi.getName());
                i++;
            }

            try {
                int equ = Integer.parseInt(sc.nextLine());

                Equipement equipement = _stock.get(equ);

                if (this._stock.contains(equipement)) {
                    if (equipement instanceof ArmeGuerre) {
                        this._stats.vit(_stats.retVit() - ((ArmeGuerre) equipement).getSpeedMalus());
                        this._stats.forc(_stats.retFor() + ((ArmeGuerre) equipement).getForceBonus());
                    } else if (equipement instanceof Armure) {
                        _stats.arm(((Armure) equipement).getArmorClass());
                        if (equipement instanceof ArmureLourde) {
                            this._stats.vit(_stats.retVit() - ((ArmureLourde) equipement).getSpeedMalus());
                        }
                    }
                    for (Equipement equip : this._equipee) {
                        if (equip instanceof Arme && equipement instanceof Arme) {
                            this.seDesequiper((Arme) equip);
                            break;
                        } else if (equip instanceof Armure && equipement instanceof Armure) {
                            this.seDesequiper((Armure) equip);
                            break;
                        }
                    }
                    this._equipee.add(equipement);
                    this._stock.remove(equipement);
                    System.out.println(equipement.getName() + " à bien été équipé");
                } else {
                    System.out.println("ERREUR : l'equipement n'est pas dans l'inventaire");
                }
            }
            catch (NullPointerException erreur)
            {
                System.out.println("Mauvaise valeur rentrée.");
                sEquiper();
            }
            catch (NumberFormatException erreur) {
                System.out.println("Mauvaise valeur rentrée.");
                sEquiper();
            }
            catch (IndexOutOfBoundsException erreur) {
                System.out.println("Mauvaise valeur rentrée.");
                sEquiper();
            }
        }
        else {
            System.out.println("\nIl n'y a pas d'équipement à equiper.");
        }
    }

    public void lancerSort(Donjon DJ) {
        if (!this._sorts.isEmpty()) {
            int choix = 1;
            System.out.println("Liste des sorts :");
            for (Sort sort : this._sorts) {
                System.out.println(choix++ + ". " + sort.getNom() + " : " + sort.getDescription());
            }
            System.out.println("Lequel voulez-vous lancer ?");
            try {
                choix = sc.nextInt();
                if (this._classe instanceof Clerc) {
                    if (choix == 1) {
                        int choixPerso = 1;
                        for (Personnage perso : DJ.getListePerso()) {
                            System.out.println(choixPerso + ". " + perso._nom);
                        }
                        choixPerso = sc.nextInt();
                        ((Guerison) this._sorts.getFirst()).lancer(DJ.getListePerso().get(choixPerso - 1));
                    }
                    else {
                        throw new Exception();
                    }
                }
                else if (this._classe instanceof Magicien) {
                    switch (choix) {
                        case 1:
                            int choixPerso = 1;
                            for (Personnage perso : DJ.getListePerso()) {
                                System.out.println("\t" + choixPerso + ". " + perso._nom);
                                choixPerso++;
                            }
                            System.out.println("Choisissez un allie a soigner :");
                            choixPerso = sc.nextInt();
                            ((Guerison) this._sorts.getFirst()).lancer(DJ.getListePerso().get(choixPerso - 1));
                            break;
                        case 2:
                            int choixEntite1 = 1;

                            for (Personnage perso : DJ.getListePerso()) {
                                System.out.println("\t" + choixEntite1 + ". " + perso._nom);
                                choixEntite1++;
                            }
                            int choixEntite2 = choixEntite1;
                            for (Monstre mons : DJ.getListeMonstre()) {
                                System.out.println("\t" + choixEntite2 + ". " + mons.getNom());
                                choixEntite2++;
                            }

                            System.out.println("Choisissez la première entité à téléporter :");
                            choixEntite1 = sc.nextInt();
                            System.out.println("Choisissez la deuxième entité à téléporter :");
                            choixEntite2 = sc.nextInt();

                            if (choixEntite1 <= DJ.getListePerso().size() && choixEntite2 <= DJ.getListePerso().size()) {
                                ((BoogieWoogie) this._sorts.get(1)).lancer(DJ.getListePerso().get(choixEntite1-1), DJ.getListePerso().get(choixEntite2-1), DJ);
                            }
                            else if (choixEntite1 > DJ.getListePerso().size() && choixEntite2 > DJ.getListePerso().size()) {
                                ((BoogieWoogie) this._sorts.get(1)).lancer(DJ.getListeMonstre().get(choixEntite1-1-DJ.getListePerso().size()), DJ.getListeMonstre().get(choixEntite2-1-DJ.getListePerso().size()), DJ);
                            }
                            else if (choixEntite1 <= DJ.getListePerso().size() && choixEntite2 > DJ.getListePerso().size()) {
                                ((BoogieWoogie) this._sorts.get(1)).lancer(DJ.getListePerso().get(choixEntite1-1), DJ.getListeMonstre().get(choixEntite2-1-DJ.getListePerso().size()), DJ);
                            }
                            else {
                                ((BoogieWoogie) this._sorts.get(1)).lancer(DJ.getListeMonstre().get(choixEntite1-1-DJ.getListePerso().size()), DJ.getListePerso().get(choixEntite2-1), DJ);
                            }
                            break;
                        case 3:
                            int choixArme = 1;
                            for (Personnage perso : DJ.getListePerso()) {
                                System.out.println("Personnage : " + perso._nom);
                                for (Equipement equipement : perso._stock) {
                                    if (equipement instanceof Arme) {
                                        System.out.println("\t" + choixArme++ + ". " + equipement.getName());
                                    }
                                }
                                for (Equipement equipement : perso._equipee) {
                                    if (equipement instanceof Arme) {
                                        System.out.println("\t" + "(Equipée) " + choixArme++ + equipement.getName());
                                    }
                                }
                            }
                            System.out.println("Choisissez une arme a améliorer (+1 dgt, +1 touche) :");
                            choixArme = sc.nextInt();
                            boolean ok = false;
                            int idArme = 1;
                            for (Personnage perso : DJ.getListePerso()) {
                                for (Equipement equipement : perso._stock) {
                                    if (equipement instanceof Arme) {
                                        if (choixArme == idArme) {
                                            ((Arme) equipement).bonusMagique();
                                            ok = true;
                                            break;
                                        }
                                        else {
                                            idArme++;
                                        }
                                    }
                                }
                                for (Equipement equipement : perso._equipee) {
                                    if (equipement instanceof Arme) {
                                        if (choixArme == idArme) {
                                            ((Arme) equipement).bonusMagique();
                                            ok = true;
                                            break;
                                        }
                                        else {
                                            idArme++;
                                        }
                                    }
                                }
                                if (!ok) {
                                    throw new Exception();
                                }
                            }
                            break;
                        default:
                            throw new Exception();
                    }
                }
            }
            catch (Exception e) {
                System.out.println("Choix invalide : " + e);
                lancerSort(DJ);
            }

        }
        else {
            System.out.println("Vous n'avez pas de sort...");
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

    public int attaquer(Donjon DJ)
    {
        int val = 0;
        System.out.println("Choisir la case à attaquer");

        try {
            String cas = sc.nextLine();

            Arme arme = getArmeEquipe();

            if (arme != null) {
                System.out.print("\n");
                this._deChar.changeDe(1, 20);
                int touche = this._deChar.roll() + arme.getBonusMagique();

                if (arme instanceof ArmeDistance) {
                    touche += this._stats.retDex();
                } else {
                    touche += this._stats.retFor();
                }

                try {
                    int[] posAtt = DJ.posInt(cas);
                    Monstre mons = DJ.getMons(posAtt);
                    if (mons != null) {
                        if (((posAtt[0] >= _pos.getAbscisse() - arme.getRange()) && (posAtt[0] <= _pos.getAbscisse() + arme.getRange()) && ((posAtt[1] >= _pos.getOrdonnee() - arme.getRange()) && (posAtt[1] <= _pos.getOrdonnee() + arme.getRange())))) {
                            if (touche > mons.getArmorClass()) {
                                System.out.println(this._nom + " perce l'armure de " + mons.toString() + " (jet de touche : " + touche + ").");
                                this._deChar.changeDe(arme.getDegats()[0], arme.getDegats()[1]);
                                int atk = this._deChar.roll() + arme.getBonusMagique();
                                System.out.println(this._nom + " fait " + atk + " dégats à " + mons.toString() + " !");
                                val = mons.seFaitAttaquer(atk, DJ);
                            } else {
                                System.out.println(this._nom + " ne perce pas l'armure de " + mons.toString() + " (jet de touche : " + touche + ").");
                            }
                        } else {
                            System.out.println(this._nom + " n'a pas une arme à la portée suffisante.");
                        }
                    } else {
                        System.out.println("Il n'y a pas de monstre à attaquer sur cette case.");
                        //est ce qu'on rapelle la fonction ?
                    }
                }
                catch (ArrayIndexOutOfBoundsException erreur)
                {
                    System.out.println("\nLes cases sont dans le format suivant : [lettre][nombre]");
                    attaquer(DJ);
                }
            } else {
                System.out.println(this._nom + " n'a pas d'arme équipée.");
            }
            return val;
        }
        catch (NullPointerException erreur)
        {
            System.out.println("\nLes cases sont dans le format suivant : [lettre][nombre]");
            attaquer(DJ);
        }
        return val;
    }

    public int getArmorClass() {
        if (getArmureEquipe() != null) {
            return getArmureEquipe().getArmorClass();
        }
        return 0;
    }

    public int seFaitAttaquer(int degats, Donjon DJ) {
        int val = 0;
        int pv = _stats.retPv() - degats;
        _stats.pv(pv);
        if (pv <= 0)
        {
            System.out.println("\n" + toString() + " à été achevé.");
            val = DJ.tuerPerso(this);
        }
        else
        {
            System.out.println("\n" + toString() + " n'a plus que " + _stats.retPv() + "/" + _stats.retPvT() + " PV.");
        }
        return val;
    }

    public void seSoigner(int soin) {
        if (this._stats.retPv() + soin > this._stats.retPvT()) {
            this._stats.pv(this._stats.retPvT());
        }
        else {
            this._stats.pv(this._stats.retPv() + soin);
        }
    }

    public void peutRamasser(Equipement equip)
    {
        _peutRamEqu = equip;
        _peutRamasser = true;
    }

    public void ramasser(Donjon DJ)
    {
        _stock.add(_peutRamEqu);
        DJ.ramasser(_peutRamEqu);
        System.out.println(_peutRamEqu.getName() + " à été ramassé");
        _peutRamasser = false;
        _peutRamEqu = null;
    }

    public String comAction()
    {
        System.out.println(toString() + " commente l'action effectuée");
        return toString() + " - " + sc.nextLine();
    }

    public String getStat() {
        return "\n\n===== " + toString() + " =====\n\nPv : " + _stats.retPv() + "/" + _stats.retPvT() + "\nForce : " + _stats.retFor() + "\nDexterite : " + _stats.retDex() + "\nVitesse : " + _stats.retVit() + "\nInitiative : " + _stats.retIni()  + "\nClasse d'armure : " + _stats.retArm();
    }

    public String getEquipee() {
        String porte = "";
        for (Equipement equip : this._equipee) {
            porte += equip.toString() + "\n";
        }
        return porte;
    }

    public String getStock() {
        String porte = "";
        for (Equipement equip : this._stock) {
            porte += equip.toString() + "\n";
        }
        return porte;
    }

    public String getInfos()
    {
        return getStat() + "\n\nEquipement :\n" + getEquipee() + "\nInventaire :\n" + getStock();
    }

    public String getLilInfos()
    {
        return (aff() + "    " + _nom + " (" + _gre.genrer(_classe.getCla()) + " " + _gre.genrer(_race.getRa()) + " " + _stats.retPv() + "/" + _stats.retPvT() + ")" + "\n");
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
        return _nom.substring(0, 3);
    }

    public int code()
    {
        return 0;
    }

    @Override
    public String toString() {
        return _nom;
    }

}
