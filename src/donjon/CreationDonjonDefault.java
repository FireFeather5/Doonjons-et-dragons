package donjon;

import Utils.MJ;
import Utils.Inputs;
import de.De;
import entite.Monstre;
import entite.Obstacle;
import entite.equipement.Equipement;
import entite.equipement.arme.courante.*;
import entite.equipement.arme.guerre.*;
import entite.equipement.arme.distance.*;
import entite.equipement.armure.legere.*;
import entite.equipement.armure.lourde.*;

import java.util.ArrayList;
import java.util.Random;

public class CreationDonjonDefault {

    private final Inputs _input = new Inputs();

    public CreationDonjonDefault()
    {
    }

    public Donjon createDefaultDJ()
    {
        Random rand = new Random();
        int choixDonjon = rand.nextInt(0, 3);

        int[] taille = new int[] {18, 23};

        Donjon DJ = new Donjon(taille);

        Obstacle obs = new Obstacle();

        Equipement epeeLongue = new EpeeLongue();
        Equipement fronde = new Fronde();
        Equipement cotteMaille = new CotteMaille();
        Equipement demiPlate = new DemiPlate();
        Equipement rapiere = new Rapiere();

        Monstre demogordgon = new Monstre("Demogorgon", ">X(", 1, new De(2, 6), new De(4, 4));
        Monstre dragonBleu = new Monstre("Dragon Bleu", "B/", 3, new De(2, 6), new De(3, 4));

        switch(choixDonjon) {
            case 0:
                obs.addPos(_input.positionCase("J8"), DJ);
                obs.addPos(_input.positionCase("J9"), DJ);
                obs.addPos(_input.positionCase("K9"), DJ);
                obs.addPos(_input.positionCase("K10"), DJ);
                obs.addPos(_input.positionCase("K11"), DJ);

                DJ.positionMonstre(_input.positionCase("P14"), demogordgon);
                DJ.positionMonstre(_input.positionCase("E4"), dragonBleu);

                DJ.positionEquipement(_input.positionCase("K4"), epeeLongue);
                DJ.positionEquipement(_input.positionCase("J15"), fronde);
                DJ.positionEquipement(_input.positionCase("Q7"), cotteMaille);
                DJ.positionEquipement(_input.positionCase("E15"), demiPlate);
                DJ.positionEquipement(_input.positionCase("T14"), rapiere);
                break;
            case 1:
                obs.addPos(_input.positionCase("H7"), DJ);
                obs.addPos(_input.positionCase("H8"), DJ);
                obs.addPos(_input.positionCase("H9"), DJ);
                obs.addPos(_input.positionCase("H10"), DJ);
                obs.addPos(_input.positionCase("H11"), DJ);
                obs.addPos(_input.positionCase("H12"), DJ);

                obs.addPos(_input.positionCase("N7"), DJ);
                obs.addPos(_input.positionCase("N8"), DJ);
                obs.addPos(_input.positionCase("N9"), DJ);
                obs.addPos(_input.positionCase("N10"), DJ);
                obs.addPos(_input.positionCase("N11"), DJ);
                obs.addPos(_input.positionCase("N12"), DJ);

                DJ.positionMonstre(_input.positionCase("T15"), dragonBleu);
                DJ.positionMonstre(_input.positionCase("K9"), demogordgon);

                DJ.positionEquipement(_input.positionCase("F5"), epeeLongue);
                DJ.positionEquipement(_input.positionCase("P14"), fronde);
                break;
            case 2:
                obs.addPos(_input.positionCase("G7"), DJ);
                obs.addPos(_input.positionCase("G8"), DJ);
                obs.addPos(_input.positionCase("G9"), DJ);
                obs.addPos(_input.positionCase("G10"), DJ);
                obs.addPos(_input.positionCase("G11"), DJ);
                obs.addPos(_input.positionCase("H7"), DJ);
                obs.addPos(_input.positionCase("H9"), DJ);
                obs.addPos(_input.positionCase("I8"), DJ);

                obs.addPos(_input.positionCase("K7"), DJ);
                obs.addPos(_input.positionCase("K9"), DJ);
                obs.addPos(_input.positionCase("K10"), DJ);
                obs.addPos(_input.positionCase("K11"), DJ);
                obs.addPos(_input.positionCase("L7"), DJ);
                obs.addPos(_input.positionCase("L9"), DJ);
                obs.addPos(_input.positionCase("L11"), DJ);
                obs.addPos(_input.positionCase("M7"), DJ);
                obs.addPos(_input.positionCase("M8"), DJ);
                obs.addPos(_input.positionCase("M9"), DJ);
                obs.addPos(_input.positionCase("M11"), DJ);

                obs.addPos(_input.positionCase("O7"), DJ);
                obs.addPos(_input.positionCase("P7"), DJ);
                obs.addPos(_input.positionCase("P8"), DJ);
                obs.addPos(_input.positionCase("P9"), DJ);
                obs.addPos(_input.positionCase("P10"), DJ);
                obs.addPos(_input.positionCase("P11"), DJ);

                DJ.positionMonstre(_input.positionCase("O8"), demogordgon);
                DJ.positionMonstre(_input.positionCase("H11"), dragonBleu);

                DJ.positionEquipement(_input.positionCase("H8"), cotteMaille);
                DJ.positionEquipement(_input.positionCase("L8"), epeeLongue);
                DJ.positionEquipement(_input.positionCase("L10"), fronde);
        }
        DJ.afficherDJ();
        return DJ;
    }

    public Donjon donjonRandom() {

        ArrayList<Equipement> equipements = new ArrayList<>();

        equipements.add(new Baton());
        equipements.add(new MasseArme());
        equipements.add(new ArbaleteLegere());
        equipements.add(new ArcCourt());
        equipements.add(new Fronde());
        equipements.add(new EpeeDeuxMains());
        equipements.add(new EpeeLongue());
        equipements.add(new Rapiere());
        equipements.add(new ArmureEcaille());
        equipements.add(new DemiPlate());
        equipements.add(new CotteMaille());
        equipements.add(new Harnois());

        MJ mj = new MJ();

        Random rand = new Random();

        int[] taille = {rand.nextInt(15, 26), rand.nextInt(15, 26)};

        Donjon DJ = new Donjon(taille);
        int numeroMonstre = 1;
        for (int i = 0; i < taille[0]; i++) {
            for (int j = 0; j < taille[1]; j++) {
                int element = rand.nextInt(0, 100);
                if (element < 1) {
                    Monstre mystique = mj.createM(
                            "Monstre mystique",
                            "<" + numeroMonstre++ + ">",
                            rand.nextInt(1, 4),
                            new De(rand.nextInt(1, 4), rand.nextInt(4, 10)),
                            new De(rand.nextInt(1, 4), rand.nextInt(4, 10))
                    );
                    DJ.positionMonstre(new int[]{i+1, j+1}, mystique);
                }
                else if (element < 6) {
                    DJ.positionEquipement(new int[]{i+1, j+1}, equipements.get(rand.nextInt(0, equipements.size())));
                }
                else if (element < 16) {
                    DJ.positionObstacle(new int[]{i+1, j+1}, new Obstacle());
                }
            }
        }
        DJ.afficherDJ();
        return DJ;
    }
}
