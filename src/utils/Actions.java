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

    public void lancerSort(Donjon DJ, Personnage person) {
        if (!person.getSorts().isEmpty()) {
            int choix = 1;
            System.out.println("Liste des sorts :");
            for (Sort sort : person.getSorts()) {
                System.out.println(choix++ + "- " + sort.getNom() + " : " + sort.getDescription());
            }
            System.out.println("Lequel voulez-vous lancer ?");
            try {
                choix = sc.nextInt();
                sc.nextLine();
                if (person.getClasse().equals("Clerc")) {
                    if (choix == 1) {
                        int choixPerso = 1;
                        for (Personnage perso : DJ.getListePerso()) {
                            System.out.println(choixPerso + "- " + perso.toString());
                        }
                        choixPerso = sc.nextInt();
                        sc.nextLine();
                        ((Guerison) person.getSorts().getFirst()).lancer(DJ.getListePerso().get(choixPerso - 1));
                    }
                    else {
                        throw new Exception();
                    }
                }
                else if (person.getClasse().equals("Magicien")) {
                    switch (choix) {
                        case 1:
                            int choixPerso = 1;
                            for (Personnage perso : DJ.getListePerso()) {
                                System.out.println("\t" + choixPerso + "- " + perso.toString());
                                choixPerso++;
                            }
                            System.out.println("Choisissez un allie a soigner :");
                            choixPerso = sc.nextInt();
                            sc.nextLine();
                            ((Guerison) person.getSorts().getFirst()).lancer(DJ.getListePerso().get(choixPerso - 1));
                            break;
                        case 2:
                            int choixEntite1 = 1;

                            for (Personnage perso : DJ.getListePerso()) {
                                System.out.println("\t" + choixEntite1 + "- " + perso.toString());
                                choixEntite1++;
                            }
                            int choixEntite2 = choixEntite1;
                            for (Monstre mons : DJ.getListeMonstre()) {
                                System.out.println("\t" + choixEntite2 + "- " + mons.getNom());
                                choixEntite2++;
                            }

                            System.out.println("Choisissez la première entité à téléporter :");
                            choixEntite1 = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Choisissez la deuxième entité à téléporter :");
                            choixEntite2 = sc.nextInt();
                            sc.nextLine();

                            if (choixEntite1 <= DJ.getListePerso().size() && choixEntite2 <= DJ.getListePerso().size()) {
                                ((BoogieWoogie) person.getSorts().get(1)).lancer(DJ.getListePerso().get(choixEntite1-1), DJ.getListePerso().get(choixEntite2-1), DJ);
                            }
                            else if (choixEntite1 > DJ.getListePerso().size() && choixEntite2 > DJ.getListePerso().size()) {
                                ((BoogieWoogie) person.getSorts().get(1)).lancer(DJ.getListeMonstre().get(choixEntite1-1-DJ.getListePerso().size()), DJ.getListeMonstre().get(choixEntite2-1-DJ.getListePerso().size()), DJ);
                            }
                            else if (choixEntite1 <= DJ.getListePerso().size() && choixEntite2 > DJ.getListePerso().size()) {
                                ((BoogieWoogie) person.getSorts().get(1)).lancer(DJ.getListePerso().get(choixEntite1-1), DJ.getListeMonstre().get(choixEntite2-1-DJ.getListePerso().size()), DJ);
                            }
                            else {
                                ((BoogieWoogie) person.getSorts().get(1)).lancer(DJ.getListeMonstre().get(choixEntite1-1-DJ.getListePerso().size()), DJ.getListePerso().get(choixEntite2-1), DJ);
                            }
                            break;
                        case 3:
                            int choixArme = 1;
                            for (Personnage perso : DJ.getListePerso()) {
                                System.out.println("Personnage : " + perso.toString());
                                for (Equipement equipement : perso.getStock()) {
                                    if (equipement.getTypeEquip().equals(TypeEquipement.ARME)) {
                                        System.out.println("\t" + choixArme++ + "- " + equipement.getName());
                                    }
                                }
                                for (Equipement equipement : perso.getEquipees()) {
                                    if (equipement.getTypeEquip().equals(TypeEquipement.ARME)) {
                                        System.out.println("\t" + choixArme++ + "- " + "(Equipée) " + equipement.getName());
                                    }
                                }
                            }
                            System.out.println("Choisissez une arme a améliorer (+1 dgt, +1 touche) :");
                            choixArme = sc.nextInt();
                            sc.nextLine();
                            boolean ok = false;
                            int idArme = 1;
                            for (Personnage perso : DJ.getListePerso()) {
                                for (Equipement equipement : perso.getStock()) {
                                    if (equipement.getTypeEquip().equals(TypeEquipement.ARME)) {
                                        if (choixArme == idArme) {
                                            ((ArmeMagique)perso.getSorts().get(2)).lancer((Arme)equipement);
                                            ok = true;
                                            break;
                                        }
                                        else {
                                            idArme++;
                                        }
                                    }
                                }
                                for (Equipement equipement : perso.getEquipees()) {
                                    if (equipement.getTypeEquip().equals(TypeEquipement.ARME)) {
                                        if (choixArme == idArme) {
                                            ((ArmeMagique)perso.getSorts().get(2)).lancer((Arme)equipement);
                                            ok = true;
                                            break;
                                        } else {
                                            idArme++;
                                        }
                                    }
                                }
                                if (!ok) {
                                    throw new Exception();
                                }
                            }
                            break;
                        default:
                            throw new Exception();
                    }
                }
            }
            catch (Exception e) {
                System.out.println(_cl.rouge() + "Choix invalide : " + e + _cl.reset());
                sc.nextLine();
                lancerSort(DJ, person);
            }

        }
        else {
            System.out.println(_cl.rouge() + "Vous n'avez pas de sort..." + _cl.reset());
        }
    }





    public StatusDonjon actionVivant(Donjon DJ, Vivant vivant, ArrayList<Monstre> listeMonstre, ArrayList<Personnage> listePersonnage)
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
            int choix = sc.nextInt();
            sc.nextLine();
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
                        lancerSort(DJ, ((Personnage)vivant));
                    }
                    else
                    {
                        System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                        actionVivant(DJ, vivant,  listeMonstre, listePersonnage);
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
                        actionVivant(DJ, vivant,  listeMonstre, listePersonnage);
                    }
                    break;
                default:
                    System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                    actionVivant(DJ, vivant,  listeMonstre, listePersonnage);
            }
        }
        catch (InputMismatchException | NumberFormatException | NullPointerException erreur)
        {
            System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
            sc.nextLine();
            actionVivant(DJ, vivant,  listeMonstre, listePersonnage);
        }
        return val;
    }





    public StatusDonjon actionMjFinTour(Donjon DJ, MJ mj, ArrayList<Vivant> listeVivant)
    {
        StatusDonjon val = StatusDonjon.NORMAL;

        System.out.println("\n\n\nQue veut faire le Maitre du Jeu ?");
        System.out.println("1- Ne rien faire\n2- Déplacer un joueur/monstre\n3- Faire des dégats à un joueur/monstre\n4- Ajouter des obstacles");
        try {
            int choix = sc.nextInt();
            sc.nextLine();
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
            sc.nextLine();
            actionMjFinTour(DJ, mj, listeVivant);
        }
        return val;
    }

}
