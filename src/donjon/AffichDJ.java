package donjon;

import utils.Affichage;
import entite.Entite;
import utils.SortieAffichage;

public class AffichDJ {

    private final int _tc1;
    private final int _tc2;
    private final String[][] _donjon;
    private final static String[] _ord = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    private final Affichage _affichage = new Affichage(SortieAffichage.CONSOLE);

    public AffichDJ(int tc1, int tc2)
    {
        _tc1 = tc1;
        _tc2 = tc2;
        _donjon = new String[_tc1][_tc2];
    }

    public void rempDJ(Entite[][] dj)
    {
        for (int i = 0; i < _tc1; i++)
        {
            for (int j = 0; j < _tc2; j++)
            {
                if (dj[i][j]!=null)
                {
                    _donjon[i][j] = dj[i][j].aff();
                }
                else
                {
                    _donjon[i][j] = " . ";
                }
            }
        }
    }

    public void afficherDJ(Entite[][] dj)
    {
        rempDJ(dj);

        _affichage.afficher(false, "\n    ");
        for (int k = 1; k <= _tc2; k++)
        {
            _affichage.afficherJaune(false, " ", _ord[k-1], " ");
        }
        _affichage.afficher(true);
        for (int i = 0; i < _tc1; i++)
        {
            if (i < 9) {
                _affichage.afficherJaune(false, " ", (i+1), "  ");
            }
            else
            {
                _affichage.afficherJaune(false, " ", (i+1), " ");
            }

            for (int j = 0; j < _tc2; j++)
            {
                _affichage.afficher(false, _donjon[i][j]);
            }
            _affichage.afficher(true);
        }

        _affichage.afficher(false, "                ");
        _affichage.afficher(false, " *  = equipement     ");
        _affichage.afficher(false, "[ ] = obstacle");

    }

}
