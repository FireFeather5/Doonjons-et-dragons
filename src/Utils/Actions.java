package Utils;

import donjon.Donjon;
import entite.Monstre;
import entite.personnages.Personnage;
import entite.personnages.classes.Clerc;
import entite.personnages.classes.Magicien;

import java.util.Scanner;

public class Actions {

    private final Couleurs _cl = new Couleurs();
    Scanner sc = new Scanner(System.in);


    public StatusDonjon actionPerso(Donjon DJ, Personnage perso)
    {
        StatusDonjon val = StatusDonjon.NORMAL;
        //doit pouvoir etre amélioré mais fonctionne pour le moment
        if (perso.peutRam())
        {
            if (perso.getClasse().equals("Clerc") || perso.getClasse().equals("Magicien")) {
                System.out.println("\n\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1\nS'équiper : 2\nSorts : 3\nRamasser : 4");
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
                            perso.sEquiper();
                            break;
                        case 3:
                            perso.lancerSort(DJ);
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
                System.out.println("\n\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1\nS'équiper : 2\nRamasser : 3");
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
                            perso.sEquiper();
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
                System.out.println("\n\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1\nS'équiper : 2\nSorts : 3");

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
                            perso.sEquiper();
                            break;
                        case 3:
                            perso.lancerSort(DJ);
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
                System.out.println("\n\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1\nS'équiper : 2");

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
                            perso.sEquiper();
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


    public StatusDonjon actionMonstre(Donjon DJ, Monstre mons)
    {
        StatusDonjon val = StatusDonjon.NORMAL;
        System.out.println("\n\nChoisir une action :\nSe déplacer : 0\nAttaquer : 1");
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

}
