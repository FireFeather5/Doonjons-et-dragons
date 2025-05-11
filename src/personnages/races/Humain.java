package personnages.races;

public class Humain implements Races {

    private static int[] _stats = {2, 2, 2, 2, 2};
    //augmente toutes les stats de 2

    public int[] augment()
    {
        return _stats;
    }

}
