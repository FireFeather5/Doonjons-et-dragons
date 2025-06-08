package entite.personnages.classes;

import entite.equipement.Equipement;

import java.util.ArrayList;

public interface Classe {

    int getPv();

    ArrayList<Equipement> getEquiBase();

    String getClasse();

}
