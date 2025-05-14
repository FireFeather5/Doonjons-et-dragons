package monstres;

import de.De;
import personnages.*;

public class Monstre {
    private final String _espece;
    private int _numero;    //a voir plus tard
    private final int _portAtt;
    private final De _degAtt;
    private final int[] _stats = {0, 0, 0, 0, 0, 0};
    //                          pv, for, dex, vit, ini, arm

    public Monstre(String espece, int portAtt, De degAtt, De charac)
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

        for (int i = 0; i < 6; i++)
        {
            System.out.println("Mo " + _stats[i]);
        }
    }

    public void seDeplacer()
    {
        // besoin de la classe qui gère le donjon
    }

    public void attaquer(Personnage pers, int dist)
    {
        De deAtt = new De(1, 20);
        if (_portAtt >= dist)
        {
            int touche = deAtt.roll() + _stats[1] + _stats[2];
            int atk = this._degAtt.roll();
            // un des deux est forcément à 0 donc on peut directement ajouter les deux
            // (évite un if else)
            System.out.println("Touche : " + touche);
            System.out.println("Atk : " + atk);
            pers.seFaitAttaquer(atk);
        }
        else
        {
            System.out.println("Cible trop loin");
        }
        // besoin des classes armement pour faire le reste
    }

    public int getArmorClass() {
        return this._stats[5];
    }

    public void seFaitAttaquer(int degats) {
        this._stats[0] -= degats;
    }

    public String getStat() {
        return "pv : " + this._stats[0] + ", force : " + this._stats[1] + ", dexterite : " + this._stats[2] + ", vitesse : " + this._stats[3] + ", initiative : " + this._stats[4] + ", CA : " + this._stats[5];
    }

    @Override
    public String toString() {
        return _espece + _numero;
    }

}
