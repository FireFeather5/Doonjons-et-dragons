import de.De;
import equipement.Equipement;
import equipement.arme.distance.ArbaleteLegere;
import equipement.arme.distance.ArmeDistance;
import equipement.armure.Armure;
import equipement.armure.lourde.ArmureLourde;
import equipement.armure.lourde.Harnois;
import java.util.ArrayList;
import personnages.*;
import personnages.classes.*;
import personnages.races.*;
import monstres.*;

public class Main {
    public static void main(String[] args){
        System.out.println("Bienvenue dans DOOnjon et Dragons");

        ArrayList<Equipement> inventaire = new ArrayList<Equipement>();

        ArmureLourde harnois = new Harnois();

        harnois.getSpeedMalus();
        ArmeDistance arbalete = new ArbaleteLegere();

        inventaire.add(harnois);
        inventaire.add(arbalete);

        System.out.println(inventaire);


        Monstre m1 = new Monstre("dragon", 5, new De(2, 15), new De(3, 6));
        Personnage p1 = new Personnage("moi", new Elfes(), new Guerrier());

        m1.attaquer(p1, 3);
    }
}