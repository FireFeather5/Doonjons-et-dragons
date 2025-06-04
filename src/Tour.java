import Utils.*;
import donjon.Donjon;
import entite.Monstre;
import entite.Vivant;
import entite.personnages.Personnage;

import java.util.ArrayList;
import java.util.Scanner;

public class Tour {

    private final Couleurs _cl = new Couleurs();
    private Inputs _input = new Inputs();

    private final ArrayList<Vivant> _vivTri;
    private int _nbViv;
    private final Actions _action = new Actions();
    private final MJ _mj;
    private final Donjon _dj;

    Scanner sc = new Scanner(System.in);

    public Tour(ArrayList<Vivant> vivTri, MJ mj, Donjon dj)
    {
        _vivTri = new ArrayList<>();
        _vivTri.addAll(vivTri);
        _nbViv = _vivTri.size();
        _mj = mj;
        _dj = dj;

    }

    public void tour()
    {
        StatusDonjon val = StatusDonjon.NORMAL;
        int compTour = 1;

        while (val == StatusDonjon.NORMAL) {
            for (int j = 0; j < _nbViv; j++) {
                for (int i = 0; i < 3; i++) {
                    if (val == StatusDonjon.NORMAL) {
                        System.out.print("\n\n");
                        System.out.println(_cl.jaune() + "-------------------------------------------------------------------");
                        System.out.print("\n           Tour de " + _vivTri.get(j).getLilInfos() + "\n Tour N°" + compTour + "\n");
                        System.out.println("-------------------------------------------------------------------\n" + _cl.reset());
                        for (int k = 0; k < _nbViv; k++) {
                            if (k != j) {
                                System.out.print("           ");
                                System.out.print(_vivTri.get(k).getLilInfos());
                            } else {
                                System.out.print(_cl.bleu() + "       --> ");
                                System.out.print(_vivTri.get(k).getLilInfos() + _cl.reset());
                            }
                        }


                        _dj.afficherDJ();
                        System.out.println(_vivTri.get(j).getInfos());
                        System.out.println("\nIl vous reste " + _cl.cyan() + (3 - i) + _cl.reset() + " actions.");

                        if (_vivTri.get(j).getTypeVivant().equals(TypeVivant.PERSONNAGE)) {
                            val = _action.actionPerso(_dj, (Personnage) _vivTri.get(j));
                        }
                        else {
                            val = _action.actionMonstre(_dj, (Monstre) _vivTri.get(j));
                        }

                        if (val == StatusDonjon.MONSTRE_MORT) {
                            for (int n = 0; n < _nbViv; n++) {
                                if (_vivTri.get(n).getPV() <= 0) {
                                    _vivTri.remove(_vivTri.get(n));
                                    _nbViv--;

                                    if (n <= j) {
                                        j--;
                                    }
                                }
                            }
                            val = StatusDonjon.NORMAL;
                        }


                        System.out.println("\nVoulez-vous commenter l'action précédente ?\n(o/n/mj)");
                        String comm = sc.nextLine();
                        while (comm != "n")
                        {
                            if (comm.equals("o"))
                            {
                                _input.persoCommenteAction((Personnage) _vivTri.get(j));
                            }
                            else if (comm.equals("mj"))
                            {
                                _input.mjCommenteAction(_mj);
                            }
                            else
                            {
                                System.out.println(_cl.rouge() + "Mauvaise valeur rentrée." + _cl.reset());
                            }
                        }

                        if (val.equals(StatusDonjon.NORMAL)) {
                            _dj.afficherDJ();
                            val = _action.actionMjFinTour(_dj, _mj);
                            if (val == StatusDonjon.MONSTRE_MORT) {
                                for (int n = 0; n < _nbViv; n++) {
                                    if (_vivTri.get(n).getPV() <= 0) {
                                        _vivTri.remove(_vivTri.get(n));
                                        _nbViv--;

                                        if (n <= j) {
                                            j--;
                                        }
                                    }
                                }
                                val = StatusDonjon.NORMAL;
                            }
                        }

                    }
                }
            }
            compTour++;
        }


        if (val == StatusDonjon.JOUEUR_MORT) {
            System.out.println(_cl.rouge() + "\nLes joueurs ont perdu" + _cl.reset());
            for (Vivant vi : _vivTri)
            {
                if (vi.getTypeVivant().equals(TypeVivant.MONSTRE))
                {
                    _vivTri.remove(vi);
                }
                else
                {
                    vi.getPV();
                }
            }
        } else {
            System.out.println(_cl.vert() + "\nLes joueurs ont fini le donjon" + _cl.reset());
            for (Vivant vi : _vivTri)
            {
                vi.getPV();
            }
        }
    }

}
