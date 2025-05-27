package entite.personnages;

import donjon.Donjon;
import entite.Entite;

public interface Vivant extends Entite {

    public String getStat();

    public String getInfos();

    public String getLilInfos();

    public int action(Donjon DJ);

    public int getIni();

    public int getPV();

    public String comAction();

    @Override
    public String toString();

}
