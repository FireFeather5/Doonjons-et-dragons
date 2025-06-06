package utils;

public class Affichage {
    private final SortieAffichage _sortie;
    private final Couleurs _couleur = new Couleurs();

    public Affichage(SortieAffichage sortie) {
        this._sortie = sortie;
    }

    public void afficher(boolean nouvelleLigne, Object... texte) {
        if (_sortie.equals(SortieAffichage.CONSOLE)) {
            for (Object boutTexte : texte) {
                System.out.print(boutTexte);
            }
            if (nouvelleLigne) {
                System.out.print("\n");
            }
        }
    }

    public void afficherJaune(boolean nouvelleLigne, Object... texte) {
        if (_sortie.equals(SortieAffichage.CONSOLE)) {
            StringBuilder text = new StringBuilder();
            for (Object boutTexte : texte) {
                text.append(_couleur.jaune(boutTexte));
            }
            afficher(nouvelleLigne, text);
        }
    }

    public void afficherRouge(boolean nouvelleLigne, Object... texte) {
        if (_sortie.equals(SortieAffichage.CONSOLE)) {
            StringBuilder text = new StringBuilder();
            for (Object boutTexte : texte) {
                text.append(_couleur.rouge(boutTexte));
            }
            afficher(nouvelleLigne, text);
        }
    }

    public void afficherVert(boolean nouvelleLigne, Object... texte) {
        if (_sortie.equals(SortieAffichage.CONSOLE)) {
            StringBuilder text = new StringBuilder();
            for (Object boutTexte : texte) {
                text.append(_couleur.vert(boutTexte));
            }
            afficher(nouvelleLigne, text);
        }
    }

    public void afficherBleu(boolean nouvelleLigne, Object... texte) {
        if (_sortie.equals(SortieAffichage.CONSOLE)) {
            StringBuilder text = new StringBuilder();
            for (Object boutTexte : texte) {
                text.append(_couleur.bleu(boutTexte));
            }
            afficher(nouvelleLigne, text);
        }
    }

    public void afficherCyan(boolean nouvelleLigne, Object... texte) {
        if (_sortie.equals(SortieAffichage.CONSOLE)) {
            StringBuilder text = new StringBuilder();
            for (Object boutTexte : texte) {
                text.append(_couleur.cyan(boutTexte));
            }
            afficher(nouvelleLigne, text);
        }
    }

    public void afficherBlanc(boolean nouvelleLigne, Object... texte) {
        if (_sortie.equals(SortieAffichage.CONSOLE)) {
            StringBuilder text = new StringBuilder();
            for (Object boutTexte : texte) {
                text.append(_couleur.blanc(boutTexte));
            }
            afficher(nouvelleLigne, text);
        }
    }

    public void afficherNoir(boolean nouvelleLigne, Object... texte) {
        if (_sortie.equals(SortieAffichage.CONSOLE)) {
            StringBuilder text = new StringBuilder();
            for (Object boutTexte : texte) {
                text.append(_couleur.noir(boutTexte));
            }
            afficher(nouvelleLigne, text);
        }
    }
}