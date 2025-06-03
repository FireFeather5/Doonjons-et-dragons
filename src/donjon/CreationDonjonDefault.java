package donjon;

import Utils.Inputs;
import de.De;
import entite.Monstre;
import entite.Obstacle;
import entite.equipement.Equipement;
import entite.equipement.arme.distance.Fronde;
import entite.equipement.arme.guerre.EpeeLongue;
import entite.equipement.arme.guerre.Rapiere;
import entite.equipement.armure.legere.DemiPlate;
import entite.equipement.armure.lourde.CotteMaille;

import java.util.ArrayList;

public class CreationDonjonDefault {

    private final Inputs _input = new Inputs();

    public CreationDonjonDefault()
    {
    }

    public Donjon createDefaultDJ()
    {
        int[] taille = new int[] {18, 23};

        Donjon DJ = new Donjon(taille);

        Obstacle obs = new Obstacle();
        obs.addPos(_input.positionCase("J8"), DJ);
        obs.addPos(_input.positionCase("J9"), DJ);
        obs.addPos(_input.positionCase("K9"), DJ);
        obs.addPos(_input.positionCase("K10"), DJ);
        obs.addPos(_input.positionCase("K11"), DJ);

        Monstre demogordgon = new Monstre("Demogorgon", ">X(", 1, new De(2, 6), new De(4, 4));
        DJ.positionMonstre(_input.positionCase("P14"), demogordgon);
        Monstre dragonBleu = new Monstre("Dragon Bleu", "B/", 3, new De(2, 6), new De(3, 4));
        DJ.positionMonstre(_input.positionCase("E4"), dragonBleu);

        Equipement epeeLongue = new EpeeLongue();
        Equipement fronde = new Fronde();
        Equipement cotteMaille = new CotteMaille();
        Equipement demiPlate = new DemiPlate();
        Equipement rapiere = new Rapiere();

        DJ.positionEquipement(_input.positionCase("K4"), epeeLongue);
        DJ.positionEquipement(_input.positionCase("J15"), fronde);
        DJ.positionEquipement(_input.positionCase("Q7"), cotteMaille);
        DJ.positionEquipement(_input.positionCase("E15"), demiPlate);
        DJ.positionEquipement(_input.positionCase("T14"), rapiere);

        DJ.afficherDJ();
        return DJ;
    }
}
