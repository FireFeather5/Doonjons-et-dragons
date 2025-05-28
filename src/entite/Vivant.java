package entite;

import Utils.StatusDonjon;
import donjon.Donjon;

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
