package Utils;

import donjon.Donjon;
import entite.Monstre;
import entite.equipement.Equipement;
import entite.equipement.arme.Arme;
import entite.personnages.Personnage;
import entite.personnages.classes.Clerc;
import entite.personnages.classes.Magicien;
import sort.BoogieWoogie;
import sort.Guerison;
import sort.Sort;

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
        //doit pouvoir etre amélioré mais fonctionne pour le moment
        if (perso.peutRam())
        {
            if (perso.getClasse().equals("Clerc") || perso.getClasse().equals("Magicien")) {
                System.out.println("\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1\nS'équiper : 2\nSorts : 3\nRamasser : 4");
                try {
                    int choix = Integer.parseInt(sc.nextLine());

                    switch (choix) {
                        case 0:
                            perso.seDeplacer(DJ);
                            break;
                        case 1:
                            val = perso.attaquer(DJ);
                            break;
                        case 2:
                            Equipement equip = _input.equiperEquip(perso);
                            perso.sEquiper(equip);
                            break;
                        case 3:
                            lancerSort(DJ, perso);
                            break;
                        case 4:
                            perso.ramasser(DJ);
                            break;
                        default:
                            System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                            actionPerso(DJ, perso);
                    }
                } catch (NumberFormatException | NullPointerException erreur) {
                    System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                    actionPerso(DJ, perso);
                }
            }
            else {
                System.out.println("\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1\nS'équiper : 2\nRamasser : 3");
                try {
                    int choix = Integer.parseInt(sc.nextLine());

                    switch (choix) {
                        case 0:
                            perso.seDeplacer(DJ);
                            break;
                        case 1:
                            val = perso.attaquer(DJ);
                            break;
                        case 2:
                            Equipement equip = _input.equiperEquip(perso);
                            perso.sEquiper(equip);
                            break;
                        case 3:
                            perso.ramasser(DJ);
                            break;
                        default:
                            System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                            actionPerso(DJ, perso);
                    }
                } catch (NumberFormatException | NullPointerException erreur) {
                    System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                    actionPerso(DJ, perso);
                }
            }
        }
        else
        {
            if (perso.getClasse().equals("Clerc") || perso.getClasse().equals("Magicien")) {
                System.out.println("\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1\nS'équiper : 2\nSorts : 3");

                try {
                    int choix = Integer.parseInt(sc.nextLine());
                    switch (choix) {
                        case 0:
                            perso.seDeplacer(DJ);
                            break;
                        case 1:
                            val = perso.attaquer(DJ);
                            break;
                        case 2:
                            Equipement equip = _input.equiperEquip(perso);
                            perso.sEquiper(equip);
                            break;
                        case 3:
                            lancerSort(DJ, perso);
                            break;
                        default:
                            System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                            actionPerso(DJ, perso);
                    }
                } catch (NumberFormatException | NullPointerException erreur) {
                    System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                    actionPerso(DJ, perso);
                }
            }
            else {
                System.out.println("\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1\nS'équiper : 2");

                try {
                    int choix = Integer.parseInt(sc.nextLine());
                    switch (choix) {
                        case 0:
                            perso.seDeplacer(DJ);
                            break;
                        case 1:
                            val = perso.attaquer(DJ);
                            break;
                        case 2:
                            Equipement equip = _input.equiperEquip(perso);
                            perso.sEquiper(equip);
                            break;
                        default:
                            System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                            actionPerso(DJ, perso);
                    }
                } catch (NumberFormatException | NullPointerException erreur) {
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
                System.out.println(choix++ + ". " + sort.getNom() + " : " + sort.getDescription());
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
                                        System.out.println("\t" + choixArme++ + ". " + equipement.getName());
                                    }
                                }
                                for (Equipement equipement : perso.getEquipees()) {
                                    if (equipement instanceof Arme) {
                                        System.out.println("\t" + choixArme++ + ". " + "(Equipée) " + equipement.getName());
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
        System.out.println("\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1");
        try {
            int choix = Integer.parseInt(sc.nextLine());

            switch (choix) {
                case 0:
                    mons.seDeplacer(DJ);
                    break;
                case 1:
                    val = mons.attaquer(DJ);
                    break;
                default:
                    System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
                    actionMonstre(DJ, mons);
            }
        }
        catch (NumberFormatException | NullPointerException erreur)
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
        System.out.println("0- Ne rien faire\n1- Déplacer un joueur/monstre\n2- Faire ds dégats à un joueur/monstre\n3- Ajouter des obstacles");
        try {
            int choix = Integer.parseInt(sc.nextLine());
            switch (choix) {
                case 1:
                    mj.depViv(DJ);
                    break;
                case 2:
                    System.out.println("A qui voulez-vous infliger des dégats ?\n(j[oueur] / m[onstre])");
                    String infDgt = sc.nextLine();
                    if (infDgt.equals("j") || infDgt.equals("joueur")) {
                        val = mj.degatJoueur(DJ);
                    } else if (infDgt.equals("m") || infDgt.equals("monstre")) {
                        val = mj.degatMonstre(DJ);
                    }
                    break;
                case 3:
                    String ch = "o";
                    while (ch.equals("o")) {
                        mj.addObst(DJ);
                        System.out.println("Voulez-vous ajouter un autre obstacle ? (o/n)");
                        ch = sc.nextLine();
                    }
                    break;
                default:
            }
        }
        catch (NumberFormatException | NullPointerException erreur) {
            System.out.println(_cl.rouge() + "Mauvais choix d'action" + _cl.reset());
            actionMjFinTour(DJ, mj);
        }
        return val;
    }

}
