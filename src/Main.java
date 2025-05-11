import de.De;
import equipement.Equipement;
import equipement.arme.distance.ArbaleteLegere;
import equipement.arme.distance.ArmeDistance;
import equipement.armure.Armure;
import equipement.armure.lourde.ArmureLourde;
import equipement.armure.lourde.Harnois;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        System.out.println("Bienvenue dans DOOnjon et Dragons");
        De de = new De(2, 20);
        System.out.println(de);

        ArrayList<Equipement> inventaire = new ArrayList<Equipement>();

        ArmureLourde harnois = new Harnois();

        harnois.getSpeedMalus();
        ArmeDistance arbalete = new ArbaleteLegere();


        inventaire.add(harnois);
        inventaire.add(arbalete);

        System.out.println(inventaire);
    }
}