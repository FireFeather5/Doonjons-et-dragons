package Utils;

import de.De;
import donjon.Donjon;
import entite.Obstacle;
import entite.equipement.Equipement;
import entite.Monstre;
import entite.personnages.Personnage;

import java.util.ArrayList;
import java.util.Scanner;

public class MJ {

    private final Couleurs _cl = new Couleurs();
    private final Inputs _input = new Inputs();
    private final Actions _action = new Actions();

    ArrayList<String> _monstresCrees = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public MJ()
    {

    }

    public Donjon creationDonjon()
    {
        System.out.println("\n\nVoulez-vous creer un donjons (o/n) ? (dans le cas contraire, vous pourrez choisir entre un donjon par défaut ou un genere aleatoirement)");
        if (sc.nextLine().equals("o"))
        {
            int[] tailleDj = _input.tailleDonjon();
            Donjon DJ = new Donjon(tailleDj);

            //boolean fini;

            System.out.println("Aperçu du donjon :");
            DJ.afficherDJ();

            System.out.println("\n\nLa taille vous convient-il (o/n) ?");
            if (sc.nextLine().equals("n"))
            {
                creationDonjon();
            }

            _input.ajoutObstacle(DJ, this);
            _input.ajoutMonstre(DJ, this);
            _input.ajoutEquipement(DJ, this);

            return DJ;
        }
        else {
            return null;
        }
    }

    public void addObst(Donjon DJ)
    {
        int[] pos = _input.choixCase("de l'obstacle");

        Obstacle obs = new Obstacle();
        boolean test = obs.addPos(pos, DJ);

        if (!test)
        {
            System.out.println(_cl.rouge() + "Erreur dans la selection de la position" + _cl.reset());
            addObst(DJ);
        }
    }

    public Monstre createM(String espece, String symb, int portee, De degAtt, De charac)
    {
        Monstre mons = new Monstre(espece, symb, portee, degAtt, charac);

        for (String monstre : this._monstresCrees) {
            if (monstre.equals(espece)) {
                mons.multiMonstre();
            }
        }
        this._monstresCrees.add(espece);

        return mons;
    }

    public void posJ(Donjon DJ, Personnage perso)
    {
        int[] pos = _input.choixCase("de " + perso);

        boolean test = DJ.positionPersonnage(pos, perso);

        if (!test)
        {
            System.out.println(_cl.rouge() + "Erreur dans la selection de la position" + _cl.reset());
            this.posJ(DJ, perso);
        }
    }

    public void posM(Donjon DJ, Monstre mons)
    {
        int[] pos = _input.choixCase("de " + mons);

        boolean test = DJ.positionMonstre(pos, mons);

        if (!test)
        {
            System.out.println(_cl.rouge() + "Erreur dans la selection de la position" + _cl.reset());
            this.posM(DJ, mons);
        }
    }

    public void posEquip(Donjon DJ, Equipement equip)
    {
        int[] pos = _input.choixCase("de l'équipement : " + equip);

        boolean test = DJ.positionEquipement(pos, equip);

        if (!test)
        {
            System.out.println(_cl.rouge() + "Erreur dans la selection de la position" + _cl.reset());
            this.posEquip(DJ, equip);
        }
    }

    public void presContext()
    {
        System.out.println(_input.contextDonjon());
    }

    public void comAction()
    {
        System.out.println(_input.mjCommenteAction());
    }

    public StatusDonjon actionFT(Donjon DJ)
    {
        StatusDonjon val = StatusDonjon.NORMAL;

        val = _action.actionMjFinTour(DJ, this);

        return val;
    }

    public void depViv(Donjon DJ)
    {
        int choix = 0;

        System.out.println("\nChoisissez un joueur ou un monstre à déplacer :");
        for (Personnage pers : DJ.getListePerso()) {
            System.out.println(choix++ + "- " + pers);
        }
        for (Monstre mons : DJ.getListeMonstre()) {
            System.out.println(choix++ + "- " + mons);
        }

        try {
            int perso = Integer.parseInt(sc.nextLine());

            int[] posD = DJ.getListePerso().get(perso).getPos();

            int[] posF = _input.choixCase("où mettre l'entité");

            DJ.switchCase(posD, posF);
        }
        catch (NumberFormatException erreur)
        {
            System.out.println(_cl.rouge() + "\nErreur dans la saisie." + _cl.reset() + "\nRecomencez");
            depViv(DJ);
        }
    }

    public StatusDonjon degatJoueur(Donjon DJ) {
        int choix = 0;
        StatusDonjon val = StatusDonjon.NORMAL;

        System.out.println("\nChoisissez un joueur :");
        for (Personnage pers : DJ.getListePerso()) {
            System.out.println(choix++ + "- " + pers);
        }

        choix = sc.nextInt();

        System.out.println("Combien de dé(s) pour infliger les dégats ?");
        int nbDe = sc.nextInt();
        System.out.println("Combien de faces pour les dés ?");
        int nbFaceDe = sc.nextInt();

        int dgt = new De(nbDe, nbFaceDe).roll();

        System.out.println("Le Utils.MJ inflige " + dgt + " a " + DJ.getListePerso().get(choix));
        val = DJ.getListePerso().get(choix).seFaitAttaquer(dgt, DJ);

        return val;
    }

    public StatusDonjon degatMonstre(Donjon DJ) {
        int choix = 0;
        StatusDonjon val;

        System.out.println("\nChoisissez un monstre :");
        for (Monstre mons : DJ.getListeMonstre()) {
            System.out.println(choix++ + ". " + mons);
        }

        choix = sc.nextInt();

        System.out.println("Combien de dé(s) pour infliger les dégats ?");
        int nbDe = sc.nextInt();
        System.out.println("Combien de faces pour les dés ?");
        int nbFaceDe = sc.nextInt();

        int dgt = new De(nbDe, nbFaceDe).roll();

        System.out.println("Le Utils.MJ inflige " + dgt + " a " + DJ.getListeMonstre().get(choix));
        val = DJ.getListeMonstre().get(choix).seFaitAttaquer(dgt, DJ);

        return val;
    }
}
