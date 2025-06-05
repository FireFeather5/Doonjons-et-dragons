package Utils;

import de.De;
import donjon.*;
import entite.Vivant;
import entite.equipement.Equipement;
import entite.personnages.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Setup {
    private int _nbPersonnages;
    private int _nbEtreVivants;
    private final ArrayList<Vivant> _etreVivants = new ArrayList<>();
    private final Inputs _inputs = new Inputs();
    private final Couleurs _couleur = new Couleurs();
    private final MJ _mj = new MJ();
    private final Scanner _scanner = new Scanner(System.in);

    public Setup() {}

    public ArrayList<Personnage> setupPersonnages() {
        ArrayList<Personnage> personnages = new ArrayList<>();
        this._nbPersonnages = 0;
        while (_nbPersonnages == 0) {
            System.out.println("\nCombien de personnages voulez-vous créer ?");
            try {
                _nbPersonnages = Integer.parseInt(_scanner.nextLine());
                if (_nbPersonnages == 0) {
                    System.out.println(_couleur.rouge() + "\nIl doit y avoir au moins un personnage !" + _couleur.reset());
                }
            } catch (NumberFormatException | NullPointerException erreur) {
                System.out.println(_couleur.rouge() + "Mauvaise entrée clavier" + _couleur.reset());
            }
        }

        CreaPerso createurPersonnage = new CreaPerso();

        for (int i = 0; i < _nbPersonnages; i++) {
            System.out.println("\n\nCréation Personnage " + (i+1));
            Personnage pers = createurPersonnage.CreaPers();
            System.out.println(pers.getInfos());

            System.out.println("\nVoulez-vous équiper un equipement ? (o/n)");
            String choix = _scanner.nextLine();

            if (choix.equals("o"))
            {
                Equipement equip = _inputs.equiperEquip(pers);
                pers.sEquiper(equip);

                System.out.println("\nVoulez-vous équiper un autre equipement ? (o/n)");
                String choixx = _scanner.nextLine();
                if (choixx.equals("o"))
                {
                    Equipement equipe = _inputs.equiperEquip(pers);
                    pers.sEquiper(equipe);
                }
                else if (!choixx.equals("n"))
                {
                    System.out.println(_couleur.rouge() + "Mauvaise valeur rentrée. hu" + _couleur.reset());
                    System.out.println("Recommencez");
                    choix = _scanner.nextLine();
                }
            }
            else if (!choix.equals("n"))
            {
                System.out.println(_couleur.rouge() + "Mauvaise valeur rentrée. ho" + _couleur.reset());
                System.out.println("Recommencez");
                choix = _scanner.nextLine();
            }
            personnages.add(pers);
        }
        return personnages;
    }

    public Donjon setupDonjon(ArrayList<Personnage> personnages, int tour) {

        System.out.print("\n\n");
        System.out.println(_couleur.jaune() + "-------------------------------------------------------------------");
        System.out.print("\n                        Donjon n°" + tour + "\n\n");
        System.out.println("-------------------------------------------------------------------\n" + _couleur.reset());

        Donjon donjon  = _inputs.creationDonjon(_mj);

        if (donjon == null)
        {
            CreationDonjonDefault creaDj = new CreationDonjonDefault();
            System.out.println("\n\nVoulez-vous que le donjon soit créé aléatoirement (l'un des donjons par défaut sera utilisé sinon) ? (o/n)");
            String choix = _scanner.nextLine();
            if (choix.equals("o")) {
                donjon = creaDj.donjonRandom();
            }
            else {
                donjon = creaDj.createDefaultDJ();
            }
        }
        else
        {
            _inputs.ajoutObstacle(donjon, _mj);
            _inputs.ajoutMonstre(donjon, _mj);
            _inputs.ajoutEquipement(donjon, _mj);
        }

        for (int i = 0; i < _nbPersonnages; i++) {
            int[] pos = _inputs.choixCase("de " + personnages.get(i));
            _mj.posJ(donjon, personnages.get(i), pos);
            donjon.afficherDJ();
            _etreVivants.add(personnages.get(i));
        }

        _etreVivants.addAll(donjon.getListeMonstre());


        this._nbEtreVivants = _nbPersonnages + donjon.getListeMonstre().size();

        return donjon;
    }

    public ArrayList<Vivant> setupInitiative() {
        for (int j = 0; j < _nbEtreVivants; j++) {
            System.out.println(_etreVivants.get(j).getStat());
        }

        De deIni = new De(1, 20);

        ArrayList<Integer> ArrIni = new ArrayList<>();
        ArrayList<Vivant> VivTri = new ArrayList<>();

        System.out.println(_couleur.jaune() + "\n\n===== Choix de l'ordre de jeu =====" + _couleur.reset());

        for (int j = 0; j < _nbEtreVivants; j++) {
            System.out.println("\n" + _etreVivants.get(j).toString() + " : ");
            int init = _etreVivants.get(j).getIni();
            init += deIni.roll();

            if (ArrIni.isEmpty()) {
                ArrIni.add(init);
                VivTri.add(_etreVivants.get(j));
            } else {
                boolean inVivTri = false;
                for (int i = 0; i < ArrIni.size(); i++) {
                    if (!inVivTri) {
                        if (init > ArrIni.get(i)) {
                            ArrIni.add(i, init);
                            VivTri.add(i, _etreVivants.get(j));
                            inVivTri = true;
                        }
                    }
                }
                if (!inVivTri) {
                    ArrIni.add(init);
                    VivTri.add(_etreVivants.get(j));
                }
            }
        }
        return VivTri;
    }
}
