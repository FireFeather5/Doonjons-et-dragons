import de.De;
import donjon.Donjon;
import entite.Obstacle;
import entite.equipement.Equipement;
import entite.Monstre;
import entite.equipement.arme.courante.*;
import entite.equipement.arme.distance.*;
import entite.equipement.arme.guerre.*;
import entite.equipement.armure.legere.*;
import entite.equipement.armure.lourde.*;
import entite.personnages.Personnage;

import java.util.ArrayList;
import java.util.Scanner;

public class MJ {

    Scanner sc = new Scanner(System.in);
    ArrayList<String> _monstresCrees = new ArrayList<>();

    public MJ()
    {

    }

    public int createDJ(Donjon DJ)
    {
        System.out.println("Voulez-vous creer un donjons (o/n) ? (dans le cas contraire, le donjons par défaut sera utilisé)");
        if (sc.nextLine().equals("o")) {
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

            boolean fini;

            System.out.println("Aperçu du donjon :");
            DJ.afficherDJ();
            System.out.println("\nCela vous convient-il (o/n) ?");
            if (sc.nextLine().equals("n")) {
                this.createDJ(DJ);
            }
            System.out.println("Voulez-vous mettre des obstacles (o/n) ?");
            fini = !sc.nextLine().equals("o");

            while (!fini) {
                this.addObst(DJ);
                DJ.afficherDJ();
                System.out.println("Créer un auter obstacle (o/n) ?");
                if (!sc.nextLine().equals("o")) fini = true;
            }

            System.out.println("Voulez-vous créer des Equipements (o/n) ?");
            fini = !sc.nextLine().equals("o");
            while (!fini) {
                Equipement equip = null;
                boolean ok = true;
                String texte = """
                        Sélectionnez l'équipement voulu :
                        Arme courante :
                        \t1.  Baton
                        \t2.  Masse d'arme
                        Arme de guerre (-2 vit / +4 for) :
                        \t3.  Epée longue
                        \t4.  Rapière
                        \t5.  Epée à 2 mains
                        Arme à distance :
                        \t6.  Fronde
                        \t7.  Arbelète légère
                        \t8.  Arc court
                        Armure légère :
                        \t9.  Armure d'écailles
                        \t10. Demi plate
                        Armure lourde (-4 vit) :
                        \t11. Cotte de maille
                        \t12. Harnois
                        """;
                System.out.println(texte);
                switch (sc.nextLine()) {
                    case "1" -> equip = new Baton();
                    case "2" -> equip = new MasseArme();
                    case "3" -> equip = new EpeeLongue();
                    case "4" -> equip = new Rapiere();
                    case "5" -> equip = new EpeeDeuxMains();
                    case "6" -> equip = new Fronde();
                    case "7" -> equip = new ArbaleteLegere();
                    case "8" -> equip = new ArcCourt();
                    case "9" -> equip = new ArmureEcaille();
                    case "10" -> equip = new DemiPlate();
                    case "11" -> equip = new CotteMaille();
                    case "12" -> equip = new Harnois();
                    case null, default -> ok = false;
                }
                if (!ok) {
                    System.out.println("/!\\ ATTENTION : Equipement non crée");
                } else {
                    this.posEquip(DJ, equip);
                    DJ.afficherDJ();
                }
                System.out.println("Créer un autre equipement (o/n)?");
                if (!sc.nextLine().equals("o")) fini = true;
            }
            System.out.println("Combien de monstres creer ? (0 en initialise deux automatiquement)");
            return Integer.parseInt(sc.nextLine());
        }
        else {
            this.createDefaultDJ(DJ);
            return 0;
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

        DJ.afficherDJ();
    }

    public String choixPos(String txt)
    {
        System.out.println("\nposition " + txt);

        return sc.nextLine();
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
        System.out.println("Espèce ?");
        String espece = sc.nextLine();
        System.out.println("Symbole d'affichage ?   (3 charactères max)");
        String symb = sc.nextLine();
        try {
            System.out.println("Portée de l'attaque ?");
            int portee = Integer.parseInt(sc.nextLine());
            System.out.println("Nombre de dés pour le calcul de l'attaque ?");
            int nbrDeDeg = Integer.parseInt(sc.nextLine());
            System.out.println("Nombre de face pour les dés pour le calcul de l'attaque ?");
            int nbrFaceDeDeg = Integer.parseInt(sc.nextLine());
            System.out.println("Nombre de dés pour le calcul des charactéristiques ?");
            int nbrDeCha = Integer.parseInt(sc.nextLine());
            System.out.println("Nombre de face pour les dés pour le calcul des charactéristiques ?");
            int nbrFaceDeCha = Integer.parseInt(sc.nextLine());

            mons.creaMonstre(espece, symb, portee, new De(nbrDeDeg, nbrFaceDeDeg), new De(nbrDeCha, nbrFaceDeCha));

            for (String monstre : this._monstresCrees) {
                if (monstre.equals(espece)) {
                    mons.multiMonstre();
                }
            }
            this._monstresCrees.add(espece);
        }
        catch (NumberFormatException erreur)
        {
            System.out.println("\nSeul l'espèce du monstre peut contenir autre chose que des entier.\nRecomencez");
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
        String pos = choixPos("de " + equip.getName());

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
        System.out.println("Le MJ commente l'action effectuée");
        return "MJ - " + sc.nextLine();
    }

    public String degatJoueur(Donjon DJ) {
        int choix = 1;

        for (Personnage pers : DJ.getListePerso()) {
            System.out.println(choix++ + ". " + pers);
        }

        System.out.println("Choisissez un joueur :");
        choix = sc.nextInt();

        System.out.println("Combien de dé(s) pour infliger les dégats ?");
        int nbDe = sc.nextInt();
        System.out.println("Combien de faces pour les dés ?");
        int nbFaceDe = sc.nextInt();

        int dgt = new De(nbDe, nbFaceDe).roll();
        DJ.getListePerso().get(choix-1).seFaitAttaquer(dgt, DJ);

        return "Le MJ inflige " + dgt + " a " + DJ.getListePerso().get(choix-1);
    }

    public String degatMonstre(Donjon DJ) {
        int choix = 1;

        for (Monstre mons : DJ.getListeMonstre()) {
            System.out.println(choix++ + ". " + mons);
        }

        System.out.println("Choisissez un monstre :");
        choix = sc.nextInt();

        System.out.println("Combien de dé(s) pour infliger les dégats ?");
        int nbDe = sc.nextInt();
        System.out.println("Combien de faces pour les dés ?");
        int nbFaceDe = sc.nextInt();

        int dgt = new De(nbDe, nbFaceDe).roll();
        DJ.getListeMonstre().get(choix-1).seFaitAttaquer(dgt, DJ);

        return "Le MJ inflige " + dgt + " a " + DJ.getListeMonstre().get(choix-1);
    }
}
