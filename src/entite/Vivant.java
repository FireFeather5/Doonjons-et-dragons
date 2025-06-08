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

    String getLilInfos();

    int getIni();

    int getPV();

    TypeVivant getTypeVivant();

    int[] getPos();

    @Override
    String toString();

}
