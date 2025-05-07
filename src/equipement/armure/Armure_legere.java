package equipement.armure;

public abstract class Armure_legere implements Armure {
    private final int _armor_class;
    private final String _name;

    public Armure_legere(int armor_class, String name) {
        this._armor_class = armor_class;
        this._name = name;
    }

    @Override
    public String getName() {
        return this._name;
    }

    @Override
    public int get_armor_class() {
        return this._armor_class;
    }
}
