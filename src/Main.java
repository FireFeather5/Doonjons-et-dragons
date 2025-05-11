import de.De;
import personnages.*;
import personnages.classes.*;
import personnages.races.*;
import monstres.*;

public class Main {
    public static void main(String[] args){
        System.out.println("Bienvenue dans DOOnjon et Dragons");

        Monstre m1 = new Monstre("dragon", 5, new De(2, 15), new De(3, 6));
        Personnage p1 = new Personnage("moi", new Elfes(), new Guerrier());

        m1.attaquer(p1, 3);
    }
}