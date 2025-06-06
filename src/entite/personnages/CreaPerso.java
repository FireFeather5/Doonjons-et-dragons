package entite.personnages;

import utils.Affichage;
import entite.personnages.classes.*;
import entite.personnages.genre.Feminin;
import entite.personnages.genre.Genre;
import entite.personnages.genre.Masculin;
import entite.personnages.races.*;
import utils.SortieAffichage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CreaPerso {

    private Races _ra;
    private Classe _cla;
    private Genre _gre;

    private final Scanner sc = new Scanner(System.in);
    private final Affichage _affichage = new Affichage(SortieAffichage.CONSOLE);

    public CreaPerso()
    {
    }

    public Personnage CreaPers() {

        _affichage.afficher(true, "\nnom ?");
        String _nom = sc.nextLine();
        while (_nom.isEmpty())
        {
            _affichage.afficherRouge(true, "Le nom ne peut pas être vide!");
            _affichage.afficher(true, "\nnom ?");
            _nom = sc.nextLine();
        }

        Race();
        Classe();
        Genre();
        return new Personnage(_nom, _ra, _cla, _gre);
    }

    public void Race()
    {
        _affichage.afficher(true, """
               \nRace ?
                1- Elfe
                2- Halfelin
                3- Humain
                4- Nain""");
        try {
            int race = sc.nextInt();
            sc.nextLine();
            switch (race) {
                case 1: {
                    _ra = new Elfes();
                    break;
                }
                case 2: {
                    _ra = new Halfelins();
                    break;
                }
                case 3: {
                    _ra = new Humain();
                    break;
                }
                case 4: {
                    _ra = new Nain();
                    break;
                }
                default: {
                    _affichage.afficherRouge(true, "Mauvais chiffre choisit.");
                    Race();
                }
            }
        }
        catch (InputMismatchException | NumberFormatException | NullPointerException erreur) {
            _affichage.afficherRouge(true, "Mauvais choix de race");
            Race();
        }
    }

    public void Classe()
    {
        _affichage.afficher(true, """
                \nClasse ?
                1- Clerc
                2- Guerrier
                3- Magicien
                4- Roublard""");
        try {
            int classe = sc.nextInt();
            sc.nextLine();
            switch (classe) {
                case 1: {
                    _cla = new Clerc();
                    break;
                }
                case 2: {
                    _cla = new Guerrier();
                    break;
                }
                case 3: {
                    _cla = new Magicien();
                    break;
                }
                case 4: {
                    _cla = new Roublard();
                    break;
                }
                default: {
                    _affichage.afficherRouge(true, "Mauvais chiffre choisit.");
                    Classe();
                }
            }
        }
        catch (InputMismatchException | NumberFormatException | NullPointerException erreur) {
            _affichage.afficherRouge(true, "Mauvais choix de classe");
            Classe();
        }
    }

    public void Genre()
    {
        _affichage.afficher(true, """
                \nGenre ?
                1- Homme
                2- Femme""");
        try {
            int gre = sc.nextInt();
            sc.nextLine();
            switch (gre) {
                case 1: {
                    _gre = new Masculin();
                    break;
                }
                case 2: {
                    _gre = new Feminin();
                    break;
                }
                default: {
                    _affichage.afficherRouge(true, "Mauvais chiffre choisit.");
                    Genre();
                }
            }
        }
        catch (InputMismatchException | NumberFormatException | NullPointerException erreur) {
            _affichage.afficherRouge(true, "Mauvais choix de classe");
            Genre();
        }
    }
}
