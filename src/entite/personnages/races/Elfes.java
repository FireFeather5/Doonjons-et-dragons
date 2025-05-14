package entite.personnages.races;

public class Elfes implements Races {

    private static int[] _stats = {0, 0, 6, 0, 0};
    //augmente la dex de 6

    public int[] augment()
    {
        return _stats;
    }
}
