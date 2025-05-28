package entite.personnages.classes;

import entite.equipement.Equipement;

import java.util.ArrayList;

public interface Classe {

    int pv();

    ArrayList<Equipement> getEquiBase();

    String getCla();

}
