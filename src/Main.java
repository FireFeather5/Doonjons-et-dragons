import de.De;
import equipement.Equipement;
import equipement.arme.courante.Baton;
import equipement.arme.distance.ArbaleteLegere;
import equipement.arme.distance.ArmeDistance;
import equipement.arme.guerre.Rapiere;
import equipement.armure.legere.DemiPlate;
import equipement.armure.lourde.ArmureLourde;
import equipement.armure.lourde.CotteMaille;
import equipement.armure.lourde.Harnois;
import java.util.ArrayList;
import personnages.*;
import personnages.classes.*;
import personnages.races.*;
import monstres.*;

public class Main {
    public static void main(String[] args){
        System.out.println("Bienvenue dans DOOnjon et Dragons");


        Monstre m1 = new Monstre();
        m1.creaMonstre("dragon", 5, new De(2, 15), new De(3, 6));
        Personnage p1 = new Personnage("moi", new Elfes(), new Guerrier());


        CotteMaille cotteMaille = new CotteMaille();
        DemiPlate demiPlate = new DemiPlate();
        Rapiere rap = new Rapiere();
        Baton bat = new Baton();

        p1.recuperer(cotteMaille);
        p1.recuperer(demiPlate);
        p1.recuperer(rap);
        p1.recuperer(bat);

        p1.sEquiper(cotteMaille);
        p1.sEquiper(rap);

        p1.sEquiper(demiPlate);
        p1.sEquiper(bat);

        System.out.println(p1.getStat());
        System.out.println(m1.getStat());

        p1.attaquer(m1, 1);

        m1.attaquer(p1, 1);

        System.out.println(p1.getStat());
        System.out.println(m1.getStat());
    }
}