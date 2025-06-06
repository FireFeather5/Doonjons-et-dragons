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
    private final Affichage _affichage = new Affichage(SortieAffichage.CONSOLE);

    public Inputs()
    {

    }

    public Donjon creationDonjon(MJ mj)
    {
        _affichage.afficher(true, "\n\nVoulez-vous creer un donjons (o/n) ? (dans le cas contraire, vous pourrez choisir entre un donjon par défaut ou un genere aleatoirement)");
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
            _affichage.afficher(true, "\n\ntaille côté ordonnée");
            tailleCote[0] = sc.nextInt();
            sc.nextLine();
            _affichage.afficher(true, "taille côté abscisse");
            tailleCote[1] = sc.nextInt();
            sc.nextLine();

            if (((15 <= tailleCote[0]) && (tailleCote[0] <= 25)) && ((15 <= tailleCote[1]) && (tailleCote[1] <= 25)))
            {
                return tailleCote;
            }
            else
            {
                _affichage.afficherRouge(true, "Erreur dans la taille du donjon");
                tailleDonjon();
            }
        }
        catch (InputMismatchException | NumberFormatException erreur)
        {
            _affichage.afficherRouge(true, "\nErreur dans la saisie des tailles du donjon, il ne doit y avoir que des nombres");
            sc.nextLine();
            tailleDonjon();
        }
        tailleDonjon();
        return null;
    }

    public void ajoutObstacle(Donjon DJ, MJ mj)
    {
        _affichage.afficher(true, "\nVoulez-vous mettre des obstacles (o/n) ?");
        String choix = sc.nextLine();

        while (!choix.equals("n")) {
            if (choix.equals("o")) {
                int[] pos = choixCase("de l'obstacle");
                mj.addObst(DJ, pos);
                DJ.afficherDJ();
                _affichage.afficher(true, "\n\nCréer un autre obstacle (o/n) ?");
                choix = sc.nextLine();
            }
            else
            {
                _affichage.afficherRouge(true, "Mauvaise valeur rentrée.");
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
                _affichage.afficher(true, "\n\nCréer un autre monstre (o/n) ?");
                choix = sc.nextLine();
            }
            else
            {
                _affichage.afficherRouge(true, "Mauvaise valeur rentrée.");
                ajoutMonstre(DJ, mj);
            }
        }
    }

    public void ajoutEquipement(Donjon DJ, MJ mj)
    {
        _affichage.afficher(true, "\nVoulez-vous créer des Equipements (o/n) ?");
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
                _affichage.afficher(true, texte);
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
                    _affichage.afficherRouge(true, "/!\\ ATTENTION : Equipement non crée");
                } else {
                    int[] pos = choixCase("de l'équipement : " + equip);
                    DJ.afficherDJ();
                    mj.posEquip(DJ, equip, pos);
                    DJ.afficherDJ();
                }
                _affichage.afficher(true, "\n\nCréer un autre equipement (o/n)?");
                choix = sc.nextLine();
            }
            else
            {
                _affichage.afficherRouge(true, "Mauvaise valeur rentrée.");
                ajoutEquipement(DJ, mj);
            }
        }
    }



    public int[] choixCase(String context)
    {
        _affichage.afficher(true, "\n\nChoisir la case ", context, _cl.cyan("   [lettre majuscule][nombre]"));
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
                _affichage.afficherRouge(true, "Les cases sont dans le format suivant : ", _cl.cyan("   [lettre majuscule][nombre]"));
                return null;
            }

            return posi;
        }
        catch (NullPointerException | StringIndexOutOfBoundsException | NumberFormatException erreur)
        {
            _affichage.afficherRouge(true, "Les cases sont dans le format suivant : ", _cl.cyan("   [lettre majuscule][nombre]"));
            return null;
        }
    }

    public Monstre creationMonstre(MJ mj)
    {
        _affichage.afficher(true, "\n\n===== Nouveau Monstre =====");
        _affichage.afficher(true, "Espèce ?");
        String espece = sc.nextLine();
        while (espece.isEmpty())
        {
            _affichage.afficherRouge(true, "L'espèce ne peut pas être vide!");
            _affichage.afficher(true, "Espèce ?");
            espece = sc.nextLine();
        }
        _affichage.afficher(true, "Symbole d'affichage ?   (3 charactères max)");
        String symb = sc.nextLine();
        while ((symb.isEmpty()) || (symb.length() > 3))
        {
            _affichage.afficherRouge(true, "Le symbole doit être entre 1 et 3 caractères!");
            _affichage.afficher(true, "Symbole d'affichage ?   (3 charactères max) ?");
            symb = sc.nextLine();
        }
        try {
            _affichage.afficher(true, "Portée de l'attaque ?");
            int portee = sc.nextInt();
            sc.nextLine();
            _affichage.afficher(true, "Nombre de dés pour le calcul de l'attaque ?");
            int nbrDeDeg = sc.nextInt();
            sc.nextLine();
            _affichage.afficher(true, "Nombre de face pour les dés pour le calcul de l'attaque ?");
            int nbrFaceDeDeg = sc.nextInt();
            sc.nextLine();
            _affichage.afficher(true, "Nombre de dés pour le calcul des charactéristiques ?");
            int nbrDeCha = sc.nextInt();
            sc.nextLine();
            _affichage.afficher(true, "Nombre de face pour les dés pour le calcul des charactéristiques ?");
            int nbrFaceDeCha = sc.nextInt();
            sc.nextLine();

            return mj.createM(espece, symb, portee, new De(nbrDeDeg, nbrFaceDeDeg), new De(nbrDeCha, nbrFaceDeCha));

        }
        catch (InputMismatchException | NumberFormatException erreur)
        {
            _affichage.afficher(true, _cl.rouge("\nSeul l'espèce et le symbole du monstre peut contenir autre chose que des entier."), "\nRecomencez");
            creationMonstre(mj);
        }
        creationMonstre(mj);
        return null;
    }

    public String contextDonjon()
    {
        _affichage.afficher(true, "Quel est le context ?");
        return sc.nextLine();
    }

    public void mjCommenteAction(MJ mj)
    {
        _affichage.afficher(true, "Le MJ commente l'action effectuée");
        String comm = sc.nextLine();
        mj.comAction(comm);
    }

    public void persoCommenteAction(Personnage perso)
    {
        _affichage.afficher(true, "Le personnage commente l'action effectuée");
        String comm = sc.nextLine();
        perso.comAction(comm);
    }

    public Equipement equiperEquip(Personnage pers)
    {
        if (!pers.getStock().isEmpty()) {
            _affichage.afficher(true, "Quel équipement équiper ?");

            int i = 1;
            for (Equipement eqi : pers.getStock()) {
                _affichage.afficher(true, i, "- ", eqi.getName());
                i++;
            }

            try {
                int equ = sc.nextInt() - 1;
                sc.nextLine();
                return pers.getStock().get(equ);
            }
            catch (InputMismatchException | NullPointerException | NumberFormatException | IndexOutOfBoundsException erreur)
            {
                _affichage.afficherRouge(true, "Mauvaise valeur rentrée.");
                equiperEquip(pers);
            }
        }
        return null;
    }


    public void depViv(Donjon dj, MJ mj)
    {
        int choix = 1;

        _affichage.afficher(true, "\nChoisissez un joueur ou un monstre à déplacer :");
        for (Personnage pers : dj.getListePerso()) {
            _affichage.afficher(true, choix++, "- ", pers);
        }
        for (Monstre mons : dj.getListeMonstre()) {
            _affichage.afficher(true, choix++, "- ", mons);
        }

        try {
            int perso = sc.nextInt() - 1;
            sc.nextLine();

            int[] posD;

            if (perso < dj.getListePerso().size()) {
                posD = dj.getListePerso().get(perso).getPos();
            }
            else {
                posD = dj.getListeMonstre().get(perso - dj.getListePerso().size()).getPos();
            }

            int[] posF = choixCase("où mettre l'entité");

            mj.depViv(dj, posD, posF);
        }
        catch (InputMismatchException | NullPointerException | NumberFormatException | IndexOutOfBoundsException erreur)
        {
            _affichage.afficher(true, _cl.rouge("\nErreur dans la saisie.") + "\nRecomencez");
            sc.nextLine();
            depViv(dj, mj);
        }
    }


    public StatusDonjon degatPersonnage(Donjon dj, MJ mj)
    {
        int choix = 1;
        StatusDonjon val = StatusDonjon.NORMAL;

        _affichage.afficher(true, "\nChoisissez un joueur :");
        for (Personnage pers : dj.getListePerso())
        {
            _affichage.afficher(true, choix++, "- ", pers);
        }

        choix = sc.nextInt() - 1;
        sc.nextLine();

        try {
            int dgt = infligerDegat();

            val = mj.degatJoueur(dj, choix, dgt);
        }
        catch (InputMismatchException | NullPointerException | NumberFormatException | IndexOutOfBoundsException erreur)
        {
            _affichage.afficher(true, _cl.rouge("\nErreur dans la saisie.") + "\nRecomencez");
            degatPersonnage(dj, mj);
        }

        return val;
    }


    public StatusDonjon degatMonstre(Donjon dj, MJ mj)
    {
        int choix = 1;
        StatusDonjon val;

        _affichage.afficher(true, "\nChoisissez un monstre :");
        for (Monstre mons : dj.getListeMonstre()) {
            _affichage.afficher(true, choix++, "- ", mons);
        }

        try {
            int dgt = infligerDegat();

            val = mj.degatMonstre(dj, choix, dgt);
        }
        catch (InputMismatchException | NullPointerException | NumberFormatException | IndexOutOfBoundsException erreur)
        {
            _affichage.afficher(true, _cl.rouge("\nErreur dans la saisie.") + "\nRecomencez");
            val = degatMonstre(dj, mj);
        }

        return val;
    }

    private int infligerDegat() {
        _affichage.afficher(true, "Combien de dé(s) pour infliger les dégats ?");
        int nbDe = sc.nextInt();
        sc.nextLine();
        _affichage.afficher(true, "Combien de faces pour les dés ?");
        int nbFaceDe = sc.nextInt();
        sc.nextLine();

        return new De(nbDe, nbFaceDe).roll();
    }

}
