package utils;

public class Couleurs {

    public Couleurs()
    {

    }

    public String jaune(Object texte)
    {
        return "\u001B[33m" + texte + "\u001B[0m";
    }

    public String rouge(Object texte)
    {
        return "\u001B[31m" + texte + "\u001B[0m";
    }

    public String vert(Object texte)
    {
        return "\u001B[32m" + texte + "\u001B[0m";
    }

    public String bleu(Object texte)
    {
        return "\u001B[34m" + texte + "\u001B[0m";
    }

    public String cyan(Object texte)
    {
        return "\u001B[36m" + texte + "\u001B[0m";
    }

    public String blanc(Object texte)
    {
        return "\u001B[37m" + texte + "\u001B[0m";
    }

    public String noir(Object texte)
    {
        return "\u001B[30m" + texte + "\u001B[0m";
    }
}
