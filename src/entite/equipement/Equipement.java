package entite.equipement;

import utils.TypeArme;
import utils.TypeArmure;
import utils.TypeEquipement;
import entite.Entite;
import statistiques.Position;

import java.util.Optional;

public abstract class Equipement implements Entite {
    private final String _name;
    private final int _speedMalus;
    private final int _forceBonus;
    private final Position _pos;
    private final TypeEquipement _typeEquip;
    private final TypeArme _typeArm;
    private final TypeArmure _typeArmur;

    public Equipement(String name, TypeEquipement typeEqu, TypeArme typeArm, TypeArmure typeArmur) {
        this(name, typeEqu, typeArm, typeArmur, 0, 0);
    }

    public Equipement(String name, TypeEquipement typeEqu, TypeArme typeArm, TypeArmure typeArmur, int speedMalus) {
        this(name, typeEqu, typeArm, typeArmur, speedMalus, 0);
    }

    public Equipement(String name, TypeEquipement typeEqu, TypeArme typeArm, TypeArmure typeArmur, int speedMalus, int forceBonus) {
        this._name = name;
        this._speedMalus = speedMalus;
        this._forceBonus = forceBonus;
        _typeEquip = typeEqu;
        _typeArm = typeArm;
        _typeArmur = typeArmur;
        _pos = new Position();
    }

    public void position(int pos1, int pos2)
    {
        _pos.changPos(pos1, pos2);
    }

    public int[] getPosition()
    {
        return _pos.getPosition();
    }

    public int getSpeedMalus() {
        return this._speedMalus;
    }

    public int getForceBonus() {
        return this._forceBonus;
    }

    public String getName() {
        return this._name;
    }


    public TypeEquipement getTypeEquip()
    {
        return _typeEquip;
    }

    public Optional<TypeArme> getTypeArm()
    {
        return Optional.ofNullable(this._typeArm);
    }

    public Optional<TypeArmure> getTypeArmur()
    {
        return Optional.ofNullable(this._typeArmur);
    }


    public String affichage()
    {
        return " * ";
    }
}
