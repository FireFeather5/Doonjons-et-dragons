package de;

import java.util.Random;

public class De {

    private String _name;
    private int _number;
    private int _faces;

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
        return total;
    }

    public void changeDe(int nombre, int faces) {
        this._name = + nombre + "d" + faces;
        this._number = nombre;
        this._faces = faces;
    }

    @Override
    public String toString() {
        return "Dé : " + this._name;
    }
}
