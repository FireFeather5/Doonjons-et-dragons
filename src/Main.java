import personnages.*;
import personnages.classes.Clerc;
import personnages.races.Humain;

public class Main {
    public static void main(String[] args){
        System.out.println("Bienvenue dans DOOnjon et Dragons");
        De de = new De(2, 20);
        System.out.println(de);

        for (int i = 0; i < 10; i++){
            System.out.println("Lancé " + (i+1) + " de " + de + " : " + de.roll());
        }

        Personnage test = new Personnage("Pap", new Humain(), new Clerc());

    }
}