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
        Couleurs couleur = new Couleurs();
        MJ mj = new MJ();

        System.out.println(couleur.rouge() + "Bienvenue dans DOOnjon et Dragons" + couleur.reset());

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

        System.out.println("                   Les joueurs ont fini le jeu!                   ");
    }
}