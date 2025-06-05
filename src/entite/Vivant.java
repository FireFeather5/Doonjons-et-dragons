package entite;

import utils.TypeVivant;

public interface Vivant extends Entite {

    String getStat();

    String getInfos();

    String getLilInfos();

    int getIni();

    int getPV();

    TypeVivant getTypeVivant();

    @Override
    String toString();

}
