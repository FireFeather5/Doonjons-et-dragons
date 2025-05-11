import java.util.Scanner;

public class MJ {



    Scanner sc = new Scanner(System.in);

    public void createDJ()
    {
        System.out.println("taille cote 1");
        String tc1s = sc.nextLine();
        System.out.println("taille cote 2");
        String tc2s = sc.nextLine();
        int tc1 = Integer.parseInt(tc1s);
        int tc2 = Integer.parseInt(tc2s);

        if (((15 <= tc1) && (tc1 <= 25)) && ((15 <= tc2) && (tc2 <= 25)))
        {
            Donjon donj = new Donjon(tc1, tc2);
        }
        else
        {
            System.out.println("Erreur dans la taille du donjon");
            this.createDJ();
        }
    }

    public void addObst()
    {
        System.out.println("position cote 1");
        String pc1s = sc.nextLine();
        System.out.println("position cote 2");
        String pc2s = sc.nextLine();
        int pc1 = Integer.parseInt(pc1s);
        int pc2 = Integer.parseInt(pc2s);


    }

}
