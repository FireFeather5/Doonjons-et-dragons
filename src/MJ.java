import de.De;
import donjon.Donjon;
import entite.Obstacle;
import entite.equipement.Equipement;
import entite.Monstre;
import entite.equipement.arme.courante.Baton;
import entite.equipement.arme.courante.MasseArme;
import entite.equipement.arme.distance.ArbaleteLegere;
import entite.equipement.arme.distance.ArcCourt;
import entite.equipement.arme.distance.Fronde;
import entite.equipement.arme.guerre.EpeeLongue;
import entite.equipement.arme.guerre.Rapiere;
import entite.equipement.armure.Armure;
import entite.equipement.armure.legere.ArmureEcaille;
import entite.equipement.armure.legere.DemiPlate;
import entite.equipement.armure.lourde.CotteMaille;
import entite.equipement.armure.lourde.Harnois;
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
        System.out.println("Voulez-vous creer un donjons (o/n) ? (dans le cas contraire, le donjons par défaut sera utilisé)");
        switch (sc.nextLine()) {
            case "o":
                System.out.println("\n\ntaille cote 1");
                String tc1s = sc.nextLine();
                System.out.println("taille cote 2");
                String tc2s = sc.nextLine();
                try {
                    int tc2 = Integer.parseInt(tc1s);
                    int tc1 = Integer.parseInt(tc2s);

                    if (((15 <= tc1) && (tc1 <= 25)) && ((15 <= tc2) && (tc2 <= 25))) {
                        DJ.creaDonjon(tc1, tc2);
                    } else {
                        System.out.println("Erreur dans la taille du donjon");
                        this.createDJ(DJ);
                    }
                }
                catch (NumberFormatException erreur)
                {
                    System.out.println("\nErreur dans la saisie des tailles du donjon, il ne doit y avoir que des nombres");
                    this.createDJ(DJ);
                }

                boolean fini = false;

                System.out.println("Voulez-vous mettre des obstacles (o/n) ?");
                fini = !sc.nextLine().equals("o");

                while (!fini){
                    this.addObst(DJ);
                    System.out.println("Créer un auter obstacle (o/n) ?");
                    if (!sc.nextLine().equals("o")) fini = true;
                }

                System.out.println("Voulez-vous créer des monstres (o/n) ?");
                fini = !sc.nextLine().equals("o");

                while (!fini) {
                    Monstre mons = new Monstre();
                    this.createM(mons);
                    this.posM(DJ, mons);
                    System.out.println("Créer un auter monstre (o/n) ?");
                    if (!sc.nextLine().equals("o")) fini = true;
                }
                System.out.println("Voulez-vous créer des Equipements (o/n) ?");
                fini = !sc.nextLine().equals("o");
                while (!fini) {
                    Equipement equip = null;
                    boolean ok = false;
                    System.out.println("Selectionnez l'equipement voulu :\n\n1. Arme\n2. Armure");
                    switch(sc.nextLine()) {
                        case "1":
                            System.out.println("Quel type d'arme ?\n\n1. Arme courante\n2. Arme de guerre\n3. Arme a distance");
                            switch (sc.nextLine()) {
                                case "1":
                                    System.out.println("Quel arme a courante ?\n\n1. Baton\n2. Masse d'arme");
                                    switch (sc.nextLine()) {
                                        case "1":
                                            equip = new Baton();
                                            ok = true;
                                            break;
                                        case "2":
                                            equip = new MasseArme();
                                            ok = true;
                                            break;
                                    }
                                    break;
                                case "2":
                                    System.out.println("Quel arme de guerre ?\n\n1. Epee longue\n2. Rapière");
                                    switch (sc.nextLine()) {
                                        case "1":
                                            equip = new EpeeLongue();
                                            ok = true;
                                            break;
                                        case "2":
                                            equip = new Rapiere();
                                            ok = true;
                                            break;
                                    }
                                    break;
                                case "3":
                                    System.out.println("Quel arme a distance ?\n\n1. Arbalète légère\n2. Arc court\n3. Fronde");
                                    switch (sc.nextLine()) {
                                        case "1":
                                            equip = new ArbaleteLegere();
                                            ok = true;
                                            break;
                                        case "2":
                                            equip = new ArcCourt();
                                            ok = true;
                                            break;
                                        case "3":
                                            equip = new Fronde();
                                            ok = true;
                                            break;
                                    }
                                    break;
                            }
                            break;
                        case "2":
                            System.out.println("Quel type d'armure ?\n\n1. Armure légère\n2. Armure lourde");
                            switch (sc.nextLine()) {
                                case "1":
                                    System.out.println("Quelle armure légère ?\n\n1. Armure d'écaille\n2. Demi plate");
                                    switch (sc.nextLine()) {
                                        case "1":
                                            equip = new ArmureEcaille();
                                            ok = true;
                                            break;
                                        case "2":
                                            equip = new DemiPlate();
                                            ok = true;
                                            break;
                                    }
                                    break;
                                case "2":
                                    System.out.println("Quelle armure lourde ?\n\n1. Cote de maille\n2. Harnois");
                                    switch (sc.nextLine()) {
                                        case "1":
                                            equip = new CotteMaille();
                                            ok = true;
                                            break;
                                        case "2":
                                            equip = new Harnois();
                                            ok = true;
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    if (!ok) {
                        System.out.println("/!\\ ATTENTION : Equipement non crée");
                    }
                    else {
                        this.posEquip(DJ, equip);
                    }
                    System.out.println("Créer un autre equipement (o/n)?");
                    if (!sc.nextLine().equals("o")) fini = true;
                }
                break;
            case null, default:
                this.createDefaultDJ(DJ);
        }


    }

    private void createDefaultDJ(Donjon DJ)
    {
        DJ.creaDonjon(18, 23);
        Obstacle obs = new Obstacle();
        obs.addPos("J8", DJ);
        obs.addPos("J9", DJ);
        obs.addPos("K9", DJ);
        obs.addPos("K10", DJ);
        obs.addPos("K11", DJ);

        Monstre demogordgon = new Monstre();
        demogordgon.creaMonstre("Demogorgon", 1, new De(2, 6), new De(4, 4));
        DJ.posM("P14", demogordgon);
        Monstre dragonBleu = new Monstre();
        dragonBleu.creaMonstre("Dragon Bleu", 3, new De(2, 6), new De(3, 4));
        DJ.posM("E4", dragonBleu);

        Equipement epeeLongue = new EpeeLongue();
        Equipement fronde = new Fronde();
        Equipement cotteMaille = new CotteMaille();
        Equipement demiPlate = new DemiPlate();
        Equipement rapiere = new Rapiere();

        DJ.posE("K4", epeeLongue);
        DJ.posE("J15", fronde);
        DJ.posE("Q7", cotteMaille);
        DJ.posE("E15", demiPlate);
        DJ.posE("T14", rapiere);
    }

    public String choixPos(String txt)
    {
        System.out.println("\nposition " + txt);
        String pc = sc.nextLine();

        return pc;
    }

    public void addObst(Donjon DJ)
    {
        String pos = choixPos(" de l'obstacle");

        Obstacle obs = new Obstacle();
        boolean test = obs.addPos(pos, DJ);

        if (!test)
        {
            System.out.println("Erreur dans la selection de la position");
            addObst(DJ);
        }
    }

    public void createM(Monstre mons)
    {
        System.out.println("\n\n===== Nouveau Monstre =====");
        System.out.println("espèce ?");
        String espece = sc.nextLine();
        try {
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
        catch (NumberFormatException erreur)
        {
            System.out.println("\nSeul l'espèce du monstre peut contenir autre chose que des entier.");
            createM(mons);
        }
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

    public String presContext()
    {
        System.out.println("Quel est le context ?");
        return sc.nextLine();
    }

    public String comAction()
    {
        System.out.println("Commentez l'action effectuée");
        return sc.nextLine();
    }
}
