package Utils;

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

import java.util.Scanner;

public class Inputs {

    private Couleurs _cl = new Couleurs();

    private final static String[] _ord = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    Scanner sc = new Scanner(System.in);

    public Inputs()
    {

    }

    public Donjon creationDonjon(MJ mj)
    {
        System.out.println("\n\nVoulez-vous creer un donjons (o/n) ? (dans le cas contraire, le donjon par défaut sera utilisé)");
        String choix = sc.nextLine();
        if (choix.equals("o"))
        {
            int[] tailleDj = tailleDonjon();
            Donjon dj = mj.creationDonjon(tailleDj);
            return dj;
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

        System.out.println("\n\ntaille cote 1");
        String tc1s = sc.nextLine();
        System.out.println("taille cote 2");
        String tc2s = sc.nextLine();
        try {
            tailleCote[0] = Integer.parseInt(tc1s);
            tailleCote[1] = Integer.parseInt(tc2s);

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
        boolean fini = !sc.nextLine().equals("o");

        while (!fini) {
            int[] pos = choixCase("de l'obstacle");
            mj.addObst(DJ, pos);
            DJ.afficherDJ();
            System.out.println("\n\nCréer un auter obstacle (o/n) ?");
            if (!sc.nextLine().equals("o")) fini = true;
        }
    }

    public void ajoutMonstre(Donjon DJ, MJ mj)
    {
        boolean fini = false;

        while (!fini) {
            Monstre mons = creationMonstre(mj);
            DJ.afficherDJ();
            int[] pos = choixCase("de " + mons);
            mj.posM(DJ, mons, pos);
            DJ.afficherDJ();
            System.out.println("\n\nCréer un autre monstre (o/n) ?");
            if (!sc.nextLine().equals("o")) fini = true;
        }
    }

    public void ajoutEquipement(Donjon DJ, MJ mj)
    {
        System.out.println("\nVoulez-vous créer des Equipements (o/n) ?");
        boolean fini = !sc.nextLine().equals("o");
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
                System.out.println(_cl.rouge() + "/!\\ ATTENTION : Equipement non crée" + _cl.reset());
            } else {
                int[] pos = choixCase("de l'équipement : " + equip);
                DJ.afficherDJ();
                mj.posEquip(DJ, equip, pos);
                DJ.afficherDJ();
            }
            System.out.println("\n\nCréer un autre equipement (o/n)?");
            if (!sc.nextLine().equals("o")) fini = true;
        }
    }


    public int[] choixCase(String context)
    {
        System.out.println("\n\nChoisir la case " + context + " [lettre][nombre]");
        String pos = sc.nextLine();

        return positionCase(pos);
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

            return posi;
        }
        catch (NullPointerException | StringIndexOutOfBoundsException | NumberFormatException erreur)
        {
            System.out.println(_cl.rouge() + "Les cases sont dans le format suivant : " + _cl.cyan() + "[lettre][nombre]" + _cl.reset());
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
            int portee = Integer.parseInt(sc.nextLine());
            System.out.println("Nombre de dés pour le calcul de l'attaque ?");
            int nbrDeDeg = Integer.parseInt(sc.nextLine());
            System.out.println("Nombre de face pour les dés pour le calcul de l'attaque ?");
            int nbrFaceDeDeg = Integer.parseInt(sc.nextLine());
            System.out.println("Nombre de dés pour le calcul des charactéristiques ?");
            int nbrDeCha = Integer.parseInt(sc.nextLine());
            System.out.println("Nombre de face pour les dés pour le calcul des charactéristiques ?");
            int nbrFaceDeCha = Integer.parseInt(sc.nextLine());

            return mj.createM(espece, symb, portee, new De(nbrDeDeg, nbrFaceDeDeg), new De(nbrDeCha, nbrFaceDeCha));

        }
        catch (NumberFormatException erreur)
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

            int i = 0;
            for (Equipement eqi : pers.getStock()) {
                System.out.println(i + "- " + eqi.getName());
                i++;
            }

            try {
                int equ = Integer.parseInt(sc.nextLine());
                Equipement equip = pers.getStock().get(equ);
                return equip;
            }
            catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException erreur)
            {
                System.out.println(_cl.rouge() + "Mauvaise valeur rentrée." + _cl.reset());
                equiperEquip(pers);
            }
        }
        return null;
    }

}
