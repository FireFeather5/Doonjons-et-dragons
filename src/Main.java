import de.De;
import donjon.Donjon;
import entite.Entite;
import entite.Monstre;
import entite.personnages.CreaPerso;
import entite.personnages.Personnage;
import entite.personnages.Vivant;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        ArrayList<Vivant> Viv = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenue dans DOOnjon et Dragons");

        MJ test = new MJ();
        Donjon tesssst = new Donjon();

        int nbMons = test.createDJ(tesssst);
        int nbrViv = nbMons;

        if (nbMons == 0)
        {
            Monstre demogordgon = new Monstre();
            demogordgon.creaMonstre("Demogorgon", "XP", 1, new De(2, 8), new De(3, 6));
            tesssst.posM("P14", demogordgon);
            Viv.add(demogordgon);
            Monstre dragonBleu = new Monstre();
            dragonBleu.creaMonstre("Dragon Bleu", "B)", 8, new De(2, 8), new De(3, 6));
            tesssst.posM("E4", dragonBleu);
            Viv.add(dragonBleu);
            nbrViv += 2;
            tesssst.afficherDJ();
        }
        else {
            for (int i = 1; i <= nbMons; i++)
            {
                Monstre mons = new Monstre();
                test.createM(mons);
                test.posM(tesssst, mons);
                Viv.add(mons);
                tesssst.afficherDJ();
            }
        }

        System.out.println("\n\nCombien de personnages voulez-vous créer ?");
        int nbrPers = Integer.parseInt(sc.nextLine());
        CreaPerso CreaPer = new CreaPerso();

        nbrViv += nbrPers;

        for (int i = 0; i < nbrPers; i++) {
            System.out.println("\n\nCréation Personnage " + (i+1));
            Personnage pers = new Personnage();
            CreaPer.CreaPers(pers);
            test.posJ(tesssst, pers);
            System.out.println(pers.getInfos());

            System.out.println("Voulez-vous équiper une arme ? (o/n)");
            String choix = sc.nextLine();
            if (choix.equals("o"))
            {
                pers.sEquiper();
            }
            System.out.println("Voulez-vous équiper une armure ? (o/n)");
            String choixx = sc.nextLine();
            if (choixx.equals("o"))
            {
                pers.sEquiper();
            }

            Viv.add(pers);
            tesssst.afficherDJ();
        }

        for (int j = 0; j < nbrViv; j++) {
            System.out.println(Viv.get(j).getStat());
        }

        De deIni = new De(1, 20);

        ArrayList<Integer> ArrIni = new ArrayList<Integer>();
        ArrayList<Vivant> VivTri = new ArrayList<Vivant>();

        for (int j = 0; j < nbrViv; j++)
        {
            int init = Viv.get(j).getIni();
            init += deIni.roll();
            System.out.println(init);

            if (ArrIni.isEmpty()) {
                ArrIni.add(init);
                VivTri.add(Viv.get(j));
            }
            else
            {
                boolean inVivTri = false;
                for (int i = 0; i < ArrIni.size(); i++)
                {
                    if (!inVivTri) {
                        if (init < ArrIni.get(i)) {
                            ArrIni.add(i, init);
                            VivTri.add(i, Viv.get(j));
                            inVivTri = true;
                        }
                    }
                }
                if (!inVivTri)
                {
                    ArrIni.add(init);
                    VivTri.add(Viv.get(j));
                }
            }
        }

        System.out.print("\n\n");
        for (Vivant vi : VivTri)
        {
            System.out.println(vi.toString());
        }



        int val = 0;
        int compTour = 1;

        while (val == 0) {
            for (int j = 0; j < nbrViv; j++)
            {
                if (val == 0)
                {
                    for (int i = 0; i < 3; i++) {
                        System.out.print("\n\n");
                        System.out.println("---------------------------------------------------------");
                        System.out.print("\n           Tour de "+ VivTri.get(j).getLilInfos() +"\n Tour N°" + compTour + "\n");
                        System.out.println("---------------------------------------------------------\n");
                        for (int k = 0; k < nbrViv; k++) {
                            if (k != j) {
                                System.out.print("           ");
                                System.out.print(VivTri.get(k).getLilInfos());
                            }
                            else
                            {
                                System.out.print("       --> ");
                                System.out.print(VivTri.get(k).getLilInfos());
                            }
                        }


                        tesssst.afficherDJ();
                        System.out.println(VivTri.get(j).getInfos());
                        System.out.println("Il vous reste " + (3-i) + " actions.");
                        val = VivTri.get(j).action(tesssst);

                        if (val == 3)
                        {
                            for (Vivant mosMo : VivTri)
                            {
                                if (mosMo.getPV() <= 0)
                                {
                                    VivTri.remove(mosMo);
                                    nbrViv--;
                                }
                            }
                            val = 0;
                        }


                        System.out.println("\nVoulez-vous commenter l'action précédente ?\n(o/n/mj)");
                        String comm = sc.nextLine();
                        if (comm.equals("o"))
                        {
                            System.out.println(VivTri.get(j).comAction());
                        }
                        else if (comm.equals("mj"))
                        {
                            System.out.println(test.comAction());
                        }
                    }
                }
            }
            compTour++;
        }

        String[] arg = new String[] {};

        if (val == 1)
        {
            System.out.println("\nLes joueurs ont perdu");
            main(arg);
        }
        else
        {
            System.out.println("\nLes joueurs ont fini le donjon");
            main(arg);
            //les persos regagnent leur vie
        }
    }
}