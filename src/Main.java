import Utils.Couleurs;
import Utils.Inputs;
import Utils.MJ;
import de.De;
import donjon.CreationDonjonDefault;
import donjon.Donjon;
import entite.equipement.Equipement;
import entite.personnages.CreaPerso;
import entite.personnages.Personnage;
import entite.Vivant;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Couleurs cl = new Couleurs();
        Inputs _input = new Inputs();

        MJ mj = new MJ();
        ArrayList<Personnage> Pers = new ArrayList<>();

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
            } catch (NumberFormatException | NullPointerException erreur) {
                System.out.println(cl.rouge() + "Mauvaise entrée clavier" + cl.reset());
            }
        }

        CreaPerso CreaPer = new CreaPerso();

        for (int i = 0; i < nbrPers; i++) {
            System.out.println("\n\nCréation Personnage " + (i+1));
            Personnage pers = CreaPer.CreaPers();
            System.out.println(pers.getInfos());

            System.out.println("\nVoulez-vous équiper un equipement ? (o/n)");
            String choix = sc.nextLine();
            if (choix.equals("o"))
            {
                Equipement equip = _input.equiperEquip(pers);
                pers.sEquiper(equip);

                System.out.println("\nVoulez-vous équiper un autre equipement ? (o/n)");
                String choixx = sc.nextLine();
                if (choixx.equals("o"))
                {
                    Equipement equipe = _input.equiperEquip(pers);
                    pers.sEquiper(equipe);
                }
            }
            Pers.add(pers);
        }


        //               TOURS
        for (int tour = 1; tour <= 3; tour++) {

            ArrayList<Vivant> Viv = new ArrayList<>();

            System.out.print("\n\n");
            System.out.println(cl.jaune() + "-------------------------------------------------------------------");
            System.out.print("\n                    Donjon n°" + tour + "                       \n");
            System.out.println("-------------------------------------------------------------------\n" + cl.reset());

            Donjon donjon  = mj.creationDonjon();

            if (donjon == null)
            {
                CreationDonjonDefault creaDj = new CreationDonjonDefault();
                System.out.println("\n\nVoulez-vous créer un donjon aléatoirement ? (o/n)");
                String choix = sc.nextLine();
                if (choix.equals("o")) {
                    donjon = creaDj.donjonRandom();
                }
                else {
                    donjon = creaDj.createDefaultDJ();
                }
            }
            for (int i = 0; i < nbrPers; i++) {
                mj.posJ(donjon, Pers.get(i));
                donjon.afficherDJ();
                Viv.add(Pers.get(i));
            }

            Viv.addAll(donjon.getListeMonstre());


            int nbrViv = nbrPers + donjon.getListeMonstre().size();


            //                  Initiative

            for (int j = 0; j < nbrViv; j++) {
                System.out.println(Viv.get(j).getStat());
            }

            De deIni = new De(1, 20);

            ArrayList<Integer> ArrIni = new ArrayList<>();
            ArrayList<Vivant> VivTri = new ArrayList<>();

            System.out.println(cl.jaune() + "\n\n===== Choix de l'ordre de jeu =====" + cl.reset());

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
                            if (init > ArrIni.get(i)) {
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

        //faire classes pour l'affichage/interaction user
        //pas faire une classe qui gère tout !
        //les classes métier (donjon/personnage/...) ne doivent pas connaitre le user et doivent tourner sans input !


        //BIEN BLOQUER LES INPUTS A o OU n QUAND DEMANDE (A FAIRE A LA FIN PSQ C'EST LONG DE TT METTRE PR LES TESTS)
    }
}