package entite.personnages;

import utils.Couleurs;
import entite.personnages.classes.*;
import entite.personnages.genre.Feminin;
import entite.personnages.genre.Genre;
import entite.personnages.genre.Masculin;
import entite.personnages.races.*;

import java.util.Scanner;

public class CreaPerso {

    private final Couleurs _cl = new Couleurs();

    private Races _race;
    private Classe _classe;
    private Genre _genre;

    private final Scanner sc = new Scanner(System.in);

    public CreaPerso()
    {
    }

    public Personnage CreaPers() {

        System.out.println("\nnom ?");
        String _nom = sc.nextLine();
        while (_nom.isEmpty())
        {
            System.out.println(_cl.rouge() + "Le nom ne peut pas être vide!" + _cl.reset());
            System.out.println("\nnom ?");
            _nom = sc.nextLine();
        }

        Race();
        Classe();
        Genre();
        return new Personnage(_nom, _race, _classe, _genre);
    }

    public void Race()
    {
        System.out.println("""
               \nRace ?
                1- Elfe
                2- Halfelin
                3- Humain
                4- Nain""");
        try {
            int race = Integer.parseInt(sc.nextLine());
            switch (race) {
                case 1: {
                    _race = new Elfes();
                    break;
                }
                case 2: {
                    _race = new Halfelins();
                    break;
                }
                case 3: {
                    _race = new Humain();
                    break;
                }
                case 4: {
                    _race = new Nain();
                    break;
                }
                default: {
                    System.out.println(_cl.rouge() + "Mauvais chiffre choisi." + _cl.reset());
                    Race();
                }
            }
        }
        catch (NumberFormatException | NullPointerException erreur) {
            System.out.println(_cl.rouge() + "Mauvais choix de race" + _cl.reset());
            Race();
        }
    }

    public void Classe()
    {
        System.out.println("""
                \nClasse ?
                1- Clerc
                2- Guerrier
                3- Magicien
                4- Roublard""");
        try {
            int classe = Integer.parseInt(sc.nextLine());
            switch (classe) {
                case 1: {
                    _classe = new Clerc();
                    break;
                }
                case 2: {
                    _classe = new Guerrier();
                    break;
                }
                case 3: {
                    _classe = new Magicien();
                    break;
                }
                case 4: {
                    _classe = new Roublard();
                    break;
                }
                default: {
                    System.out.println(_cl.rouge() + "Mauvais chiffre choisi." + _cl.reset());
                    Classe();
                }
            }
        }
        catch (NumberFormatException | NullPointerException erreur) {
            System.out.println(_cl.rouge() + "Mauvais choix de classe" + _cl.reset());
            Classe();
        }
    }

    public void Genre()
    {
        System.out.println("""
                \nGenre ?
                1- Homme
                2- Femme""");
        try {
            int gre = Integer.parseInt(sc.nextLine());
            switch (gre) {
                case 1: {
                    _genre = new Masculin();
                    break;
                }
                case 2: {
                    _genre = new Feminin();
                    break;
                }
                default: {
                    System.out.println(_cl.rouge() + "Mauvais chiffre choisi." + _cl.reset());
                    Genre();
                }
            }
        }
        catch (NumberFormatException | NullPointerException erreur) {
            System.out.println(_cl.rouge() + "Mauvais choix de classe" + _cl.reset());
            Genre();
        }
    }
}
