package entite.personnages;

import Utils.StatusDonjon;
import donjon.Donjon;
import entite.Entite;

public interface Vivant extends Entite {

    String getStat();

    String getInfos();

    String getLilInfos();

    StatusDonjon action(Donjon DJ);

    int getIni();

    int getPV();

    String comAction();

    @Override
    String toString();

}
