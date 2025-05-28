package entite.personnages;

import Utils.Couleurs;
import entite.personnages.classes.Classe;
import entite.personnages.classes.*;
import entite.personnages.genre.Feminin;
import entite.personnages.genre.Genre;
import entite.personnages.genre.Masculin;
import entite.personnages.races.*;

import java.util.Scanner;

public class CreaPerso {

    private Races _ra;
    private Classe _cla;
    private Genre _gre;
    private Couleurs _cl = new Couleurs();

    Scanner sc = new Scanner(System.in);

    public CreaPerso()
    {
    }

    public Personnage CreaPers(Personnage pers) {

        System.out.println("\nnom ?");
        String _nom = sc.nextLine();
        Race();
        Classe();
        Genre();
        pers.CreaPers(_nom, _ra, _cla, _gre);
        return pers;
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
                    System.out.println(_cl.rouge() + "Mauvais chiffre choisit." + _cl.reset());
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
                4- Roublard
                5- Assassin""");
        try {
            int classe = Integer.parseInt(sc.nextLine());
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
                case 5: {
                    _cla = new Assassin();
                    break;
                }
                default: {
                    System.out.println(_cl.rouge() + "Mauvais chiffre choisit." + _cl.reset());
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
                    _gre = new Masculin();
                    break;
                }
                case 2: {
                    _gre = new Feminin();
                    break;
                }
                default: {
                    System.out.println(_cl.rouge() + "Mauvais chiffre choisit." + _cl.reset());
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
