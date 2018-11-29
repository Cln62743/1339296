package ca.cours5b5.charleslangevin.modeles;

import android.util.Log;

import ca.cours5b5.charleslangevin.global.GCouleur;
import ca.cours5b5.charleslangevin.global.GDirection;

public class MGrilleIA extends MGrille {
    private GCouleur couleurJoueur = GCouleur.ROUGE;;
    private GCouleur couleurIA = GCouleur.JAUNE;;

    public MGrilleIA(int largeur){
        super(largeur);
    }

    public boolean evaluerGagne(){
        boolean result = false;
        int maxLigneJoueur = 0;
        int maxLigneIA = 0;

        for(int idColonne = 0; idColonne < colonnes.size(); idColonne++){
            int ligneJoueur = evaluerGagneCetteColonne(couleurJoueur, idColonne);
            int ligneIA = evaluerGagneCetteColonne(couleurIA, idColonne);

            if(ligneJoueur > maxLigneJoueur) maxLigneJoueur = ligneJoueur;
            if(ligneIA > maxLigneIA) maxLigneIA = ligneIA;
        }

        // Joueur gagne
        if(maxLigneJoueur > maxLigneIA) result = true;

        Log.d("ProjetFinal", "Max Ligne Joueur :: " + maxLigneJoueur);
        Log.d("ProjetFinal", "Max Ligne IA :: " + maxLigneIA);
        // IA gagne if false
        return result;
    }

    private int evaluerGagneCetteColonne(GCouleur couleur, int idColonne){
        MColonne colonne = colonnes.get(idColonne);
        int max = 0;

        for(int idRangee = 0; idRangee <  colonne.getJetons().size(); idRangee++){
            int current = evaluerGagneCetteCase(couleur, idColonne, idRangee);
            if(current > max) max = current;
        }

        return max;
    }

    private int evaluerGagneCetteCase(GCouleur couleur, int idColonne, int idRangee) {
        int max = 0;

        for(GDirection direction : GDirection.directions){
            int current = evaluerGagneDansCetteDirection(couleur, idColonne, idRangee, direction, 0);
            if(current > max) max = current;
        }
        // IA gagne
        return max;
    }

    private int evaluerGagneDansCetteDirection(GCouleur couleur, int idColonne, int idRangee, GDirection direction, int nbSerie) {
        int max = nbSerie;

        if(siMemeCouleurCetteCase(couleur, idColonne, idRangee)){
            int nouvelleColonne = idColonne + direction.incrementHorizontal;
            int nouvelleRangee = idRangee + direction.incrementVertical;

            int current = evaluerGagneDansCetteDirection(couleur, nouvelleColonne, nouvelleRangee, direction, nbSerie + 1);
            if(current > max) max = current;
        }

        return max;
    }

    private boolean siMemeCouleurCetteCase(GCouleur couleur, int idColonne, int idRangee){
        if(idColonne >= 0 && idColonne < colonnes.size()){
            MColonne colonne = colonnes.get(idColonne);

            if(idRangee >= 0 && idRangee < colonne.getJetons().size()){
                return colonne.getJetons().get(idRangee).siMemeCouleur(couleur);
            }
        }
        return false;
    }
}
