import donjon.Donjon;
import personnages.*;
import personnages.classes.*;
import personnages.races.*;
import monstres.*;

public class Main {
    public static void main(String[] args){
        System.out.println("Bienvenue dans DOOnjon et Dragons");

        MJ test = new MJ();
        Donjon tesssst = new Donjon();
        Personnage fesmfjeio = new Personnage("Céleste", new Humain(), new Roublard());
        Monstre pjpegfs = new Monstre();

        test.createDJ(tesssst);
        test.addObst(tesssst);
        test.createM(pjpegfs);
        test.posJ(tesssst, fesmfjeio);
        test.posM(tesssst, pjpegfs);
        tesssst.afficherDJ();

        pjpegfs.seDeplacer(tesssst);
    }
}