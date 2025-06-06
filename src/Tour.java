import utils.*;
import donjon.Donjon;
import entite.Monstre;
import entite.Vivant;
import entite.personnages.Personnage;

import java.util.ArrayList;
import java.util.Scanner;

public class Tour {

    private final Couleurs _cl = new Couleurs();
    private final Inputs _input = new Inputs();

    private final ArrayList<Vivant> _vivTri;
    private int _nbViv;
    private final Actions _action = new Actions();
    private final MJ _mj;
    private final Donjon _dj;

    private final Scanner sc = new Scanner(System.in);

    private final Affichage _affichage = new Affichage(SortieAffichage.CONSOLE);

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
                        _affichage.afficher(false, "\n\n");
                        _affichage.afficherJaune(true, "-------------------------------------------------------------------");
                        _affichage.afficherJaune(false, "\n           Tour de ", _vivTri.get(j).getLilInfos(), "\n Tour N°", compTour, "\n");
                        _affichage.afficherJaune(true, "-------------------------------------------------------------------\n");
                        for (int k = 0; k < _nbViv; k++) {
                            if (k != j) {
                                _affichage.afficher(false, "           ");
                                _affichage.afficher(false, _vivTri.get(k).getLilInfos());
                            } else {
                                _affichage.afficherBleu(false, "       --> ");
                                _affichage.afficherBleu(false, _vivTri.get(k).getLilInfos());
                            }
                        }


                        _dj.afficherDJ();
                        _affichage.afficher(true, _vivTri.get(j).getInfos());
                        _affichage.afficher(true, "\nIl vous reste ", _cl.cyan(3 - i), " actions.");

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

                        if (_vivTri.get(j).getTypeVivant().equals(TypeVivant.PERSONNAGE)) {
                            String comm = "o";

                            while (!comm.equals("n"))
                            {
                                _affichage.afficher(true, "\nVoulez-vous commenter l'action précédente ?\n(o/n/mj)");
                                comm = sc.nextLine();

                                if (comm.equals("o"))
                                {
                                    _input.persoCommenteAction((Personnage) _vivTri.get(j));
                                    comm = "n";
                                }
                                else if (comm.equals("mj"))
                                {
                                    _input.mjCommenteAction(_mj);
                                    comm = "n";
                                }
                                else if (!comm.equals("n"))
                                {
                                    _affichage.afficherRouge(true, "Mauvaise valeur rentrée.");
                                }
                            }
                        }
                        else
                        {
                            String comm = "o";

                            while (!comm.equals("n"))
                            {
                                _affichage.afficher(true, "\nVoulez-vous commenter l'action précédente ?\n(mj/n)");
                                comm = sc.nextLine();

                                if (comm.equals("mj"))
                                {
                                    _input.mjCommenteAction(_mj);
                                    comm = "n";
                                }
                                else if (!comm.equals("n"))
                                {
                                    _affichage.afficherRouge(true, "Mauvaise valeur rentrée.");
                                }
                            }
                        }
                        // /!\ CODE DUPLIQUE, je vois pas trop comment regler ca
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
            _affichage.afficherRouge(true, "\nLes joueurs ont perdu");
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
            _affichage.afficherVert(true, "\nLes joueurs ont fini le donjon");
            for (Vivant vi : _vivTri)
            {
                vi.getPV();
            }
        }
    }

}
