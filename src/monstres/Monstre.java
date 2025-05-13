package monstres;

import de.De;
import personnages.*;
import donjon.Donjon;

public class Monstre {
    private String _espece;
    private int _numero;    //a voir plus tard
    private int _portAtt;
    private De _degAtt;
    private int[] _stats = {0, 0, 0, 0, 0, 0};
    //                  pv, for, dex, vit, ini, arm
    //a modifier

    public Monstre()
    {

    }

    public void creaMonstre(String espece, int portAtt, De degAtt, De charac)
    {
        _espece = espece;
        _portAtt = portAtt;
        _degAtt = degAtt;

        for (int i = 0; i < 6; i++)
        {
            _stats[i] += charac.roll() + 3;
        }
        if (_portAtt == 1)
        {
            _stats[2] = 0;
        }
        else
        {
            _stats[1] = 0;
        }

        /*for (int i = 0; i < 6; i++)
        {
            System.out.println("Mo " + _stats[i]);
        }*/
    }

    public void seDeplacer(Donjon DJ)
    {
        int[] pos = DJ.getPos(this.toString());

        int lonD = _stats[4]/3;

        DJ.deplacer(this.toString(), lonD);



    }

    public void attaquer(Personnage pers, int dist)
    {
        De deAtt = new De(1, 20);
        if (_portAtt >= dist)
        {
            int atk = deAtt.roll() + _stats[1] + _stats[2];
            // un des deux est forcément à 0 donc on peut directement ajouter les deux
            // (évite un if else)
            System.out.println("atk : " + atk);
        }
        else
        {
            System.out.println("Cible trop loin");
        }
        // besoin des classes armement pour faire le reste
    }

    @Override
    public String toString() {
        return _espece + _numero;
    }

}
