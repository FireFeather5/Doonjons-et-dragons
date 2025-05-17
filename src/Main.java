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

        test.createM(pjpegfs);

        tesssst.afficherDJ();
        test.addObst(tesssst);
        tesssst.afficherDJ();
        test.posJ(tesssst, fesmfjeio);
        tesssst.afficherDJ();
        test.posM(tesssst, pjpegfs);
        tesssst.afficherDJ();
        test.posEquip(tesssst, arme);
        tesssst.afficherDJ();
        test.posEquip(tesssst, armee);
        tesssst.afficherDJ();
        test.posEquip(tesssst, armure);
        tesssst.afficherDJ();

        System.out.println(pjpegfs.getStat());
        System.out.println(fesmfjeio.getStat());

        //pjpegfs.action(tesssst);
        //System.out.println(pjpegfs.getStat());

        tesssst.afficherDJ();

        int val = 0;

        while (val == 0) {
            for (int i = 0; i < 3; i++)
            {
                if (val == 0)
                {
                    tesssst.afficherDJ();
                    System.out.println(fesmfjeio.getInfos());
                    System.out.println(pjpegfs.getStat());
                    val = fesmfjeio.action(tesssst);
                }
            }
            for (int i = 0; i < 3; i++)
            {
                if (val == 0)
                {
                    tesssst.afficherDJ();
                    System.out.println(pjpegfs.getStat());
                    System.out.println(fesmfjeio.getInfos());
                    val = pjpegfs.action(tesssst);
                }
            }
        }
        if (val == 1)
        {
            System.out.println("\nLes joueurs ont perdu");
        }
        else
        {
            System.out.println("\nLes joueurs ont fini le donjon");
        }



        /*ArrayList<Equipement> inventaire = new ArrayList<>();

        ArmureLourde harnois = new Harnois();

        harnois.getSpeedMalus();
        ArmeDistance arbalete = new ArbaleteLegere();

        inventaire.add(harnois);
        inventaire.add(arbalete);

        System.out.println(inventaire);


        Personnage p1 = new Personnage("moi", new Elfes(), new Guerrier());

        pjpegfs.action(tesssst);
        fesmfjeio.action(tesssst);

        tesssst.afficherDJ();



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
        System.out.println("Equipee : " + p1.getPorte());*/
    }
}