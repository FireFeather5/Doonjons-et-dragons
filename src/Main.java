import donjon.Donjon;
import entite.Monstre;
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
        Monstre jpeg = new Monstre();

        test.createDJ(tesssst);

        tesssst.afficherDJ();

        test.createM(pjpegfs);
        test.createM(jpeg);
        test.posJ(tesssst, fesmfjeio);
        test.posM(tesssst, pjpegfs);
        test.posM(tesssst, jpeg);

        tesssst.afficherDJ();

        System.out.println("Nom du monstre 1 : " + pjpegfs);
        System.out.println("Nom du monstre 2 : " + jpeg);




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