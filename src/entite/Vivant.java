package entite;

import Utils.StatusDonjon;
import Utils.TypeVivant;
import donjon.Donjon;

public interface Vivant extends Entite {

    String getStat();

    String getInfos();

    String getLilInfos();

    int getIni();

    int getPV();

    void comAction(String comAct);

    TypeVivant getTypeVivant();

    @Override
    String toString();

}
