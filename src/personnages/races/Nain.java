package personnages.races;

public class Nain implements Races {

    private static int[] _stats = {0, 6, 0, 0, 0};
    //augmente la for de 6

    public int[] Augment()
    {
        return _stats;
    }
}
