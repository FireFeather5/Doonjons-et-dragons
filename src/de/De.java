package de;

import utils.Affichage;
import utils.Couleurs;
import utils.SortieAffichage;

import java.util.Random;
import java.util.Scanner;

public class De {

    private String _name;
    private int _number;
    private int _faces;

    private final Scanner _scanner = new Scanner(System.in);
    private final Affichage _affichage = new Affichage(SortieAffichage.CONSOLE);
    private final Couleurs _couleur = new Couleurs();

    public De() {
        _name = "1d6";
        _number = 1;
        _faces = 6;
    }

    public De(int faces) {
        _name = "1d" + faces;
        _number = 1;
        _faces = faces;
    }

    public De(int number, int faces) {
        _name = number + "d" + faces;
        _number = number;
        _faces = faces;
    }

    public int roll() {
        Random rand = new Random();
        int total = 0;
        for (int i = 0; i < this._number; i++) {
            total += rand.nextInt(1, this._faces + 1);
        }
        _affichage.afficher(false, "\nappuyez sur entrée pour lancer " + _name);
        _scanner.nextLine();
        if (total < (_number * _faces) / 4) {
            _affichage.afficher(true, "Vous avez fait ", _couleur.rouge(total));
        }
        else if (total < (_number * _faces) / 2) {
            _affichage.afficher(true, "Vous avez fait ", _couleur.jaune(total));
        }
        else if (total < (3 * _number * _faces) / 4) {
            _affichage.afficher(true, "Vous avez fait ", _couleur.vert(total));
        }
        else {
            _affichage.afficher(true, "Vous avez fait ", _couleur.cyan(total));
        }

        return total;
    }

    public void changeDe(int nombre, int faces) {
        this._name = nombre + "d" + faces;
        this._number = nombre;
        this._faces = faces;
    }

    @Override
    public String toString() {
        return "Dé : " + this._name;
    }
}
