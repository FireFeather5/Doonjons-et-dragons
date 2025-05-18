package statistiques;

public class Stats {
    private int _pointDeVieTot;
    private int _pointDeVie;
    private int _force;
    private int _dexterite;
    private int _vitesse;
    private int _initiative;
    private int _armure;

    public Stats()
    {
        _armure = 0;
    }
    public void pvt(int st)
    {
        _pointDeVieTot = st;
        pv(st);
    }
    public void pv(int st)
    {
        _pointDeVie = st;
    }
    public void forc(int st)
    {
        _force = st;
    }
    public void dex(int st)
    {
        _dexterite = st;
    }
    public void vit(int st)
    {
        _vitesse = st;
    }
    public void ini(int st)
    {
        _initiative = st;
    }
    public void arm(int st)
    {
        _armure = st;
    }

    public void add(Stats other)
    {
        this._pointDeVieTot += other._pointDeVieTot;
        this._pointDeVie += other._pointDeVie;
        this._force += other._force;
        this._dexterite += other._dexterite;
        this._vitesse += other._vitesse;
        this._initiative += other._initiative;
        this._armure += other._armure;
    }

    public int retPvT()
    {
        return _pointDeVieTot;
    }
    public int retPv()
    {
        return _pointDeVie;
    }
    public int retFor()
    {
        return _force;
    }
    public int retDex()
    {
        return _dexterite;
    }
    public int retVit()
    {
        return _vitesse;
    }
    public int retIni()
    {
        return _initiative;
    }
    public int retArm()
    {
        return _armure;
    }
}
