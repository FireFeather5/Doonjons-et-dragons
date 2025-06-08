package utils;

import donjon.Donjon;
import entite.Monstre;
import entite.Vivant;
import entite.equipement.Equipement;
import entite.equipement.arme.Arme;
import entite.personnages.Personnage;
import sort.ArmeMagique;
import sort.BoogieWoogie;
import sort.Guerison;
import sort.Sort;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Actions {

    private final Couleurs _couleur = new Couleurs();
    private final Inputs _input = new Inputs();

    private final Scanner sc = new Scanner(System.in);

    public Actions()
    {

    }
    public StatusDonjon actionVivant(Donjon DJ, Vivant vivant, ArrayList<Monstre> listeMonstre, ArrayList<Personnage> listePersonnage, ArrayList<Vivant> listeVivant)
    {
        StatusDonjon statusDonj = StatusDonjon.NORMAL;
        boolean sort = false;
        boolean ramasser = false;
        boolean personnage = false;

        System.out.println("\nChoisir une action :\n1- Se déplacer\n2- Attaquer");


        if (vivant.getTypeVivant().equals(TypeVivant.PERSONNAGE))
        {
            personnage = true;

            System.out.println("3- S'équiper");

            if (((Personnage)vivant).getClasse().equals("Clerc") || ((Personnage)vivant).getClasse().equals("Magicien"))
            {
                System.out.println("4- Sorts");
                sort = true;
            }
            else
            {
                System.out.println(_couleur.gris() + "4- Sorts" + _couleur.reset());
            }

            if (((Personnage)vivant).peutRam())
            {
                System.out.println("5- Ramasser");
                ramasser = true;
            }
            else
            {
                System.out.println(_couleur.gris() + "5- Ramasser" + _couleur.reset());
            }
        }

        try {
            int choix = Integer.parseInt(sc.nextLine());
            Erreurs erreurs = Erreurs.PROBLEME_CASE;

            switch (choix) {
                case 1:
                    while(erreurs.equals(Erreurs.PROBLEME_CASE))
                    {
                        int[] pos = _input.choixCase("où se déplacer");
                        erreurs = vivant.seDeplacer(DJ, pos);
                        if (erreurs.equals(Erreurs.TOUT_OK))
                        {
                            System.out.println("Déplacement effectué");
                        }
                        else if (erreurs.equals(Erreurs.PROBLEME_CASE))
                        {
                            System.out.println(_couleur.rouge() + "Problème dans le choix de la case" + _couleur.reset());
                        }
                        else
                        {
                            System.out.println(_couleur.rouge() + vivant + " n'a pas une vitesse suffisante pour se déplacer." + _couleur.reset());
                        }
                    }
                    break;
                case 2:
                    statusDonj = StatusDonjon.ERREUR;
                    if (personnage)
                    {
                        while (statusDonj.equals(StatusDonjon.ERREUR))
                        {
                            int nbMonstre = 0;
                            System.out.println("\n");
                            for (Monstre mons : listeMonstre)
                            {
                                nbMonstre++;
                                System.out.println(nbMonstre + "- " + mons);
                            }

                            try {
                                int monstre = Integer.parseInt(sc.nextLine());

                                Monstre mons = listeMonstre.get(monstre-1);

                                statusDonj = ((Personnage) vivant).attaquer(DJ, mons);

                            } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException erreur) {
                                System.out.println(_couleur.rouge() + "\nErreur dans le choix du monstre" + _couleur.reset());
                            }
                        }
                    }
                    else
                    {
                        while (statusDonj.equals(StatusDonjon.ERREUR))
                        {
                            int nbPerso = 0;
                            System.out.println("\n");
                            for (Personnage perso : listePersonnage)
                            {
                                nbPerso++;
                                System.out.println(nbPerso + "- " + perso);
                            }

                            try {
                                int pers = Integer.parseInt(sc.nextLine());

                                Personnage perso = listePersonnage.get(pers-1);

                                statusDonj = ((Monstre) vivant).attaquer(DJ, perso);

                            } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException erreur) {
                                System.out.println(_couleur.rouge() + "\nErreur dans le choix du personnage" + _couleur.reset());
                            }
                        }
                    }
                    break;
                case 3:
                    if (personnage)
                    {
                        boolean reussi = false;
                        while (!reussi)
                        {
                            Equipement equip = _input.equiperEquip(((Personnage)vivant));
                            reussi = ((Personnage)vivant).sEquiper(equip);
                            if (reussi)
                            {
                                System.out.println(equip.getName() + " à bien été équipé");
                            }
                        }
                    }
                    else
                    {
                        System.out.println(_couleur.rouge() + "Mauvais choix d'action" + _couleur.reset());
                        actionVivant(DJ, vivant,  listeMonstre, listePersonnage, listeVivant);
                    }
                    break;
                case 4:
                    if (sort)
                    {
                        lancerSort(DJ, (Personnage)vivant, listePersonnage, listeVivant);
                    }
                    else
                    {
                        System.out.println(_couleur.rouge() + "Mauvais choix d'action" + _couleur.reset());
                        actionVivant(DJ, vivant,  listeMonstre, listePersonnage, listeVivant);
                    }
                    break;
                case 5:
                    if (ramasser)
                    {
                        ((Personnage)vivant).ramasser(DJ);
                    }
                    else
                    {
                        System.out.println(_couleur.rouge() + "Mauvais choix d'action" + _couleur.reset());
                        actionVivant(DJ, vivant,  listeMonstre, listePersonnage, listeVivant);
                    }
                    break;
                default:
                    System.out.println(_couleur.rouge() + "Mauvais choix d'action" + _couleur.reset());
                    actionVivant(DJ, vivant,  listeMonstre, listePersonnage, listeVivant);
            }
        }
        catch (NumberFormatException | NullPointerException erreur)
        {
            System.out.println(_couleur.rouge() + "Mauvais choix d'action" + _couleur.reset());
            actionVivant(DJ, vivant,  listeMonstre, listePersonnage, listeVivant);
        }
        return statusDonj;
    }





    public void lancerSort(Donjon DJ, Personnage person, ArrayList<Personnage> listePersonnage, ArrayList<Vivant> listeVivant)
    {
        if (!person.getSorts().isEmpty())
        {
            boolean magicien = false;

            if (person.getClasse().equals("Magicien"))
            {
                magicien = true;
            }

            int choix = 1;

            System.out.println("\nLequel voulez-vous lancer ?");
            for (Sort sort : person.getSorts())
            {
                System.out.println(choix++ + "- " + sort.getNom() + " : " + sort.getDescription());
            }

            try {
                choix = Integer.parseInt(sc.nextLine());


                switch (choix) {

                    case 1:
                        int choixPerso = 1;

                        System.out.println("\nChoisissez un allié à soigner :");

                        for (Personnage perso : listePersonnage)
                        {
                            System.out.println(choixPerso + "- " + perso);
                            choixPerso++;
                        }

                        choixPerso = Integer.parseInt(sc.nextLine());

                        int soin = ((Guerison) person.getSorts().get(0)).lancer(listePersonnage.get(choixPerso - 1));

                        System.out.println("\n" + listePersonnage.get(choixPerso - 1) + " à été soigné de " + soin + " pv.");

                        break;

                    case 2:
                        if (magicien) {

                            int choixVivant1 = 1;

                            System.out.println("\n");
                            for (Vivant vivant : listeVivant) {
                                System.out.println(choixVivant1 + "- " + vivant);
                                choixVivant1++;
                            }

                            System.out.println("\nChoisissez la première entité à téléporter :");
                            choixVivant1 = Integer.parseInt(sc.nextLine());

                            System.out.println("Choisissez la deuxième entité à téléporter :");
                            int choixVivant2 = Integer.parseInt(sc.nextLine());

                            ((BoogieWoogie) person.getSorts().get(1)).lancer(listeVivant.get(choixVivant1 - 1), listeVivant.get(choixVivant2 - 1), DJ);
                        }
                        break;

                    case 3:
                        if (magicien) {

                            int choixArme = 1;

                            for (Personnage perso : listePersonnage) {
                                System.out.println("Personnage : " + perso);

                                for (Equipement equipement : perso.getStock()) {
                                    if (equipement.getTypeEquip().equals(TypeEquipement.ARME)) {
                                        System.out.println(choixArme + "- " + equipement.getName());
                                        choixArme++;
                                    }
                                }
                                for (Equipement equipement : perso.getEquipees()) {
                                    if (equipement.getTypeEquip().equals(TypeEquipement.ARME)) {
                                        System.out.println(choixArme + "- (Equipée) " + equipement.getName());
                                        choixArme++;
                                    }
                                }
                            }

                            System.out.println("Choisissez une arme a améliorer (+1 dégat, +1 touche) :");
                            choixArme = Integer.parseInt(sc.nextLine());

                            boolean reussi = false;

                            int idArme = 1;

                            for (Personnage perso : listePersonnage)
                            {
                                for (Equipement equipement : perso.getStock())
                                {
                                    if (equipement.getTypeEquip().equals(TypeEquipement.ARME))
                                    {
                                        if (choixArme == idArme)
                                        {
                                            ((ArmeMagique) person.getSorts().get(2)).lancer((Arme) equipement);
                                            System.out.println("\n" + equipement + "à été amélioré.");
                                            reussi = true;
                                            break;
                                        }
                                        else
                                        {
                                            idArme++;
                                        }
                                    }
                                }
                                for (Equipement equipement : perso.getEquipees())
                                {
                                    if (equipement.getTypeEquip().equals(TypeEquipement.ARME))
                                    {
                                        if (choixArme == idArme)
                                        {
                                            ((ArmeMagique) person.getSorts().get(2)).lancer((Arme) equipement);
                                            System.out.println("\n" + equipement + "à été amélioré.");
                                            reussi = true;
                                            break;
                                        }
                                        else
                                        {
                                            idArme++;
                                        }
                                    }
                                }
                            }

                            if (!reussi)
                            {
                                System.out.println(_couleur.rouge() + "Mauvais choix d'action" + _couleur.reset());
                                lancerSort(DJ, person, listePersonnage, listeVivant);
                            }
                        }
                        break;

                    default:
                        System.out.println(_couleur.rouge() + "Mauvais choix d'action" + _couleur.reset());
                        lancerSort(DJ, person, listePersonnage, listeVivant);
                }


            }
            catch (InputMismatchException | NumberFormatException | NullPointerException erreur) {
                System.out.println(_couleur.rouge() + "Mauvais choix d'action" + _couleur.reset());
                lancerSort(DJ, person, listePersonnage, listeVivant);
            }

        }
        else {
            System.out.println(_couleur.rouge() + "Vous n'avez pas de sort..." + _couleur.reset());
        }
    }





    public StatusDonjon actionMjFinTour(Donjon DJ, MJ mj, ArrayList<Vivant> listeVivant)
    {
        StatusDonjon statusDonj = StatusDonjon.NORMAL;

        System.out.println("\n\n\nQue veut faire le Maitre du Jeu ?");
        System.out.println("1- Ne rien faire\n2- Déplacer un joueur/monstre\n3- Faire des dégats à un joueur/monstre\n4- Ajouter des obstacles");
        try {
            int choix = Integer.parseInt(sc.nextLine());
            switch (choix) {
                case 1:
                    break;
                case 2:
                    _input.mjDeplaceVivant(DJ, mj, listeVivant);
                    break;
                case 3:
                        statusDonj = _input.mjDegatVivant(DJ, mj, listeVivant);
                    break;
                case 4:
                    _input.ajoutObstacle(DJ, mj);
                    break;
                default:
                    System.out.println(_couleur.rouge() + "Mauvais choix d'action" + _couleur.reset());
                    statusDonj = actionMjFinTour(DJ, mj, listeVivant);
            }
        }
        catch (InputMismatchException | NumberFormatException | NullPointerException erreur) {
            System.out.println(_couleur.rouge() + "Mauvais choix d'action" + _couleur.reset());
            actionMjFinTour(DJ, mj, listeVivant);
        }
        return statusDonj;
    }

}
