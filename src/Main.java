import equipement.armure.Harnois;

public class Main {
    public static void main(String[] args){
        System.out.println("Bienvenue dans DOOnjon et Dragons");
        De de = new De(2, 20);
        System.out.println(de);

        for (int i = 0; i < 10; i++){
            System.out.println("Lancé " + (i+1) + " de " + de + " : " + de.roll());
        }

        Harnois harnois = new Harnois();
        System.out.println(harnois.get_speed_malus() + " malus");
    }
}