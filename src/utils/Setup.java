package utils;

import de.De;
import donjon.*;
import entite.Vivant;
import entite.equipement.Equipement;
import entite.personnages.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Setup {
    private int _nbPersonnages;
    private int _nbVivants;
    private ArrayList<Vivant> _vivant;
    private final Inputs _input = new Inputs();
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
            Personnage personnage = createurPersonnage.CreaPers();
            System.out.println(personnage.getInfos());

            System.out.println("\nVoulez-vous équiper un equipement ? (o/n)");
            String choix = _scanner.nextLine();

            while (!choix.equals("n"))
            {
                if (choix.equals("o"))
                {
                    Equipement equip = _input.equiperEquip(personnage);
                    personnage.sEquiper(equip);

                    System.out.println("\nVoulez-vous équiper un autre equipement ? (o/n)");
                    String choixx = _scanner.nextLine();

                    while (!choixx.equals("n"))
                    {
                        if (choixx.equals("o"))
                        {
                            Equipement equipe = _input.equiperEquip(personnage);
                            personnage.sEquiper(equipe);
                            choixx = "n";
                        }
                        else
                        {
                            System.out.println(_couleur.rouge() + "Mauvaise valeur rentrée." + _couleur.reset());
                            System.out.println("Recommencez");
                            choixx = _scanner.nextLine();
                        }
                    }
                    choix = "n";
                }
                else
                {
                    System.out.println(_couleur.rouge() + "Mauvaise valeur rentrée." + _couleur.reset());
                    System.out.println("Recommencez");
                    choix = _scanner.nextLine();
                }
            }

            personnages.add(personnage);
        }
        return personnages;
    }

    public Donjon setupDonjon(ArrayList<Personnage> personnages, int tour)
    {
        _vivant = new ArrayList<>();

        for (Personnage personnage : personnages)
        {
            personnage.regenerationPerso();
            personnage.reinilisationRamasser();
        }

        System.out.print("\n\n");
        System.out.println(_couleur.jaune() + "-------------------------------------------------------------------\n");
        System.out.print("                        Donjon n°" + tour + "\n\n");
        System.out.println("-------------------------------------------------------------------\n" + _couleur.reset());

        Donjon donjon  = _input.creationDonjon(_mj);

        if (donjon == null)
        {
            CreationDonjonDefault createurDonjon = new CreationDonjonDefault();
            boolean reussi = false;

            System.out.println("\n\nVoulez-vous que le donjon soit créé aléatoirement (o/n) ? (l'un des donjons par défaut sera utilisé sinon)");
            String choix = _scanner.nextLine();

            while (!reussi)
            {
                if (choix.equals("o"))
                {
                    donjon = createurDonjon.donjonRandom();
                    reussi = true;
                }
                else if (choix.equals("n"))
                {
                    donjon = createurDonjon.creationDonjonDefaut();
                    reussi = true;
                }
                else
                {
                    System.out.println(_couleur.rouge() + "Mauvaise valeur rentrée." + _couleur.reset());
                    System.out.println("Recommencez");
                    choix = _scanner.nextLine();
                }
            }

        }
        else
        {
            _input.ajoutObstacle(donjon, _mj);
            _input.ajoutMonstre(donjon, _mj);
            _input.ajoutEquipement(donjon, _mj);
        }

        for (int i = 0; i < _nbPersonnages; i++)
        {
            boolean ok = false;
            while (!ok)
            {
                int[] pos = _input.choixCase("de " + personnages.get(i));
                ok = _mj.setPositionPerso(donjon, personnages.get(i), pos);
            }
            donjon.afficherDJ();
            _vivant.add(personnages.get(i));
        }

        _vivant.addAll(donjon.getListeMonstre());

        _nbVivants = _nbPersonnages + donjon.getListeMonstre().size();


        String text = _input.contextDonjon();
        if (text.isEmpty()) {
            text = "Pas de contexte !";
        }
        _mj.presContext(text);


        return donjon;
    }

    public ArrayList<Vivant> setupInitiative()
    {
        for (int j = 0; j < _nbVivants; j++)
        {
            System.out.println(_vivant.get(j).getStat());
        }

        De deInitiative = new De(1, 20);

        ArrayList<Integer> listeInitiatives = new ArrayList<>();
        ArrayList<Vivant> vivantsTries = new ArrayList<>();

        System.out.println(_couleur.jaune() + "\n\n===== Choix de l'ordre de jeu =====" + _couleur.reset());

        for (int j = 0; j < _nbVivants; j++)
        {
            System.out.println("\n" + _vivant.get(j).toString() + " : ");
            int init = _vivant.get(j).getInitiative();
            init += deInitiative.roll();

            if (listeInitiatives.isEmpty())
            {
                listeInitiatives.add(init);
                vivantsTries.add(_vivant.get(j));
            }
            else
            {
                boolean inVivTri = false;
                for (int i = 0; i < listeInitiatives.size(); i++)
                {
                    if (!inVivTri)
                    {
                        if (init > listeInitiatives.get(i))
                        {
                            listeInitiatives.add(i, init);
                            vivantsTries.add(i, _vivant.get(j));
                            inVivTri = true;
                        }
                    }
                }
                if (!inVivTri)
                {
                    listeInitiatives.add(init);
                    vivantsTries.add(_vivant.get(j));
                }
            }
        }
        return vivantsTries;
    }
}
