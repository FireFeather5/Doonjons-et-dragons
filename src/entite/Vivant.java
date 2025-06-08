package entite;

import donjon.Donjon;
import utils.Erreurs;
import utils.StatusDonjon;
import utils.TypeVivant;

public interface Vivant extends Entite {


    void setPosition(int pos1, int pos2);
    Erreurs seDeplacer(Donjon DJ, int[] pos);
    StatusDonjon seFaitAttaquer(int degats, Donjon DJ);



    String getStat();

    String getInfos();

    String getPetitesInfos();

    int getInitiative();

    int getPV();

    TypeVivant getTypeVivant();

    int[] getPosition();

    @Override
    String toString();

}
