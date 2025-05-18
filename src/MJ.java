import de.De;
import donjon.Donjon;
import entite.Obstacle;
import entite.equipement.Equipement;
import entite.Monstre;
import entite.personnages.Personnage;

import java.util.ArrayList;
import java.util.Scanner;

public class MJ {

    Scanner sc = new Scanner(System.in);
    ArrayList<String> _monstresCrees = new ArrayList<>();

    public MJ()
    {

    }

    public void createDJ(Donjon DJ)
    {
        System.out.println("taille cote 1");
        String tc1s = sc.nextLine();
        System.out.println("taille cote 2");
        String tc2s = sc.nextLine();
        int tc2 = Integer.parseInt(tc1s);
        int tc1 = Integer.parseInt(tc2s);

        if (((15 <= tc1) && (tc1 <= 25)) && ((15 <= tc2) && (tc2 <= 25)))
        {
            DJ.creaDonjon(tc1, tc2);
        }
        else
        {
            System.out.println("Erreur dans la taille du donjon");
            this.createDJ(DJ);
        }
    }

    public String choixPos(String txt)
    {
        System.out.println("\n\nposition " + txt);
        String pc = sc.nextLine();

        return pc;
    }

    public void addObst(Donjon DJ)
    {
        String pos = choixPos(" de l'obstacle");

        Obstacle obs = new Obstacle();
        obs.addPos(pos, DJ);
    }

    public void createM(Monstre mons)
    {
        System.out.println("\n\n===== Nouveau Monstre =====");
        System.out.println("espèce ?");
        String espece = sc.nextLine();
        System.out.println("portée de l'attaque ?");
        int portee = Integer.parseInt(sc.nextLine());
        System.out.println("nombre de dés pour le calcul de l'attaque ?");
        int nbrDeDeg = Integer.parseInt(sc.nextLine());
        System.out.println("nombre de face pour les dés pour le calcul de l'attaque ?");
        int nbrFaceDeDeg = Integer.parseInt(sc.nextLine());
        System.out.println("nombre de dés pour le calcul des charactéristiques ?");
        int nbrDeCha = Integer.parseInt(sc.nextLine());
        System.out.println("nombre de face pour les dés pour le calcul des charactéristiques ?");
        int nbrFaceDeCha = Integer.parseInt(sc.nextLine());

        mons.creaMonstre(espece, portee, new De(nbrDeDeg, nbrFaceDeDeg), new De(nbrDeCha, nbrFaceDeCha));

        for (String monstre : this._monstresCrees) {
            if (monstre.equals(espece)) {
                mons.multiMonstre();
            }
        }
        this._monstresCrees.add(espece);
    }

    public void posJ(Donjon DJ, Personnage perso)
    {
        String pos = choixPos(" de " + perso.aff());

        boolean test = DJ.posJ(pos, perso);

        if (!test)
        {
            System.out.println("Erreur dans la selection de la position");
            this.posJ(DJ, perso);
        }
    }

    public void posM(Donjon DJ, Monstre mons)
    {
        String pos = choixPos(" de " + mons.toString());

        boolean test = DJ.posM(pos, mons);

        if (!test)
        {
            System.out.println("Erreur dans la selection de la position");
            this.posM(DJ, mons);
        }
    }

    public void posEquip(Donjon DJ, Equipement equip)
    {
        String pos = choixPos(" de " + equip.getName());

        boolean test = DJ.posE(pos, equip);

        if (!test)
        {
            System.out.println("Erreur dans la selection de la position");
            this.posEquip(DJ, equip);
        }
    }

    public void presContext()
    {
        System.out.println("Quel est le context ?");
        String context = sc.nextLine();
    }
}
