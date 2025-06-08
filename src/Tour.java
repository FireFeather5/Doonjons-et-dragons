import entite.Monstre;
import utils.*;
import donjon.Donjon;
import entite.Vivant;
import entite.personnages.Personnage;

import java.util.ArrayList;
import java.util.Scanner;

public class Tour {

    private final Couleurs _couleur = new Couleurs();
    private final Inputs _input = new Inputs();

    private final ArrayList<Vivant> _listeVivant;
    private final ArrayList<Personnage> _listePersonnage;
    private final ArrayList<Monstre> _listeMonstre;
    private int _nombreVivants;
    private StatusDonjon _statusDonj;

    private final Actions _action = new Actions();
    private final MJ _mj;
    private final Donjon _donjon;

    private final Scanner sc = new Scanner(System.in);

    public Tour(ArrayList<Vivant> listeVivant, ArrayList<Personnage> listePers, MJ mj, Donjon dj)
    {
        _statusDonj = StatusDonjon.NORMAL;

        _listeVivant = new ArrayList<>();
        _listeVivant.addAll(listeVivant);

        _listePersonnage = new ArrayList<>();
        _listePersonnage.addAll(listePers);

        _listeMonstre = new ArrayList<>();
        _listeMonstre.addAll(dj.getListeMonstre());

        _nombreVivants = _listeVivant.size();
        _mj = mj;
        _donjon = dj;

    }

    public void tour()
    {
        int compteurTour = 1;

        while (_statusDonj.equals(StatusDonjon.NORMAL)) {
            for (int vivant = 0; vivant < _nombreVivants; vivant++) {
                for (int i = 0; i < 3; i++) {
                    if (_statusDonj.equals(StatusDonjon.NORMAL)) {
                        System.out.print("\n\n");
                        System.out.println(_couleur.jaune() + "-------------------------------------------------------------------");
                        System.out.print("\n           Tour de " + _listeVivant.get(vivant).getPetitesInfos() + "\n Tour N°" + compteurTour + "\n");
                        System.out.println("-------------------------------------------------------------------\n" + _couleur.reset());
                        for (int k = 0; k < _nombreVivants; k++) {
                            if (k != vivant) {
                                System.out.print("           ");
                                System.out.print(_listeVivant.get(k).getPetitesInfos());
                            } else {
                                System.out.print(_couleur.bleu() + "       --> ");
                                System.out.print(_listeVivant.get(k).getPetitesInfos() + _couleur.reset());
                            }
                        }


                        _donjon.afficherDJ();
                        System.out.println(_listeVivant.get(vivant).getInfos());
                        System.out.println("\nIl vous reste " + _couleur.cyan() + (3 - i) + _couleur.reset() + " actions.");


                        _statusDonj = _action.actionVivant(_donjon, _listeVivant.get(vivant), _listeMonstre, _listePersonnage, _listeVivant);

                        if (_statusDonj.equals(StatusDonjon.MONSTRE_MORT)) {


                            for (int n = 0; n < _nombreVivants; n++) {
                                if (_listeVivant.get(n).getPV() <= 0) {
                                    _listeVivant.remove(_listeVivant.get(n));
                                    _nombreVivants--;

                                    if (n == vivant)
                                    {
                                        i = -1;
                                    }
                                    if (n < vivant) {
                                        vivant--;
                                    }
                                }
                            }
                            for (int n = 0; n < _listeMonstre.size(); n++) {
                                if (_listeMonstre.get(n).getPV() <= 0) {
                                    _listeMonstre.remove(_listeMonstre.get(n));
                                }
                            }
                            _statusDonj = StatusDonjon.NORMAL;

                        }

                        if (_listeVivant.get(vivant).getTypeVivant().equals(TypeVivant.PERSONNAGE)) {
                            String comm = "o";

                            while (!comm.equals("n"))
                            {
                                System.out.println("\nVoulez-vous commenter l'action précédente ?\n(o/n/mj)");
                                comm = sc.nextLine();

                                if (comm.equals("o"))
                                {
                                    _input.persoCommenteAction((Personnage) _listeVivant.get(vivant));
                                    comm = "n";
                                }
                                else if (comm.equals("mj"))
                                {
                                    _input.mjCommenteAction(_mj);
                                    comm = "n";
                                }
                                else if (!comm.equals("n"))
                                {
                                    System.out.println(_couleur.rouge() + "Mauvaise valeur rentrée." + _couleur.reset());
                                }
                            }
                        }
                        else
                        {
                            String comm = "o";

                            while (!comm.equals("n"))
                            {
                                System.out.println("\nVoulez-vous commenter l'action précédente ?\n(mj/n)");
                                comm = sc.nextLine();

                                if (comm.equals("mj"))
                                {
                                    _input.mjCommenteAction(_mj);
                                    comm = "n";
                                }
                                else if (!comm.equals("n"))
                                {
                                    System.out.println(_couleur.rouge() + "Mauvaise valeur rentrée." + _couleur.reset());
                                }
                            }
                        }

                        if (_statusDonj.equals(StatusDonjon.NORMAL))
                        {
                            _donjon.afficherDJ();
                            _statusDonj = _action.actionMjFinTour(_donjon, _mj, _listeVivant);
                            if (_statusDonj.equals(StatusDonjon.MONSTRE_MORT))
                            {
                                for (int n = 0; n < _nombreVivants; n++)
                                {
                                    if (_listeVivant.get(n).getPV() <= 0)
                                    {
                                        _listeVivant.remove(_listeVivant.get(n));
                                        _nombreVivants--;

                                        if (n == vivant)
                                        {
                                            i = -1;
                                        }
                                        if (n < vivant)
                                        {
                                            vivant--;
                                        }
                                    }
                                }
                                for (int n = 0; n < _listeMonstre.size(); n++)
                                {
                                    if (_listeMonstre.get(n).getPV() <= 0)
                                    {
                                        _listeMonstre.remove(_listeMonstre.get(n));
                                    }
                                }
                                _statusDonj = StatusDonjon.NORMAL;

                            }
                        }

                    }
                }
            }
            compteurTour++;
        }
        finDonjon();
    }


    public void finDonjon()
    {
        if (_statusDonj == StatusDonjon.JOUEUR_MORT)
        {
            System.out.println(_couleur.rouge() + "\nLes joueurs ont perdu" + _couleur.reset());
            _listeVivant.clear();
            System.exit(0);
        }
        else
        {
            System.out.println(_couleur.vert() + "\nLes joueurs ont fini le donjon" + _couleur.reset());
            _listeVivant.clear();
        }
    }


}
