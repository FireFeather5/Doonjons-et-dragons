import entite.Monstre;
import utils.*;
import donjon.Donjon;
import entite.Vivant;
import entite.personnages.Personnage;

import java.util.ArrayList;
import java.util.Scanner;

public class Tour {

    private final Couleurs _cl = new Couleurs();
    private final Inputs _input = new Inputs();

    private final ArrayList<Vivant> _listeVivant;
    private final ArrayList<Personnage> _listePersonnage;
    private final ArrayList<Monstre> _listeMonstre;
    private int _nbViv;
    private StatusDonjon _val;

    private final Actions _action = new Actions();
    private final MJ _mj;
    private final Donjon _dj;

    private final Scanner sc = new Scanner(System.in);

    public Tour(ArrayList<Vivant> vivTri, ArrayList<Personnage> listePers, MJ mj, Donjon dj)
    {
        _val = StatusDonjon.NORMAL;

        _listeVivant = new ArrayList<>();
        _listeVivant.addAll(vivTri);

        _listePersonnage = new ArrayList<>();
        _listePersonnage.addAll(listePers);

        _listeMonstre = new ArrayList<>();
        _listeMonstre.addAll(dj.getListeMonstre());

        _nbViv = _listeVivant.size();
        _mj = mj;
        _dj = dj;

    }

    public void tour()
    {
        int compTour = 1;

        while (_val.equals(StatusDonjon.NORMAL)) {
            for (int j = 0; j < _nbViv; j++) {
                for (int i = 0; i < 3; i++) {
                    if (_val.equals(StatusDonjon.NORMAL)) {
                        System.out.print("\n\n");
                        System.out.println(_cl.jaune() + "-------------------------------------------------------------------");
                        System.out.print("\n           Tour de " + _listeVivant.get(j).getLilInfos() + "\n Tour N°" + compTour + "\n");
                        System.out.println("-------------------------------------------------------------------\n" + _cl.reset());
                        for (int k = 0; k < _nbViv; k++) {
                            if (k != j) {
                                System.out.print("           ");
                                System.out.print(_listeVivant.get(k).getLilInfos());
                            } else {
                                System.out.print(_cl.bleu() + "       --> ");
                                System.out.print(_listeVivant.get(k).getLilInfos() + _cl.reset());
                            }
                        }


                        _dj.afficherDJ();
                        System.out.println(_listeVivant.get(j).getInfos());
                        System.out.println("\nIl vous reste " + _cl.cyan() + (3 - i) + _cl.reset() + " actions.");

                        /*if (_vivTri.get(j).getTypeVivant().equals(TypeVivant.PERSONNAGE)) {
                            _val = _action.actionPerso(_dj, (Personnage) _vivTri.get(j));
                        }
                        else {
                            _val = _action.actionMonstre(_dj, (Monstre) _vivTri.get(j));
                        }*/

                        _val = _action.actionVivant(_dj, _listeVivant.get(j), _listeMonstre, _listePersonnage, _listeVivant);

                        if (_val.equals(StatusDonjon.MONSTRE_MORT)) {


                            for (int n = 0; n < _nbViv; n++) {
                                if (_listeVivant.get(n).getPV() <= 0) {
                                    _listeVivant.remove(_listeVivant.get(n));
                                    _nbViv--;

                                    if (n == j)
                                    {
                                        i = -1;
                                    }
                                    if (n < j) {
                                        j--;
                                    }
                                }
                            }
                            for (int n = 0; n < _listeMonstre.size(); n++) {
                                if (_listeMonstre.get(n).getPV() <= 0) {
                                    _listeMonstre.remove(_listeMonstre.get(n));
                                }
                            }
                            _val = StatusDonjon.NORMAL;

                        }

                        if (_listeVivant.get(j).getTypeVivant().equals(TypeVivant.PERSONNAGE)) {
                            String comm = "o";

                            while (!comm.equals("n"))
                            {
                                System.out.println("\nVoulez-vous commenter l'action précédente ?\n(o/n/mj)");
                                comm = sc.nextLine();

                                if (comm.equals("o"))
                                {
                                    _input.persoCommenteAction((Personnage) _listeVivant.get(j));
                                    comm = "n";
                                }
                                else if (comm.equals("mj"))
                                {
                                    _input.mjCommenteAction(_mj);
                                    comm = "n";
                                }
                                else if (!comm.equals("n"))
                                {
                                    System.out.println(_cl.rouge() + "Mauvaise valeur rentrée." + _cl.reset());
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
                                    System.out.println(_cl.rouge() + "Mauvaise valeur rentrée." + _cl.reset());
                                }
                            }
                        }

                        if (_val.equals(StatusDonjon.NORMAL)) {
                            _dj.afficherDJ();
                            _val = _action.actionMjFinTour(_dj, _mj, _listeVivant);
                            if (_val.equals(StatusDonjon.MONSTRE_MORT)) {


                                for (int n = 0; n < _nbViv; n++) {
                                    if (_listeVivant.get(n).getPV() <= 0) {
                                        _listeVivant.remove(_listeVivant.get(n));
                                        _nbViv--;

                                        if (n == j)
                                        {
                                            i = -1;
                                        }
                                        if (n < j) {
                                            j--;
                                        }
                                    }
                                }
                                for (int n = 0; n < _listeMonstre.size(); n++) {
                                    if (_listeMonstre.get(n).getPV() <= 0) {
                                        _listeMonstre.remove(_listeMonstre.get(n));
                                    }
                                }
                                _val = StatusDonjon.NORMAL;

                            }
                        }

                    }
                }
            }
            compTour++;
        }

        finDonjon();

    }


    public int monstreMort(int vivant)
    {
        for (int n = 0; n < _nbViv; n++) {
            if (_listeVivant.get(n).getPV() <= 0) {
                _listeVivant.remove(_listeVivant.get(n));
                _nbViv--;

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
        return vivant;
    }


    public void finDonjon()
    {
        if (_val == StatusDonjon.JOUEUR_MORT) {
            System.out.println(_cl.rouge() + "\nLes joueurs ont perdu" + _cl.reset());
            _listeVivant.clear();
            System.exit(0);
        } else {
            System.out.println(_cl.vert() + "\nLes joueurs ont fini le donjon" + _cl.reset());
            _listeVivant.clear();
        }
    }


}
