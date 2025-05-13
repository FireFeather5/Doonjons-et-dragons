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

        ArrayList<Equipement> inventaire = new ArrayList<>();

        ArmureLourde harnois = new Harnois();

        harnois.getSpeedMalus();
        ArmeDistance arbalete = new ArbaleteLegere();

        inventaire.add(harnois);
        inventaire.add(arbalete);

        System.out.println(inventaire);


        Monstre m1 = new Monstre("dragon", 5, new De(2, 15), new De(3, 6));
        Personnage p1 = new Personnage("moi", new Elfes(), new Guerrier());

        m1.attaquer(p1, 3);


        CotteMaille cotteMaille = new CotteMaille();
        DemiPlate demiPlate = new DemiPlate();
        Rapiere rap = new Rapiere();
        Baton bat = new Baton();

        System.out.println(p1.getStat());

        p1.recuperer(cotteMaille);
        p1.recuperer(demiPlate);
        p1.recuperer(rap);
        p1.recuperer(bat);

        System.out.println(p1.getStat());

        System.out.println("Inventaire : " + p1.getStock());
        System.out.println("Equipee : " + p1.getPorte());

        p1.sEquiper(cotteMaille);
        p1.sEquiper(rap);

        System.out.println(p1.getStat());
        System.out.println("Inventaire : " + p1.getStock());
        System.out.println("Equipee : " + p1.getPorte());

        p1.sEquiper(demiPlate);
        p1.sEquiper(bat);

        System.out.println(p1.getStat());
        System.out.println("Inventaire : " + p1.getStock());
        System.out.println("Equipee : " + p1.getPorte());

        p1.attaquer(m1, 1);
    }
}