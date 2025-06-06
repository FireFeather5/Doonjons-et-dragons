package entite;

import utils.TypeVivant;

public interface Vivant extends Entite {

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
