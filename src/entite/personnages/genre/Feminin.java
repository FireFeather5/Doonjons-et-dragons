package entite.personnages.genre;

public class Feminin implements Genre {


    public String genrer(String mot)
    {
        if (mot.endsWith("en"))
        {
            return mot + "ne";
        }
        if (mot.endsWith("e"))
        {
            return mot;
        }
        return mot + "e";
    }
}
