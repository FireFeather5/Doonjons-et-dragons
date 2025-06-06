import utils.*;
import donjon.Donjon;
import entite.personnages.Personnage;
import entite.Vivant;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        Affichage aff = new Affichage(SortieAffichage.CONSOLE);

        Setup setup = new Setup();
        MJ mj = new MJ();

        aff.afficherRouge(true, "Bienvenue dans DOOnjon et Dragons");

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