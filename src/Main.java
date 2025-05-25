import de.De;
import donjon.Donjon;
import entite.Entite;
import entite.Monstre;
import entite.equipement.Equipement;
import entite.equipement.arme.distance.ArcCourt;
import entite.equipement.arme.guerre.EpeeLongue;
import entite.equipement.armure.legere.ArmureEcaille;
import entite.personnages.CreaPerso;
import entite.personnages.Personnage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenue dans DOOnjon et Dragons");

        MJ test = new MJ();
        Donjon tesssst = new Donjon();
        Monstre mos = new Monstre();
        Equipement arme = new EpeeLongue();
        Equipement armee = new ArcCourt();
        Equipement armure = new ArmureEcaille();

        test.createDJ(tesssst);
        tesssst.afficherDJ();


        System.out.println("\n\nCombien de personnages voulez vous créer ?");
        int nbrPers = Integer.parseInt(sc.nextLine());

        Personnage[] persos = new Personnage[nbrPers];

        for (int i = 0; i < nbrPers; i++) {
            System.out.println("Création Personnage " + (i+1));
            CreaPerso creaPer = new CreaPerso();
            Personnage pers = new Personnage();
            persos[i] = creaPer.CreaPers(pers);
        }

        test.createM(mos);
        int nbrEnti = nbrPers + 1;

        for (int j = 0; j < nbrPers; j++)
        {
            test.posJ(tesssst, persos[j]);
            tesssst.afficherDJ();
        }

        tesssst.afficherDJ();
        test.addObst(tesssst);
        tesssst.afficherDJ();
        test.posM(tesssst, mos);
        tesssst.afficherDJ();
        test.posEquip(tesssst, arme);
        tesssst.afficherDJ();
        test.posEquip(tesssst, armee);
        tesssst.afficherDJ();
        test.posEquip(tesssst, armure);
        tesssst.afficherDJ();



        /*Personnage fesmfjeio = new Personnage("Céleste", new Humain(), new Roublard(), new Feminin());
        Monstre pjpegfs = new Monstre();
        Equipement arme = new EpeeLongue();
        Equipement armee = new ArcCourt();
        Equipement armure = new ArmureEcaille();

        test.createDJ(tesssst);

        tesssst.afficherDJ();

        test.createM(pjpegfs);

        tesssst.afficherDJ();
        test.addObst(tesssst);
        tesssst.afficherDJ();
        test.posJ(tesssst, fesmfjeio);
        tesssst.afficherDJ();
        test.posM(tesssst, pjpegfs);
        tesssst.afficherDJ();
        test.posEquip(tesssst, arme);
        tesssst.afficherDJ();
        test.posEquip(tesssst, armee);
        tesssst.afficherDJ();
        test.posEquip(tesssst, armure);
        tesssst.afficherDJ();*/


        for (int j = 0; j < nbrPers; j++)
        {
            System.out.println(persos[j].getStat());
        }
        System.out.println(mos.getStat());

        tesssst.afficherDJ();


        Entite[] Enti = new Entite[nbrEnti];

        for (int j = 0; j < nbrPers; j++)
        {
            Enti[j] = persos[j];
        }
        Enti[nbrEnti-1] = mos;

        De de = new De(1, 20);

        for (int j = 0; j < nbrEnti; j++)
        {
            if (Enti[j].code() == 0)
            {
                //faire une deuxième interface (vivant) ?
                //qui contient personnage et monstre
                //et toutes les méthodes définies dedans
                //pour ne pas à avoir à savoir forcément si personnage ou monstre
                //on fait l'ordre de jeu, on balance tt dans un tableau
                //et apres juste une boucle pour les tours
                //ca devrait fonctionner
            }
        }



        int val = 0;

        while (val == 0) {
            for (int j = 0; j < nbrPers; j++)
            {
                if (val == 0)
                {
                    for (int i = 0; i < 3; i++) {
                        //faire une fonction (dans donjon ? )   A MODIFIER
                        System.out.print("\n\n\n");
                        System.out.print("    --> ");
                        System.out.print(persos[j].getLilInfos());
                        System.out.print("        ");
                        System.out.print(mos.getLilInfos());
                        //_______________________________
                        tesssst.afficherDJ();
                        System.out.println(persos[j].getInfos());
                        //System.out.println(pjpegfs.getStat());
                        val = persos[j].action(tesssst);
                    }
                }
            }
            for (int i = 0; i < 3; i++)
            {
                if (val == 0)
                {
                    tesssst.afficherDJ();
                    System.out.println(mos.getStat());
                    //System.out.println(fesmfjeio.getInfos());
                    val = mos.action(tesssst);
                }
            }
        }
        if (val == 1)
        {
            System.out.println("\nLes joueurs ont perdu");
        }
        else
        {
            System.out.println("\nLes joueurs ont fini le donjon");
            //les persos regagnent leur vie
        }

        //          A FAIRE
        // améliorer l'affichage
        // trucs à ajouter
        //      - créer persos, monstre et autre en mode console (sans le main)
        //      - choix ordre (initiative)

    }
}