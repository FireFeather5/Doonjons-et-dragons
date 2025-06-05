import Utils.Couleurs;
import Utils.MJ;
import donjon.Donjon;
import entite.personnages.Personnage;
import entite.Vivant;
import Utils.Setup;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        Setup setup = new Setup();
        Couleurs cl = new Couleurs();
        MJ mj = new MJ();

        System.out.println(cl.rouge() + "Bienvenue dans DOOnjon et Dragons" + cl.reset());

        //                     Crea persos
        ArrayList<Personnage> Pers = new ArrayList<>(setup.setupPersonnages());

        //               TOURS
        for (int tour = 1; tour <= 3; tour++) {
            Donjon donjon  = setup.setupDonjon(Pers, tour);

            //                  Initiative
            ArrayList<Vivant> etreVivantsTrie = new ArrayList<>(setup.setupInitiative());

            Tour tr = new Tour(etreVivantsTrie, mj, donjon);
            tr.tour();
        }


        //BIEN BLOQUER LES INPUTS A o OU n QUAND DEMANDE (A FAIRE A LA FIN PSQ C'EST LONG DE TT METTRE PR LES TESTS)

        // METTRE QUE DES EQUALS AVEC LES STRINGS !!!!!! CA BUEUGE SINON


    }
}