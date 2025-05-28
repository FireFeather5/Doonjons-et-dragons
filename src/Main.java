import Utils.Couleurs;
import de.De;
import donjon.Donjon;
import entite.Monstre;
import entite.personnages.CreaPerso;
import entite.personnages.Personnage;
import entite.personnages.Vivant;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Couleurs cl = new Couleurs();
        MJ mj = new MJ();
        Donjon donjon = new Donjon();
        ArrayList<Vivant> Viv = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println(cl.rouge() + "Bienvenue dans DOOnjon et Dragons" + cl.reset());

        //                     Crea persos
        int nbrPers = 0;
        while (nbrPers == 0) {
            System.out.println("\nCombien de personnages voulez-vous créer ?");
            try {
                nbrPers = Integer.parseInt(sc.nextLine());
                if (nbrPers == 0) {
                    System.out.println(cl.rouge() + "\nIl doit y avoir au moins un personnage !" + cl.reset());
                }
            } catch (NumberFormatException erreur) {
                System.out.println(cl.rouge() + "Mauvaise entrée clavier" + cl.reset());
            } catch (NullPointerException erreur) {
                System.out.println(cl.rouge() + "Mauvaise entrée clavier" + cl.reset());
            }
        }

        CreaPerso CreaPer = new CreaPerso();

        for (int i = 0; i < nbrPers; i++) {
            System.out.println("\nCréation Personnage " + (i+1));
            Personnage pers = new Personnage();
            CreaPer.CreaPers(pers);
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
        }

        for (int pt = 1; pt <= 3; pt++) {

            int nbMons = mj.createDJ(donjon);
            int nbrViv = nbMons;
            nbrViv += nbrPers;

            for (int i = 0; i < nbrPers; i++) {
                mj.posJ(donjon, (Personnage)Viv.get(i));
                donjon.afficherDJ();
            }

            //              Créa monstre

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


            //                  Initiative


            for (int j = 0; j < nbrViv; j++) {
                System.out.println(Viv.get(j).getStat());
            }

            De deIni = new De(1, 20);

        ArrayList<Integer> ArrIni = new ArrayList<>();
        ArrayList<Vivant> VivTri = new ArrayList<>();

            System.out.println("\n\nChoix de l'ordre de jeu");

            for (int j = 0; j < nbrViv; j++) {
                System.out.println("\n" + Viv.get(j).toString() + " : ");
                int init = Viv.get(j).getIni();
                init += deIni.roll();

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


            Tour tr = new Tour(VivTri, mj, donjon);
            tr.tour();

        }
    }
}