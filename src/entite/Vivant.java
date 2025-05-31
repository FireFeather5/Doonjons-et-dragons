package entite;

import Utils.StatusDonjon;
import Utils.TypeVivant;
import donjon.Donjon;

public interface Vivant extends Entite {

    String getStat();

    String getInfos();

    String getLilInfos();

    //StatusDonjon action(Donjon DJ);

    int getIni();

    int getPV();

    void comAction();

    TypeVivant getTypeVivant();

    @Override
    String toString();

}
