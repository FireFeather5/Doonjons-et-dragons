package equipement.armure;

public abstract class Armure {
    private final int _armor_class;
    private final String _name;

    public Armure(int armor_class, String name) {
        this._armor_class = armor_class;
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public int get_armor_class() {
        return this._armor_class;
    }
}
