package utils;

import de.De;
import donjon.*;
import entite.Vivant;
import entite.equipement.Equipement;
import entite.personnages.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Setup {
    private int _nbPersonnages;
    private int _nbEtreVivants;
    private final ArrayList<Vivant> _etreVivants = new ArrayList<>();
    private final Inputs _inputs = new Inputs();
    private final MJ _mj = new MJ();
    private final Scanner _scanner = new Scanner(System.in);
    private final Affichage _affichage = new Affichage(SortieAffichage.CONSOLE);

    public Setup() {}

    public ArrayList<Personnage> setupPersonnages() {
        ArrayList<Personnage> personnages = new ArrayList<>();
        this._nbPersonnages = 0;
        while (_nbPersonnages == 0) {
            _affichage.afficher(true, "\nCombien de personnages voulez-vous créer ?");
            try {
                _nbPersonnages = _scanner.nextInt();
                _scanner.nextLine();
                if (_nbPersonnages == 0) {
                    _affichage.afficherRouge(true, "\nIl doit y avoir au moins un personnage !");
                }
            } catch (InputMismatchException | NumberFormatException | NullPointerException erreur) {
                _affichage.afficherRouge(true, "Mauvaise entrée clavier");
                _scanner.nextLine();
            }
        }

        CreaPerso createurPersonnage = new CreaPerso();

        for (int i = 0; i < _nbPersonnages; i++) {
            _affichage.afficher(true, "\n\nCréation Personnage ", i+1);
            Personnage personnage = createurPersonnage.CreaPers();
            _affichage.afficher(true, personnage.getInfos());

            _affichage.afficher(true, "\nVoulez-vous équiper un equipement ? (o/n)");
            String choix = _scanner.nextLine();
            while (!choix.equals("n")) {
                if (choix.equals("o")) {
                    Equipement equip = _inputs.equiperEquip(personnage);
                    personnage.sEquiper(equip);

                    _affichage.afficher(true, "\nVoulez-vous équiper un autre equipement ? (o/n)");
                }
                else {
                    _affichage.afficherRouge(true, "Mauvaise valeur rentrée");
                    _affichage.afficher(true, "Recommencez");
                }
                choix = _scanner.nextLine();
            }
            personnages.add(personnage);
        }
        return personnages;
    }

    public Donjon setupDonjon(ArrayList<Personnage> personnages, int tour) {
        try {
            String text = _inputs.contextDonjon();
            if (text.isEmpty()) {
                text = "Pas de contexte !";
            }
            _mj.presContext(text);
        }
        catch (NoSuchElementException erreur) {
            _affichage.afficherRouge(true, "Erreur dans le contexte !");
            return setupDonjon(personnages, tour);
        }

        for (Personnage personnage : personnages) {
            personnage.regePV();
        }

        _affichage.afficher(true, "\n\n");
        _affichage.afficherJaune(true, "-------------------------------------------------------------------\n");
        _affichage.afficherJaune(false, "                        Donjon n°", tour, "\n\n");
        _affichage.afficherJaune(true, "-------------------------------------------------------------------\n");

        Donjon donjon  = _inputs.creationDonjon(_mj);

        if (donjon == null)
        {
            CreationDonjonDefault createurDonjon = new CreationDonjonDefault();
            _affichage.afficher(true, "\n\nVoulez-vous que le donjon soit créé aléatoirement (l'un des donjons par défaut sera utilisé sinon) ? (o/n)");
            String choix = _scanner.nextLine();
            if (choix.equals("o")) {
                donjon = createurDonjon.donjonRandom();
            }
            else {
                donjon = createurDonjon.createDefaultDJ();
            }
        }
        else
        {
            _inputs.ajoutObstacle(donjon, _mj);
            _inputs.ajoutMonstre(donjon, _mj);
            _inputs.ajoutEquipement(donjon, _mj);
        }

        for (int i = 0; i < _nbPersonnages; i++) {
            boolean ok = false;
            while (!ok) {
                int[] pos = _inputs.choixCase("de " + personnages.get(i));
                ok = _mj.posJ(donjon, personnages.get(i), pos);
            }
            donjon.afficherDJ();
            _etreVivants.add(personnages.get(i));
        }

        _etreVivants.addAll(donjon.getListeMonstre());


        this._nbEtreVivants = _nbPersonnages + donjon.getListeMonstre().size();

        return donjon;
    }

    public ArrayList<Vivant> setupInitiative() {
        for (int j = 0; j < _nbEtreVivants; j++) {
            _affichage.afficher(true, _etreVivants.get(j).getStat());
        }

        De deInitiative = new De(1, 20);

        ArrayList<Integer> listeInitiatives = new ArrayList<>();
        ArrayList<Vivant> vivantsTries = new ArrayList<>();

        _affichage.afficherJaune(true, "\n\n===== Choix de l'ordre de jeu =====");

        for (int j = 0; j < _nbEtreVivants; j++) {
            _affichage.afficher(true, "\n" + _etreVivants.get(j).toString() + " : ");
            int init = _etreVivants.get(j).getIni();
            init += deInitiative.roll();

            if (listeInitiatives.isEmpty()) {
                listeInitiatives.add(init);
                vivantsTries.add(_etreVivants.get(j));
            } else {
                boolean inVivTri = false;
                for (int i = 0; i < listeInitiatives.size(); i++) {
                    if (!inVivTri) {
                        if (init > listeInitiatives.get(i)) {
                            listeInitiatives.add(i, init);
                            vivantsTries.add(i, _etreVivants.get(j));
                            inVivTri = true;
                        }
                    }
                }
                if (!inVivTri) {
                    listeInitiatives.add(init);
                    vivantsTries.add(_etreVivants.get(j));
                }
            }
        }
        return vivantsTries;
    }
}
