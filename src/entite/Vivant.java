package entite;

import donjon.Donjon;

public interface Vivant extends Entite {

    String getStat();

    String getInfos();

    String getLilInfos();

    int action(Donjon DJ);

    int getIni();

    int getPV();

    String comAction();

    @Override
    String toString();

}
