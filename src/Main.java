import utils.Couleurs;
import utils.MJ;
import donjon.Donjon;
import entite.personnages.Personnage;
import entite.Vivant;
import utils.Setup;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        Setup setup = new Setup();
        Couleurs cl = new Couleurs();
        MJ mj = new MJ();

        System.out.println(cl.rouge() + "Bienvenue dans DOOnjon et Dragons" + cl.reset());

        //                     Crea persos
        ArrayList<Personnage> pers = new ArrayList<>(setup.setupPersonnages());

        //               TOURS
        for (int tour = 1; tour <= 3; tour++) {
            Donjon donjon  = setup.setupDonjon(pers, tour);

            //                  Initiative
            ArrayList<Vivant> etreVivantsTrie = new ArrayList<>(setup.setupInitiative());

            Tour tr = new Tour(etreVivantsTrie, pers, mj, donjon);
            tr.tour();
        }
    }


    // faire une liste monstre et un liste perso dans tour, et utiliser celles la en argument au lieu de celles de donjon ?
    // et sinon utiliser listeVivant de tour ? (genre pour le mj)


}