import Utils.Couleurs;
import donjon.Donjon;
import entite.personnages.Vivant;

import java.util.ArrayList;
import java.util.Scanner;

public class Tour {
    private ArrayList<Vivant> _vivTri;
    private int _nbViv;
    private MJ _mj;
    private Donjon _dj;
    private Couleurs _cl = new Couleurs();

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
        int val = 0;
        int compTour = 1;

        while (val == 0) {
            for (int j = 0; j < _nbViv; j++) {
                for (int i = 0; i < 3; i++) {
                    if (val == 0) {
                        System.out.print("\n\n");
                        System.out.println("---------------------------------------------------------");
                        System.out.print("\n           Tour de " + _vivTri.get(j).getLilInfos() + "\n Tour N°" + compTour + "\n");
                        System.out.println("---------------------------------------------------------\n");
                        for (int k = 0; k < _nbViv; k++) {
                            if (k != j) {
                                System.out.print("           ");
                                System.out.print(_vivTri.get(k).getLilInfos());
                            } else {
                                System.out.print(_cl.rouge() + "       --> " + _cl.reset());
                                System.out.print(_cl.rouge() + _vivTri.get(k).getLilInfos() + _cl.reset());
                            }
                        }


                        _dj.afficherDJ();
                        System.out.println(_vivTri.get(j).getInfos());
                        System.out.println("\nIl vous reste " + _cl.cyan() + (3 - i) + _cl.reset() + " actions.");
                        val = _vivTri.get(j).action(_dj);

                        if (val == 3) {
                            for (int n = 0; n < _nbViv; n++) {
                                if (_vivTri.get(n).getPV() <= 0) {
                                    _vivTri.remove(_vivTri.get(n));
                                    _nbViv--;

                                    if (n <= j) {
                                        j--;
                                    }
                                }
                            }
                            val = 0;
                        }


                        System.out.println("\nVoulez-vous commenter l'action précédente ?\n(o/n/mj)");
                        String comm = sc.nextLine();
                        if (comm.equals("o"))
                        {
                            System.out.println(_vivTri.get(j).comAction());
                        }
                        else if (comm.equals("mj"))
                        {
                            System.out.println(_mj.comAction());
                        }


                        _mj.actionFT(_dj);


                    }
                }
            }
            compTour++;
        }


        if (val == 1) {
            System.out.println(_cl.vert() + "\nLes joueurs ont perdu" + _cl.reset());
        } else {
            System.out.println(_cl.rouge() + "\nLes joueurs ont fini le donjon" + _cl.reset());
            for (Vivant vi : _vivTri)
            {
                vi.getPV();
            }
            //les persos regagnent leur vie
        }
    }

}
