package personnages.races;

public class Halfelins implements Races {

    private static int[] _stats = {0, 0, 4, 2, 0};
    //augmente la dex de 4 et la vit de 2

    public int[] augment()
    {
        return _stats;
    }
}
