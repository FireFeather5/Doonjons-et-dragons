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

    private final Couleurs _cl = new Couleurs();
    private final Inputs _input = new Inputs();

    private final Scanner sc = new Scanner(System.in);

    public Actions()
    {

    }
                    // A REVOIR

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

            System.out.println("Lequel voulez-vous lancer ?");
            for (Sort sort : person.getSorts())
            {
                System.out.println(choix++ + "- " + sort.getNom() + " : " + sort.getDescription());
            }

            try {
                choix = Integer.parseInt(sc.nextLine());


                switch (choix) {

                    case 1:
                        int choixPerso = 1;

                        for (Personnage perso : listePersonnage)
                        {
                            System.out.println(choixPerso + "- " + perso);
                            choixPerso++;
                        }

                        System.out.println("Choisissez un allié à soigner :");
                        choixPerso = Integer.parseInt(sc.nextLine());

                        ((Guerison) person.getSorts().get(0)).lancer(DJ.getListePerso().get(choixPerso - 1));

                        break;

                    case 2:
                        if (magicien) {

                            int choixVivant1 = 1;

                            for (Vivant vivant : listeVivant) {
                                System.out.println(choixVivant1 + "- " + vivant);
                                choixVivant1++;
                            }

                            System.out.println("Choisissez la première entité à téléporter :");
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

                            boolean ok = false;

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
                                            ok = true;
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
                                            ok = true;
                                            break;
                                        }
                                        else
                                        {
                                            idArme++;
                                        }
                                    }
                                }
                            }

                            if (!ok)
                            {
                                System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                                lancerSort(DJ, person, listePersonnage, listeVivant);
                            }
                        }
                        break;

                    default:
                        System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                        lancerSort(DJ, person, listePersonnage, listeVivant);
                }


            }
            catch (InputMismatchException | NumberFormatException | NullPointerException erreur) {
                System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                lancerSort(DJ, person, listePersonnage, listeVivant);
            }

        }
        else {
            System.out.println(_cl.rouge() + "Vous n'avez pas de sort..." + _cl.reset());
        }
    }





    public StatusDonjon actionVivant(Donjon DJ, Vivant vivant, ArrayList<Monstre> listeMonstre, ArrayList<Personnage> listePersonnage, ArrayList<Vivant> listeVivant)
    {
        StatusDonjon val = StatusDonjon.NORMAL;
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
                System.out.println(_cl.blanc() + "4- Sorts" + _cl.reset());
            }

            if (((Personnage)vivant).peutRam())
            {
                System.out.println("5- Ramasser");
                ramasser = true;
            }
            else
            {
                System.out.println(_cl.blanc() + "5- Ramasser" + _cl.reset());
            }
        }

        try {
            int choix = Integer.parseInt(sc.nextLine());
            boolean ok = false;

            switch (choix) {
                case 1:
                    while(!ok) {
                        int[] pos = _input.choixCase("où se déplacer");
                        ok = vivant.seDeplacer(DJ, pos);
                        if (ok) {
                            System.out.println("Déplacement effectué");
                        }
                        else {
                            System.out.println(_cl.rouge() + "Problème dans le choix de la case" + _cl.reset());
                        }
                    }
                    break;
                case 2:
                    val = StatusDonjon.ERREUR;
                    if (personnage)
                    {
                        while (val.equals(StatusDonjon.ERREUR))
                        {
                            int nbMonstre = 0;
                            for (Monstre mons : listeMonstre)
                            {
                                nbMonstre++;
                                System.out.println(nbMonstre + "- " + mons);
                            }

                            try {
                                int monstre = Integer.parseInt(sc.nextLine());

                                Monstre mons = listeMonstre.get(monstre-1);

                                val = ((Personnage) vivant).attaquer(DJ, mons);

                            } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException erreur) {
                                System.out.println(_cl.rouge() + "\nErreur dans le choix du monstre" + _cl.reset());
                            }
                        }
                    }
                    else
                    {
                        while (val.equals(StatusDonjon.ERREUR))
                        {
                            int nbPerso = 0;
                            for (Personnage perso : listePersonnage)
                            {
                                nbPerso++;
                                System.out.println(nbPerso + "- " + perso);
                            }

                            try {
                                int pers = Integer.parseInt(sc.nextLine());

                                Personnage perso = listePersonnage.get(pers-1);

                                val = ((Monstre) vivant).attaquer(DJ, perso);

                            } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException erreur) {
                                System.out.println(_cl.rouge() + "\nErreur dans le choix du personnage" + _cl.reset());
                            }
                        }
                    }
                    break;
                case 3:
                    if (personnage) {
                        while (!ok) {
                            Equipement equip = _input.equiperEquip(((Personnage)vivant));
                            ok = ((Personnage)vivant).sEquiper(equip);
                            if (ok) {
                                System.out.println(equip.getName() + " à bien été équipé");
                            } else {
                                System.out.println(_cl.rouge() + "ERREUR : l'equipement n'est pas dans l'inventaire" + _cl.reset());
                            }
                        }
                    }
                    break;
                case 4:
                    if (sort)
                    {
                        lancerSort(DJ, (Personnage)vivant, listePersonnage, listeVivant);
                    }
                    else
                    {
                        System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
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
                        System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                        actionVivant(DJ, vivant,  listeMonstre, listePersonnage, listeVivant);
                    }
                    break;
                default:
                    System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                    actionVivant(DJ, vivant,  listeMonstre, listePersonnage, listeVivant);
            }
        }
        catch (NumberFormatException | NullPointerException erreur)
        {
            System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
            actionVivant(DJ, vivant,  listeMonstre, listePersonnage, listeVivant);
        }
        return val;
    }





    public StatusDonjon actionMjFinTour(Donjon DJ, MJ mj, ArrayList<Vivant> listeVivant)
    {
        StatusDonjon val = StatusDonjon.NORMAL;

        System.out.println("\n\n\nQue veut faire le Maitre du Jeu ?");
        System.out.println("1- Ne rien faire\n2- Déplacer un joueur/monstre\n3- Faire des dégats à un joueur/monstre\n4- Ajouter des obstacles");
        try {
            int choix = Integer.parseInt(sc.nextLine());
            switch (choix) {
                case 1:
                    break;
                case 2:
                    _input.depViv(DJ, mj, listeVivant);
                    break;
                case 3:
                        val = _input.degatVivant(DJ, mj, listeVivant);
                    break;
                case 4:
                    _input.ajoutObstacle(DJ, mj);
                    break;
                default:
                    System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                    val = actionMjFinTour(DJ, mj, listeVivant);
            }
        }
        catch (InputMismatchException | NumberFormatException | NullPointerException erreur) {
            System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
            actionMjFinTour(DJ, mj, listeVivant);
        }
        return val;
    }

}
