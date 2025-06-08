package donjon;

import utils.Couleurs;
import entite.Entite;

public class AffichDJ {
    private final Couleurs _couleur = new Couleurs();

    private final int _tailleCote1;
    private final int _tailleCote2;
    private final String[][] _donjon;
    private final static String[] _tableauLettres = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public AffichDJ(int tc1, int tc2)
    {
        _tailleCote1 = tc1;
        _tailleCote2 = tc2;
        _donjon = new String[_tailleCote1][_tailleCote2];
    }

    public void remplirDJ(Entite[][] dj)
    {
        for (int i = 0; i < _tailleCote1; i++)
        {
            for (int j = 0; j < _tailleCote2; j++)
            {
                if (dj[i][j]!=null)
                {
                    _donjon[i][j] = dj[i][j].affichage();
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
        remplirDJ(dj);

        System.out.print("\n    ");
        for (int k = 1; k <= _tailleCote2; k++)
        {
            System.out.print(" " + _couleur.jaune() + _tableauLettres[k-1] + _couleur.reset() + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < _tailleCote1; i++)
        {
            if (i < 9) {
                System.out.print(" " + _couleur.jaune() + (i+1) + _couleur.reset() + "  ");
            }
            else
            {
                System.out.print(" " + _couleur.jaune() + (i+1) + _couleur.reset() + " ");
            }

            for (int j = 0; j < _tailleCote2; j++)
            {
                System.out.print(_donjon[i][j]);
            }
            System.out.print("\n");
        }

        System.out.print("                ");
        System.out.print(" *  = equipement     ");
        System.out.print("[ ] = obstacle");

    }

}
