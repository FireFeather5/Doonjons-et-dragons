package donjon;

import Utils.Couleurs;
import entite.Entite;
import entite.Obstacle;
import entite.Monstre;
import entite.equipement.Equipement;
import entite.personnages.Personnage;

import java.util.ArrayList;

public class Donjon {

    private Couleurs _cl = new Couleurs();

    private int _tc1;
    private int _tc2;
    private final static String[] _ord = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private Entite[][] _donjon;
    private AffichDJ _affDJ;
    private final ArrayList<Equipement> _equip;
    private final ArrayList<Personnage> _pers;
    private final ArrayList<Monstre> _mons;

    public Donjon()
    {
        _equip = new ArrayList<>();
        _pers = new ArrayList<>();
        _mons = new ArrayList<>();
    }

    public void creaDonjon(int tc1, int tc2)
    {
        _tc1 = tc1;
        _tc2 = tc2;
        _donjon = new Entite[_tc1][_tc2];
        _affDJ = new AffichDJ(_tc1, _tc2);

        for (int i = 0; i < _tc1; i++)
        {
            for (int j = 0; j < _tc2; j++)
            {
                _donjon[i][j] = null;
            }
        }
    }

    public int[] posInt(String pos)
    {
        try {
            String pos1 = pos.substring(0, 1);
            String pos2 = pos.substring(1);

            int[] posi = new int[2];

            for (int i = 0; i < 26; i++) {
                if (pos1.equals(_ord[i])) {
                    posi[1] = i + 1;
                }
            }
            posi[0] = Integer.parseInt(pos2);

            //System.out.println(posi[1] + "    " + posi[0]);

            return posi;
        }
        catch (NullPointerException | StringIndexOutOfBoundsException | NumberFormatException erreur)
        {
            System.out.println(_cl.rouge() + "Les cases sont dans le format suivant : " + _cl.cyan() + "[lettre][nombre]" + _cl.reset());
            return null;
        }
    }

    public boolean addObst(String pos, Obstacle obst)
    {
        int[] pc = posInt(pos);

        try {
            if (((_tc1 >= pc[0]) && (pc[0] >= 1)) && ((_tc2 >= pc[1]) && (pc[1] >= 1))) {
                if (_donjon[pc[0] - 1][pc[1] - 1] == null) {
                    obst.position(pc[0], pc[1]);            //donne sa position a l'obstacle
                    _donjon[pc[0] - 1][pc[1] - 1] = obst;
                    return true;
                }
            }
            return false;
        }
        catch (NullPointerException erreur)
        {
            return false;
        }
    }

    public boolean posJ(String pos, Personnage perso)
    {
        int[] pc = posInt(pos);

        try {
            if (((_tc1 >= pc[0]) && (pc[0] >= 1)) && ((_tc2 >= pc[1]) && (pc[1] >= 1))) {
                if (_donjon[pc[0] - 1][pc[1] - 1] == null) {
                    if (!_pers.contains(perso)) {
                        _pers.add(perso);
                    }
                    perso.position(pc[0], pc[1]);            //donne sa position au joueur
                    _donjon[pc[0] - 1][pc[1] - 1] = perso;
                    return true;
                }
                for (Equipement var : _equip) {
                    if (_donjon[pc[0] - 1][pc[1] - 1].equals(var)) {
                        if (!_pers.contains(perso)) {
                            _pers.add(perso);
                        }
                        perso.peutRamasser(var);
                        perso.position(pc[0], pc[1]);            //donne sa position au joueur
                        _donjon[pc[0] - 1][pc[1] - 1] = perso;
                        return true;
                    }
                }
            }
            return false;
        }
        catch (NullPointerException erreur)
        {
            return false;
        }

    }

    public boolean posM(String pos, Monstre mons)
    {
        int[] pc = posInt(pos);

        try {
            if (((_tc1 >= pc[0]) && (pc[0] >= 1)) && ((_tc2 >= pc[1]) && (pc[1] >= 1))) {
                if (_donjon[pc[0] - 1][pc[1] - 1] == null) {
                    if (!_mons.contains(mons)) {
                        _mons.add(mons);
                    }
                    mons.position(pc[0], pc[1]);            //donne sa position au monstre
                    _donjon[pc[0] - 1][pc[1] - 1] = mons;
                    return true;
                }
            }
            return false;
        }
        catch (NullPointerException erreur)
        {
            return false;
        }
    }

    public boolean posE(String pos, Equipement equip)
    {
        int[] pc = posInt(pos);

        try {
            if (((_tc1 >= pc[0]) && (pc[0] >= 1)) && ((_tc2 >= pc[1]) && (pc[1] >= 1)))
            {
                if (_donjon[pc[0] - 1][pc[1] - 1] == null)
                {
                    _equip.add(equip);
                    equip.position(pc[0], pc[1]);            //donne sa position a l'equipement
                    _donjon[pc[0] - 1][pc[1] - 1] = equip;
                    return true;
                }
            }
            return false;
        }
        catch (NullPointerException erreur)
        {
            return false;
        }
    }

    public boolean posE(int[] pc, Equipement equip)
    {
        try {
            if (((_tc1 >= pc[0]) && (pc[0] >= 1)) && ((_tc2 >= pc[1]) && (pc[1] >= 1))) {
                if (_donjon[pc[0] - 1][pc[1] - 1] == null) {
                    equip.position(pc[0], pc[1]);            //donne sa position a l'equipement
                    _donjon[pc[0] - 1][pc[1] - 1] = equip;
                    return true;
                }
            }
            return false;
        }
        catch (NullPointerException erreur)
        {
            return false;
        }
    }

    public Monstre getMons(int[] pc)
    {
        if (_donjon[pc[0]-1][pc[1]-1] != null)
        {
            for(Monstre mons : _mons)
            {
                if (_donjon[pc[0]-1][pc[1]-1].equals(mons))
                {
                    return mons;
                }
            }
        }
        return null;
    }

    public Personnage getPers(int[] pc)
    {
        if (_donjon[pc[0]-1][pc[1]-1] != null)
        {
            for(Personnage pers : _pers)
            {
                if (_donjon[pc[0]-1][pc[1]-1].equals(pers))
                {
                    return pers;
                }
            }
        }
        return null;
    }

    public void ramasser(Equipement equip)
    {
        _equip.remove(equip);
    }

    public int tuerMonstre(Monstre mons)
    {
        _mons.remove(mons);
        int[] pc = mons.getPos();
        emptyCase(pc);
        if (_mons.isEmpty())
        {
            return 2;
        }
        return 3;
    }

    public int tuerPerso(Personnage pers)
    {
        _pers.remove(pers);
        int[] pc = pers.getPos();
        emptyCase(pc);
        return 1;
    }

    public void switchCase(String posDep, String posFin)
    {
        int[] pcD = posInt(posDep);
        int[] pcF = posInt(posFin);

        boolean caseVal = false;
        for (Personnage per : _pers)
        {
            if (per.equals(_donjon[pcD[0]-1][pcD[1]-1]))
            {
                caseVal = true;
                break;
            }
        }
        for (Monstre mos : _mons)
        {
            if (mos.equals(_donjon[pcD[0]-1][pcD[1]-1]))
            {
                caseVal = true;
                break;
            }
        }

        if (caseVal)
        {
            if (_donjon[pcF[0] - 1][pcF[1] - 1] == null)
            {
                _donjon[pcF[0] - 1][pcF[1] - 1] = _donjon[pcD[0]-1][pcD[1]-1];
                System.out.println("Le déplacement à fonctionné");
                emptyCase(pcD);
            }
            else
            {
                System.out.println(_cl.rouge() + "La case d'arrivée n'est pas vide" + _cl.reset());
            }
        }
        else
        {
            System.out.println(_cl.rouge() + "Il n'y a ni personnage ni monstre sur la case départ" + _cl.reset());
        }
    }

    public void emptyCase(int[] pc)
    {
        _donjon[pc[0]-1][pc[1]-1] = null;
    }

    public void afficherDJ()
    {
        _affDJ.afficherDJ(_donjon);
    }

    public ArrayList<Personnage> getListePerso() {
        return new ArrayList<>(_pers);
    }
    public ArrayList<Monstre> getListeMonstre() {
        return new ArrayList<>(_mons);
    }
}
