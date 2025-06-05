package utils;

import de.De;
import donjon.Donjon;
import entite.Monstre;
import entite.equipement.Equipement;
import entite.equipement.arme.courante.*;
import entite.equipement.arme.distance.*;
import entite.equipement.arme.guerre.*;
import entite.equipement.armure.legere.*;
import entite.equipement.armure.lourde.*;
import entite.personnages.Personnage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Inputs {

    private final Couleurs _cl = new Couleurs();

    private final static String[] _ord = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    private final Scanner sc = new Scanner(System.in);

    public Inputs()
    {

    }

    public Donjon creationDonjon(MJ mj)
    {
        System.out.println("\n\nVoulez-vous creer un donjons (o/n) ? (dans le cas contraire, vous pourrez choisir entre un donjon par défaut ou un genere aleatoirement)");
        String choix = sc.nextLine();
        if (choix.equals("o"))
        {
            int[] tailleDj = tailleDonjon();
            return mj.creationDonjon(tailleDj);
        }
        else if (choix.equals("n"))
        {
            return null;
        }
        else
        {
            creationDonjon(mj);
        }
        return null;
    }

    public int[] tailleDonjon()
    {
        int[] tailleCote = new int[2];

        try {
            System.out.println("\n\ntaille côté ordonnée");
            tailleCote[0] = sc.nextInt();
            System.out.println("taille côté abscisse");
            tailleCote[1] = sc.nextInt();

            if (((15 <= tailleCote[0]) && (tailleCote[0] <= 25)) && ((15 <= tailleCote[1]) && (tailleCote[1] <= 25)))
            {
                return tailleCote;
            }
            else
            {
                System.out.println(_cl.rouge() + "Erreur dans la taille du donjon" + _cl.reset());
                tailleDonjon();
            }
        }
        catch (NumberFormatException erreur)
        {
            System.out.println(_cl.rouge() + "\nErreur dans la saisie des tailles du donjon, il ne doit y avoir que des nombres" + _cl.reset());
            tailleDonjon();
        }
        tailleDonjon();
        return null;
    }

    public void ajoutObstacle(Donjon DJ, MJ mj)
    {
        System.out.println("\nVoulez-vous mettre des obstacles (o/n) ?");
        String choix = sc.nextLine();

        while (!choix.equals("n")) {
            if (choix.equals("o")) {
                int[] pos = choixCase("de l'obstacle");
                mj.addObst(DJ, pos);
                DJ.afficherDJ();
                System.out.println("\n\nCréer un autre obstacle (o/n) ?");
                choix = sc.nextLine();
            }
            else
            {
                System.out.println(_cl.rouge() + "Mauvaise valeur rentrée." + _cl.reset());
                ajoutObstacle(DJ, mj);
            }
        }
    }

    public void ajoutMonstre(Donjon DJ, MJ mj)
    {
        String choix = "o";

        while (!choix.equals("n")) {
            if (choix.equals("o")) {
                Monstre mons = creationMonstre(mj);
                DJ.afficherDJ();
                int[] pos = choixCase("de " + mons);
                mj.posM(DJ, mons, pos);
                DJ.afficherDJ();
                System.out.println("\n\nCréer un autre monstre (o/n) ?");
                choix = sc.nextLine();
            }
            else
            {
                System.out.println(_cl.rouge() + "Mauvaise valeur rentrée." + _cl.reset());
                ajoutMonstre(DJ, mj);
            }
        }
    }

    public void ajoutEquipement(Donjon DJ, MJ mj)
    {
        System.out.println("\nVoulez-vous créer des Equipements (o/n) ?");
        String choix = sc.nextLine();

        while (!choix.equals("n")) {
            if (choix.equals("o"))
            {
                Equipement equip = null;
                boolean ok = true;
                String texte = """
                        Sélectionnez l'équipement voulu :
                        Arme courante :
                        \t1-  Baton
                        \t2-  Masse d'arme
                        Arme de guerre (-2 vit / +4 for) :
                        \t3-  Epée longue
                        \t4-  Rapière
                        \t5-  Epée à 2 mains
                        Arme à distance :
                        \t6-  Fronde
                        \t7-  Arbelète légère
                        \t8-  Arc court
                        Armure légère :
                        \t9-  Armure d'écailles
                        \t10- Demi plate
                        Armure lourde (-4 vit) :
                        \t11- Cotte de maille
                        \t12- Harnois
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
                    System.out.println(_cl.rouge() + "/!\\ ATTENTION : Equipement non crée" + _cl.reset());
                } else {
                    int[] pos = choixCase("de l'équipement : " + equip);
                    DJ.afficherDJ();
                    mj.posEquip(DJ, equip, pos);
                    DJ.afficherDJ();
                }
                System.out.println("\n\nCréer un autre equipement (o/n)?");
                choix = sc.nextLine();
            }
            else
            {
                System.out.println(_cl.rouge() + "Mauvaise valeur rentrée." + _cl.reset());
                ajoutEquipement(DJ, mj);
            }
        }
    }



    public int[] choixCase(String context)
    {
        System.out.println("\n\nChoisir la case " + context + _cl.cyan() + "   [lettre majuscule][nombre]" + _cl.reset());
        String pos = sc.nextLine();

        int[] posi = positionCase(pos);

        while (posi == null)
        {
            posi = choixCase(context);
        }

        return posi;
    }

    public int[] positionCase(String pos)
    {
        try {
            String pos1 = pos.substring(0, 1);
            String pos2 = pos.substring(1);

            int[] posi = new int[2];

            for (int i = 0; i < 26; i++) {
                if (pos1.equals(_ord[i])) {
                    posi[1] = i + 1;
                }
            }
            posi[0] = Integer.parseInt(pos2);

            if ((posi[0] == 0) || (posi[1] == 0))
            {
                System.out.println(_cl.rouge() + "Les cases sont dans le format suivant : " + _cl.cyan() + "[lettre majuscule][nombre]" + _cl.reset());
                return null;
            }

            return posi;
        }
        catch (NullPointerException | StringIndexOutOfBoundsException | NumberFormatException erreur)
        {
            System.out.println(_cl.rouge() + "Les cases sont dans le format suivant : " + _cl.cyan() + "   [lettre majuscule][nombre]" + _cl.reset());
            return null;
        }
    }

    public Monstre creationMonstre(MJ mj)
    {
        System.out.println("\n\n===== Nouveau Monstre =====");
        System.out.println("Espèce ?");
        String espece = sc.nextLine();
        while (espece.isEmpty())
        {
            System.out.println(_cl.rouge() + "L'espèce ne peut pas être vide!" + _cl.reset());
            System.out.println("Espèce ?");
            espece = sc.nextLine();
        }
        System.out.println("Symbole d'affichage ?   (3 charactères max)");
        String symb = sc.nextLine();
        while ((symb.isEmpty()) || (symb.length() > 3))
        {
            System.out.println(_cl.rouge() + "Le symbole doit être entre 1 et 3 caractères!" + _cl.reset());
            System.out.println("Symbole d'affichage ?   (3 charactères max) ?");
            symb = sc.nextLine();
        }
        try {
            System.out.println("Portée de l'attaque ?");
            int portee = sc.nextInt();
            System.out.println("Nombre de dés pour le calcul de l'attaque ?");
            int nbrDeDeg = sc.nextInt();
            System.out.println("Nombre de face pour les dés pour le calcul de l'attaque ?");
            int nbrFaceDeDeg = sc.nextInt();
            System.out.println("Nombre de dés pour le calcul des charactéristiques ?");
            int nbrDeCha = sc.nextInt();
            System.out.println("Nombre de face pour les dés pour le calcul des charactéristiques ?");
            int nbrFaceDeCha = sc.nextInt();

            return mj.createM(espece, symb, portee, new De(nbrDeDeg, nbrFaceDeDeg), new De(nbrDeCha, nbrFaceDeCha));

        }
        catch (InputMismatchException | NumberFormatException erreur)
        {
            System.out.println(_cl.rouge() + "\nSeul l'espèce et le symbole du monstre peut contenir autre chose que des entier." + _cl.reset() + "\nRecomencez");
            creationMonstre(mj);
        }
        creationMonstre(mj);
        return null;
    }

    public String contextDonjon()
    {
        System.out.println("Quel est le context ?");
        return sc.nextLine();
    }

    public void mjCommenteAction(MJ mj)
    {
        System.out.println("Le MJ commente l'action effectuée");
        String comm = sc.nextLine();
        mj.comAction(comm);
    }

    public void persoCommenteAction(Personnage perso)
    {
        System.out.println("Le personnage commente l'action effectuée");
        String comm = sc.nextLine();
        perso.comAction(comm);
    }

    public Equipement equiperEquip(Personnage pers)
    {
        if (!pers.getStock().isEmpty()) {
            System.out.println("Quel équipement équiper ?");

            int i = 1;
            for (Equipement eqi : pers.getStock()) {
                System.out.println(i + "- " + eqi.getName());
                i++;
            }

            try {
                int equ = sc.nextInt() - 1;
                return pers.getStock().get(equ);
            }
            catch (InputMismatchException | NullPointerException | NumberFormatException | IndexOutOfBoundsException erreur)
            {
                System.out.println(_cl.rouge() + "Mauvaise valeur rentrée." + _cl.reset());
                equiperEquip(pers);
            }
        }
        return null;
    }


    public void depViv(Donjon dj, MJ mj)
    {
        int choix = 1;

        System.out.println("\nChoisissez un joueur ou un monstre à déplacer :");
        for (Personnage pers : dj.getListePerso()) {
            System.out.println(choix++ + "- " + pers);
        }
        for (Monstre mons : dj.getListeMonstre()) {
            System.out.println(choix++ + "- " + mons);
        }

        try {
            int perso = sc.nextInt() - 1;

            int[] posD = dj.getListePerso().get(perso).getPos();

            int[] posF = choixCase("où mettre l'entité");

            mj.depViv(dj, posD, posF);
        }
        catch (InputMismatchException | NullPointerException | NumberFormatException | IndexOutOfBoundsException erreur)
        {
            System.out.println(_cl.rouge() + "\nErreur dans la saisie." + _cl.reset() + "\nRecomencez");
            depViv(dj, mj);
        }
    }


    public StatusDonjon degatPersonnage(Donjon dj, MJ mj)
    {
        int choix = 1;
        StatusDonjon val = StatusDonjon.NORMAL;

        System.out.println("\nChoisissez un joueur :");
        for (Personnage pers : dj.getListePerso())
        {
            System.out.println(choix++ + "- " + pers);
        }

        choix = sc.nextInt() - 1;

        try {
            System.out.println("Combien de dé(s) pour infliger les dégats ?");
            int nbDe = sc.nextInt();
            System.out.println("Combien de faces pour les dés ?");
            int nbFaceDe = sc.nextInt();

            int dgt = new De(nbDe, nbFaceDe).roll();

            val = mj.degatJoueur(dj, choix, dgt);
        }
        catch (InputMismatchException | NullPointerException | NumberFormatException | IndexOutOfBoundsException erreur)
        {
            System.out.println(_cl.rouge() + "\nErreur dans la saisie." + _cl.reset() + "\nRecomencez");
            degatPersonnage(dj, mj);
        }

        return val;
    }


    public StatusDonjon degatMonstre(Donjon dj, MJ mj)
    {
        int choix = 1;
        StatusDonjon val = StatusDonjon.NORMAL;

        System.out.println("\nChoisissez un monstre :");
        for (Monstre mons : dj.getListeMonstre()) {
            System.out.println(choix++ + ". " + mons);
        }

        try {
            choix = sc.nextInt() - 1;

            System.out.println("Combien de dé(s) pour infliger les dégats ?");
            int nbDe = sc.nextInt();
            System.out.println("Combien de faces pour les dés ?");
            int nbFaceDe = sc.nextInt();

            int dgt = new De(nbDe, nbFaceDe).roll();

            val = mj.degatMonstre(dj, choix, dgt);
        }
        catch (InputMismatchException | NullPointerException | NumberFormatException | IndexOutOfBoundsException erreur)
        {
            System.out.println(_cl.rouge() + "\nErreur dans la saisie." + _cl.reset() + "\nRecomencez");
            degatMonstre(dj, mj);
        }

        return val;
    }

}
