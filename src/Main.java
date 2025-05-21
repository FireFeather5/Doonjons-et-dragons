import donjon.Donjon;
import entite.Monstre;
import entite.equipement.Equipement;
import entite.equipement.arme.distance.ArcCourt;
import entite.equipement.arme.guerre.EpeeLongue;
import entite.equipement.armure.legere.ArmureEcaille;
import entite.personnages.Personnage;
import entite.personnages.classes.Roublard;
import entite.personnages.races.Humain;

public class Main {
    public static void main(String[] args){
        System.out.println("Bienvenue dans DOOnjon et Dragons");

        MJ test = new MJ();
        Donjon tesssst = new Donjon();
        Personnage fesmfjeio = new Personnage("Céleste", new Humain(), new Roublard());
        Monstre pjpegfs = new Monstre();
        Equipement arme = new EpeeLongue();
        Equipement armee = new ArcCourt();
        Equipement armure = new ArmureEcaille();

        test.createDJ(tesssst);

        tesssst.afficherDJ();
//
//        test.createM(pjpegfs);
//
//        tesssst.afficherDJ();
//        test.addObst(tesssst);
//        tesssst.afficherDJ();
//        test.posJ(tesssst, fesmfjeio);
//        tesssst.afficherDJ();
//        test.posM(tesssst, pjpegfs);
//        tesssst.afficherDJ();
//        test.posEquip(tesssst, arme);
//        tesssst.afficherDJ();
//        test.posEquip(tesssst, armee);
//        tesssst.afficherDJ();
//        test.posEquip(tesssst, armure);
//        tesssst.afficherDJ();
//
//        System.out.println(pjpegfs.getStat());
//        System.out.println(fesmfjeio.getStat());
//
//
//        tesssst.afficherDJ();
//
//        int val = 0;
//
//        while (val == 0) {
//            for (int i = 0; i < 3; i++)
//            {
//                if (val == 0)
//                {
//                    tesssst.afficherDJ();
//                    System.out.println(fesmfjeio.getInfos());
//                    //System.out.println(pjpegfs.getStat());
//                    val = fesmfjeio.action(tesssst);
//                }
//            }
//            for (int i = 0; i < 3; i++)
//            {
//                if (val == 0)
//                {
//                    tesssst.afficherDJ();
//                    System.out.println(pjpegfs.getStat());
//                    //System.out.println(fesmfjeio.getInfos());
//                    val = pjpegfs.action(tesssst);
//                }
//            }
//        }
//        if (val == 1)
//        {
//            System.out.println("\nLes joueurs ont perdu");
//        }
//        else
//        {
//            System.out.println("\nLes joueurs ont fini le donjon");
//            //les persos regagnent leur vie
//        }

        //          A FAIRE
        // améliorer l'affichage
        //      - au niveau des dés (voir enoncé)
        //      - à qui est le tour
        //      - légende donjon
        //      - pv restants apres attaque ?
        // trucs à ajouter
        //      - empecher que le programme s'arrête si mauvaise entrée (en cours)
        //      - créer persos, monstre et autre en mode console (sans le main)
        //      - choix ordre (initiative)
        //      - différent symboles pour monstres

    }
}