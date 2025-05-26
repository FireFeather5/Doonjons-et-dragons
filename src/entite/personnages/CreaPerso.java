package entite.personnages;

import entite.personnages.classes.Classe;
import entite.personnages.classes.*;
import entite.personnages.genre.Feminin;
import entite.personnages.genre.Genre;
import entite.personnages.genre.Masculin;
import entite.personnages.races.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CreaPerso {

    private Personnage _pers;
    private Races _ra;
    private Classe _cla;
    private String _nom;
    private Genre _gre;

    Scanner sc = new Scanner(System.in);

    public CreaPerso()
    {
    }

    public Personnage CreaPers(Personnage pers) {

        _pers = pers;
        System.out.println("nom ?");
        _nom = sc.nextLine();
        Race();
        Classe();
        Genre();
        _pers.CreaPers(_nom, _ra, _cla, _gre);
        return _pers;
    }



    public void Race()
    {
        System.out.println("Race ?" + "\n1- Elfe" + "\n2- Halfelin" + "\n3- Humain" + "\n4- Nain");
        String race = sc.nextLine();
        switch (Integer.parseInt(race)) {
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
                System.out.println("Mauvais chiffre choisit.");
                Race();
            }
        }
    }


    public void Classe()
    {
        System.out.println("Classe ?" + "\n1- Clerc" + "\n2- Guerrier" + "\n3- Magicien" + "\n4- Roublard");
        String race = sc.nextLine();
        switch (Integer.parseInt(race)) {
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
                System.out.println("Mauvais chiffre choisit.");
                Classe();
            }
        }
    }

    public void Genre()
    {
        System.out.println("Genre ?" + "\n1- Homme" + "\n2- Femme");
        String race = sc.nextLine();
        switch (Integer.parseInt(race)) {
            case 1: {
                _gre = new Masculin();
                break;
            }
            case 2: {
                _gre = new Feminin();
                break;
            }
            default: {
                System.out.println("Mauvais chiffre choisit.");
                Genre();
            }
        }
    }
}
