import de.De;
import donjon.Donjon;
import monstres.Monstre;
import personnages.Personnage;

import java.util.Scanner;

public class MJ {

    Scanner sc = new Scanner(System.in);

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
        System.out.println("position " + txt);
        String pc = sc.nextLine();

        return pc;
    }

    public void addObst(Donjon DJ)
    {
        String pos = choixPos(" de l'obstacle");

        boolean test = DJ.addObst(pos);

        if (!test)
        {
            System.out.println("Erreur dans la selection de la position");
            this.addObst(DJ);
        }
    }

    public void createM(Monstre mons)
    {
        System.out.println("===== Nouveau Monstre =====");
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
    }

    public void posJ(Donjon DJ, Personnage perso)
    {
        String pos = choixPos(" de " + perso.getN());

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
}
