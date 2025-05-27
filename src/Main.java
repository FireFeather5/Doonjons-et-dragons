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

        MJ mj = new MJ();
        Donjon donjon = new Donjon();

        System.out.println("\n\nCombien de personnages voulez-vous créer ?");
        int nbrPers = Integer.parseInt(sc.nextLine());
        CreaPerso CreaPer = new CreaPerso();

        for (int i = 0; i < nbrPers; i++) {
            System.out.println("\n\nCréation Personnage " + (i+1));
            Personnage pers = new Personnage();
            CreaPer.CreaPers(pers);
            mj.posJ(donjon, pers);
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
            donjon.afficherDJ();
        }

        for (int pt = 1; pt <= 3; pt++) {
            int nbMons = mj.createDJ(donjon);
            int nbrViv = nbMons;
            nbrViv += nbrPers;

            if (nbMons == 0) {
                Monstre demogordgon = new Monstre();
                demogordgon.creaMonstre("Demogorgon", "XP", 1, new De(2, 8), new De(3, 6));
                donjon.posM("P14", demogordgon);
                Viv.add(demogordgon);
                Monstre dragonBleu = new Monstre();
                dragonBleu.creaMonstre("Dragon Bleu", "B)", 8, new De(2, 8), new De(3, 6));
                donjon.posM("E4", dragonBleu);
                Viv.add(dragonBleu);
                nbrViv += 2;
                donjon.afficherDJ();
            } else {
                for (int i = 1; i <= nbMons; i++) {
                    Monstre mons = new Monstre();
                    mj.createM(mons);
                    mj.posM(donjon, mons);
                    Viv.add(mons);
                    donjon.afficherDJ();
                }
            }


            for (int j = 0; j < nbrViv; j++) {
                System.out.println(Viv.get(j).getStat());
            }

            De deIni = new De(1, 20);

            ArrayList<Integer> ArrIni = new ArrayList<Integer>();
            ArrayList<Vivant> VivTri = new ArrayList<Vivant>();

            for (int j = 0; j < nbrViv; j++) {
                int init = Viv.get(j).getIni();
                init += deIni.roll();
                System.out.println(init);

                if (ArrIni.isEmpty()) {
                    ArrIni.add(init);
                    VivTri.add(Viv.get(j));
                } else {
                    boolean inVivTri = false;
                    for (int i = 0; i < ArrIni.size(); i++) {
                        if (!inVivTri) {
                            if (init < ArrIni.get(i)) {
                                ArrIni.add(i, init);
                                VivTri.add(i, Viv.get(j));
                                inVivTri = true;
                            }
                        }
                    }
                    if (!inVivTri) {
                        ArrIni.add(init);
                        VivTri.add(Viv.get(j));
                    }
                }
            }

            System.out.print("\n\n");
            for (Vivant vi : VivTri) {
                System.out.println(vi.toString());
            }


            int val = 0;
            int compTour = 1;

            while (val == 0) {
                for (int j = 0; j < nbrViv; j++) {
                    for (int i = 0; i < 3; i++) {
                        if (val == 0) {
                            System.out.print("\n\n");
                            System.out.println("---------------------------------------------------------");
                            System.out.print("\n           Tour de " + VivTri.get(j).getLilInfos() + "\n Tour N°" + compTour + "\n");
                            System.out.println("---------------------------------------------------------\n");
                            for (int k = 0; k < nbrViv; k++) {
                                if (k != j) {
                                    System.out.print("           ");
                                    System.out.print(VivTri.get(k).getLilInfos());
                                } else {
                                    System.out.print("       --> ");
                                    System.out.print(VivTri.get(k).getLilInfos());
                                }
                            }


                            donjon.afficherDJ();
                            System.out.println(VivTri.get(j).getInfos());
                            System.out.println("\nIl vous reste " + (3 - i) + " actions.");
                            val = VivTri.get(j).action(donjon);

                            if (val == 3) {
                                for (int n = 0; n < nbrViv; n++) {
                                    if (VivTri.get(n).getPV() <= 0) {
                                        VivTri.remove(VivTri.get(n));
                                        nbrViv--;

                                        if (n <= j) {
                                            j--;
                                        }
                                    }
                                }
                                val = 0;
                            }
                        }
                        else if (comm.equals("mj"))
                        {
                            System.out.println(test.comAction());
                        }
                        System.out.println("MJ, voulez-vous infliger des dégats à quelqu'un ?\n(j[oueur] / m[onstre] / n[on])");
                        String infDgt = sc.nextLine();
                        if (infDgt.equals("j") || infDgt.equals("joueur")) {
                            System.out.println(test.degatJoueur(tesssst));
                        }
                        else if (infDgt.equals("m") || infDgt.equals("monstre")) {
                            System.out.println(test.degatMonstre(tesssst));
                        }
                    }
                }
                compTour++;
            }


            if (val == 1) {
                System.out.println("\nLes joueurs ont perdu");
            } else {
                System.out.println("\nLes joueurs ont fini le donjon");
                //les persos regagnent leur vie
            }
        }
    }
}