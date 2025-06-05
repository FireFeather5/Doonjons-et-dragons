package Utils;

import donjon.Donjon;
import entite.Monstre;
import entite.equipement.Equipement;
import entite.equipement.arme.Arme;
import entite.personnages.Personnage;
import sort.BoogieWoogie;
import sort.Guerison;
import sort.Sort;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Actions {

    private final Couleurs _cl = new Couleurs();
    private final Inputs _input = new Inputs();

    Scanner sc = new Scanner(System.in);

    public Actions()
    {

    }

    public StatusDonjon actionPerso(Donjon DJ, Personnage perso)
    {
        StatusDonjon val = StatusDonjon.NORMAL;

        System.out.println("peur ram : " + perso.peutRam());
        if (perso.peutRam())
        {
            if (perso.getClasse().equals("Clerc") || perso.getClasse().equals("Magicien")) {
                System.out.println("\nChoisir une action :\n1- Se déplacer\n2- Attaquer\n3- S'équiper\n4- Sorts\n5- Ramasser");
                try {
                    int choix = sc.nextInt();

                    switch (choix) {
                        case 1:
                            boolean test = false;
                            while(!test) {
                                int[] pos = _input.choixCase("où se déplacer");
                                test = perso.seDeplacer(DJ, pos);
                            }
                            break;
                        case 2:
                            val = StatusDonjon.ERREUR_CHOIX_CASE;
                            while (val.equals(StatusDonjon.ERREUR_CHOIX_CASE))
                            {
                                int[] posAtt = _input.choixCase("à attaquer");
                                val = perso.attaquer(DJ, posAtt);
                            }
                            break;
                        case 3:
                            Equipement equip = _input.equiperEquip(perso);
                            perso.sEquiper(equip);
                            break;
                        case 4:
                            lancerSort(DJ, perso);
                            break;
                        case 5:
                            perso.ramasser(DJ);
                            break;
                        default:
                            System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                            actionPerso(DJ, perso);
                    }
                } catch (InputMismatchException | NumberFormatException | NullPointerException erreur) {
                    System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                    actionPerso(DJ, perso);
                }
            }
            else {
                System.out.println("\nChoisir une action :\n1- Se déplacer\n2- Attaquer\n3- S'équiper\n4- Ramasser");
                try {
                    int choix = sc.nextInt();

                    switch (choix) {
                        case 1:
                            boolean test = false;
                            while(!test) {
                                int[] pos = _input.choixCase("où se déplacer");
                                test = perso.seDeplacer(DJ, pos);
                            }
                            break;
                        case 2:
                            val = StatusDonjon.ERREUR_CHOIX_CASE;
                            while (val.equals(StatusDonjon.ERREUR_CHOIX_CASE))
                            {
                                int[] posAtt = _input.choixCase("à attaquer");
                                val = perso.attaquer(DJ, posAtt);
                            }
                            break;
                        case 3:
                            Equipement equip = _input.equiperEquip(perso);
                            perso.sEquiper(equip);
                            break;
                        case 4:
                            perso.ramasser(DJ);
                            break;
                        default:
                            System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                            actionPerso(DJ, perso);
                    }
                } catch (InputMismatchException | NumberFormatException | NullPointerException erreur) {
                    System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                    actionPerso(DJ, perso);
                }
            }
        }
        else
        {
            if (perso.getClasse().equals("Clerc") || perso.getClasse().equals("Magicien")) {
                System.out.println("\nChoisir une action :\n1- Se déplacer\n2- Attaquer\n3- S'équiper\n4- Sorts");

                try {
                    int choix = sc.nextInt();
                    switch (choix) {
                        case 1:
                            boolean test = false;
                            while(!test) {
                                int[] pos = _input.choixCase("où se déplacer");
                                test = perso.seDeplacer(DJ, pos);
                            }
                            break;
                        case 2:
                            val = StatusDonjon.ERREUR_CHOIX_CASE;
                            while (val.equals(StatusDonjon.ERREUR_CHOIX_CASE))
                            {
                                int[] posAtt = _input.choixCase("à attaquer");
                                val = perso.attaquer(DJ, posAtt);
                            }
                            break;
                        case 3:
                            Equipement equip = _input.equiperEquip(perso);
                            perso.sEquiper(equip);
                            break;
                        case 4:
                            lancerSort(DJ, perso);
                            break;
                        default:
                            System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                            actionPerso(DJ, perso);
                    }
                } catch (InputMismatchException | NumberFormatException | NullPointerException erreur) {
                    System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                    actionPerso(DJ, perso);
                }
            }
            else {
                System.out.println("\nChoisir une action :\n1- Se déplacer\n2- Attaquer\n3- S'équiper");

                try {
                    int choix = sc.nextInt();
                    switch (choix) {
                        case 1:
                            boolean test = false;
                            while(!test) {
                                int[] pos = _input.choixCase("où se déplacer");
                                test = perso.seDeplacer(DJ, pos);
                            }
                            break;
                        case 2:
                            val = StatusDonjon.ERREUR_CHOIX_CASE;
                            while (val.equals(StatusDonjon.ERREUR_CHOIX_CASE))
                            {
                                int[] posAtt = _input.choixCase("à attaquer");
                                val = perso.attaquer(DJ, posAtt);
                            }
                            break;
                        case 3:
                            Equipement equip = _input.equiperEquip(perso);
                            perso.sEquiper(equip);
                            break;
                        default:
                            System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                            actionPerso(DJ, perso);
                    }
                } catch (InputMismatchException | NumberFormatException | NullPointerException erreur) {
                    System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                    actionPerso(DJ, perso);
                }
            }
        }
        return val;
    }


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
                if (person.getClasse().equals("Clerc")) {
                    if (choix == 1) {
                        int choixPerso = 1;
                        for (Personnage perso : DJ.getListePerso()) {
                            System.out.println(choixPerso + ". " + perso.toString());
                        }
                        choixPerso = sc.nextInt();
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
                                System.out.println("\t" + choixPerso + ". " + perso.toString());
                                choixPerso++;
                            }
                            System.out.println("Choisissez un allie a soigner :");
                            choixPerso = sc.nextInt();
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
                            System.out.println("Choisissez la deuxième entité à téléporter :");
                            choixEntite2 = sc.nextInt();

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
                                    if (equipement instanceof Arme) {
                                        System.out.println("\t" + choixArme++ + "- " + equipement.getName());
                                    }
                                }
                                for (Equipement equipement : perso.getEquipees()) {
                                    if (equipement instanceof Arme) {
                                        System.out.println("\t" + choixArme++ + "- " + "(Equipée) " + equipement.getName());
                                    }
                                }
                            }
                            System.out.println("Choisissez une arme a améliorer (+1 dgt, +1 touche) :");
                            choixArme = sc.nextInt();
                            boolean ok = false;
                            int idArme = 1;
                            for (Personnage perso : DJ.getListePerso()) {
                                for (Equipement equipement : perso.getStock()) {
                                    if (equipement instanceof Arme) {
                                        if (choixArme == idArme) {
                                            ((Arme) equipement).bonusMagique();
                                            ok = true;
                                            break;
                                        }
                                        else {
                                            idArme++;
                                        }
                                    }
                                }
                                for (Equipement equipement : perso.getEquipees()) {
                                    if (equipement instanceof Arme) {
                                        if (choixArme == idArme) {
                                            ((Arme) equipement).bonusMagique();
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
                lancerSort(DJ, person);
            }

        }
        else {
            System.out.println(_cl.rouge() + "Vous n'avez pas de sort..." + _cl.reset());
        }
    }





    public StatusDonjon actionMonstre(Donjon DJ, Monstre mons)
    {
        StatusDonjon val = StatusDonjon.NORMAL;
        System.out.println("\nChoisir une action :\n1- Se déplacer\n2- Attaquer");
        try {
            int choix = sc.nextInt();

            switch (choix) {
                case 1:
                    int[] pos = _input.choixCase("où se déplacer");
                    mons.seDeplacer(DJ, pos);
                    break;
                case 2:
                    int[] posAtt = _input.choixCase("à attaquer");
                    val = mons.attaquer(DJ, posAtt);
                    break;
                default:
                    System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                    actionMonstre(DJ, mons);
            }
        }
        catch (InputMismatchException | NumberFormatException | NullPointerException erreur)
        {
            System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
            actionMonstre(DJ, mons);
        }
        return val;
    }





    public StatusDonjon actionMjFinTour(Donjon DJ, MJ mj)
    {
        StatusDonjon val = StatusDonjon.NORMAL;

        System.out.println("\n\n\nQue veut faire le Maitre du Jeu ?");
        System.out.println("1- Ne rien faire\n2- Déplacer un joueur/monstre\n3- Faire ds dégats à un joueur/monstre\n4- Ajouter des obstacles");
        try {
            int choix = sc.nextInt();
            switch (choix) {
                case 2:
                    _input.depViv(DJ, mj);
                    break;
                case 3:
                    System.out.println("A qui voulez-vous infliger des dégats ?\n(j[oueur] / m[onstre])");
                    String infDgt = sc.nextLine();
                    if (infDgt.equals("j") || infDgt.equals("joueur")) {
                        val = _input.degatPersonnage(DJ, mj);
                    } else if (infDgt.equals("m") || infDgt.equals("monstre")) {
                        val = _input.degatMonstre(DJ, mj);
                    }
                    break;
                case 4:
                    _input.ajoutObstacle(DJ, mj);
                    break;
                default:
            }
        }
        catch (InputMismatchException | NumberFormatException | NullPointerException erreur) {
            System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
            actionMjFinTour(DJ, mj);
        }
        return val;
    }

}
