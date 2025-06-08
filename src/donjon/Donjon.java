package donjon;

import entite.Vivant;
import utils.Couleurs;
import utils.StatusDonjon;
import entite.Entite;
import entite.Obstacle;
import entite.Monstre;
import entite.equipement.Equipement;
import entite.personnages.Personnage;
import utils.TypeVivant;

import java.util.ArrayList;
import java.util.Arrays;

public class Donjon {

    private final Couleurs _cl = new Couleurs();

    private final int _tc1;
    private final int _tc2;
    private final Entite[][] _donjon;
    private final AffichDJ _affDJ;

    private final ArrayList<Equipement> _equip;
    private final ArrayList<Personnage> _pers;
    private final ArrayList<Monstre> _mons;

    public Donjon(int[] tailleCote)
    {
        _tc1 = tailleCote[0];
        _tc2 = tailleCote[1];
        _donjon = new Entite[_tc1][_tc2];
        _affDJ = new AffichDJ(_tc1, _tc2);

        _equip = new ArrayList<>();
        _pers = new ArrayList<>();
        _mons = new ArrayList<>();

        for (int i = 0; i < _tc1; i++)
        {
            for (int j = 0; j < _tc2; j++)
            {
                _donjon[i][j] = null;
            }
        }
    }

    public boolean positionObstacle(int[] pc, Obstacle obst)
    {
        if (((_tc1 >= pc[0]) && (pc[0] >= 1)) && ((_tc2 >= pc[1]) && (pc[1] >= 1))) {
            if (_donjon[pc[0] - 1][pc[1] - 1] == null) {
                obst.position(pc[0], pc[1]);            //donne sa position a l'obstacle
                _donjon[pc[0] - 1][pc[1] - 1] = obst;
                return true;
            }
        }
        return false;
    }

    public boolean positionVivant(int[] pc, Vivant etreVivant)
    {
        if (((_tc1 >= pc[0]) && (pc[0] >= 1)) && ((_tc2 >= pc[1]) && (pc[1] >= 1)))
        {
            if (etreVivant.getTypeVivant().equals(TypeVivant.MONSTRE))
            {
                if (!_mons.contains((Monstre) etreVivant))
                {
                    _mons.add((Monstre) etreVivant);
                }

                if (_donjon[pc[0] - 1][pc[1] - 1] == null)
                {
                    for (Equipement equip : _equip)
                    {
                        if (_donjon[equip.getPosition()[0]-1][equip.getPosition()[1]-1] == null)
                        {
                            _donjon[equip.getPosition()[0] - 1][equip.getPosition()[1] - 1] = equip;
                        }
                    }

                    ((Monstre) etreVivant).setPosition(pc[0], pc[1]);            //donne sa position au monstre
                    _donjon[pc[0] - 1][pc[1] - 1] = ((Monstre) etreVivant);
                    return true;
                }

                for (Equipement equip : _equip)
                {
                    if (Arrays.equals(pc, equip.getPosition()))
                    {
                        ((Monstre) etreVivant).setPosition(pc[0], pc[1]);            //donne sa position au monstre
                        _donjon[pc[0] - 1][pc[1] - 1] = ((Monstre) etreVivant);

                        return true;
                    }
                }
            }
            else {
                Personnage perso = (Personnage) etreVivant;

                if (!_pers.contains(perso))
                {
                    _pers.add(perso);
                }

                if (_donjon[pc[0] - 1][pc[1] - 1] == null)
                {
                    if (perso.peutRam())
                    {
                        Equipement equip = perso.getPeutRamEqu();
                        {
                            if (_donjon[equip.getPosition()[0]-1][equip.getPosition()[1]-1] == null) {
                                _donjon[equip.getPosition()[0] - 1][equip.getPosition()[1] - 1] = equip;
                            }
                        }
                        perso.reinilisation();
                    }

                    for (Equipement equip : _equip) {
                        if (Arrays.equals(pc, equip.getPosition())) {
                            perso.peutRamasser(equip);
                        }
                    }

                    perso.setPosition(pc[0], pc[1]);            //donne sa position au joueur
                    _donjon[pc[0] - 1][pc[1] - 1] = perso;
                    return true;

                }
                else {
                    for (Equipement equip : _equip) {
                        if (Arrays.equals(pc, equip.getPosition()))
                        {
                            perso.peutRamasser(equip);

                            perso.setPosition(pc[0], pc[1]);            //donne sa position au joueur
                            _donjon[pc[0] - 1][pc[1] - 1] = perso;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean positionEquipement(int[] pc, Equipement equip)
    {
        if (((_tc1 >= pc[0]) && (pc[0] >= 1)) && ((_tc2 >= pc[1]) && (pc[1] >= 1))) {
            if (_donjon[pc[0] - 1][pc[1] - 1] == null) {
                if (!_equip.contains(equip)) {
                    _equip.add(equip);
                }
                equip.position(pc[0], pc[1]);            //donne sa position a l'equipement
                _donjon[pc[0] - 1][pc[1] - 1] = equip;
                return true;
            }
        }
        return false;
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

    public void ramasserEquipement(Equipement equip)
    {
        _equip.remove(equip);
    }

    public StatusDonjon tuerMonstre(Monstre mons)
    {
        _mons.remove(mons);
        int[] pc = mons.getPos();
        emptyCase(pc);
        if (_mons.isEmpty())
        {
            return StatusDonjon.AUCUN_MONSTRE;
        }
        return StatusDonjon.MONSTRE_MORT;
    }

    public StatusDonjon tuerPerso(Personnage pers)
    {
        _pers.remove(pers);
        int[] pc = pers.getPos();
        emptyCase(pc);
        return StatusDonjon.JOUEUR_MORT;
    }


    public void switchCase(Vivant viv1, Vivant viv2)
    {
        int[] posViv1 = viv1.getPos();
        int[] posViv2 = viv2.getPos();

        emptyCase(posViv1);
        emptyCase(posViv2);

        positionVivant(posViv2, viv1);
        positionVivant(posViv1, viv2);

        System.out.println("L'échange à fonctionné");
    }


    public void emptyCase(int[] pc)
    {
        boolean test = false;
        for (Equipement equip : _equip)
        {
            if (Arrays.equals(equip.getPosition(), pc))
            {
                _donjon[pc[0]-1][pc[1]-1] = equip;
                test = true;

            }
        }
        if (!test)
        {
            _donjon[pc[0]-1][pc[1]-1] = null;
        }
    }

    public ArrayList<Personnage> getListePerso()
    {
        return new ArrayList<>(_pers);
    }

    public ArrayList<Monstre> getListeMonstre()
    {
        return new ArrayList<>(_mons);
    }

    public void afficherDJ()
    {
        _affDJ.afficherDJ(_donjon);
    }
}
