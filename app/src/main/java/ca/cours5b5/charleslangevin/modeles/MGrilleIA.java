package ca.cours5b5.charleslangevin.modeles;

import android.util.Log;
import java.util.ArrayList;
import ca.cours5b5.charleslangevin.global.GCouleur;
import ca.cours5b5.charleslangevin.global.GDirection;

public class MGrilleIA extends MGrille {
    private GCouleur couleurJoueur = GCouleur.ROUGE;
    private GCouleur couleurIA = GCouleur.JAUNE;

    private MGrilleIA temp = null;

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

        //Log.d("ProjetFinal", "Max Ligne Joueur :: " + maxLigneJoueur);
        //Log.d("ProjetFinal", "Max Ligne IA :: " + maxLigneIA);
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

    // MinMax
    /*public int ChoisirCoup(GCouleur curCouleur, MGrilleIA grille){
        int colonneToPlay = -1;
        int value = -10000;
        temp = grille;

        int i = 0;
        for(MJeton jeton : GetJetons()){
            int minMax = MinMax(jeton, 2, curCouleur);
            if(minMax > value) {
                value = minMax;
                colonneToPlay = i;
            }

            i++;
        }

        return colonneToPlay;
    }

    private int MinMax( MJeton curJeton, int depth, GCouleur couleur){
        if(depth == 0){
            return SetValue(curJeton);
        }

        int value;
        if(couleur == couleurIA){
            value = -10000;
            for(MJeton jeton : GetJetons(curJeton)){
                value = Math.max(value, MinMax(jeton, depth -1, couleurJoueur));
            }

        }else{
            value = 10000;
            for(MJeton jeton : GetJetons(curJeton)){
                value = Math.min(value, MinMax(jeton, depth -1, couleurIA));
            }
        }
        return value;
    }

    private ArrayList<MJeton> GetJetons(){
        ArrayList<MJeton> jetons = new ArrayList<>();

        for(MColonne colonne : temp.colonnes){
            MJeton foundJeton = colonne.getJeton();
            jetons.add(foundJeton);
        }
        return jetons;
    }

    private ArrayList<MJeton> GetJetons(MJeton curJeton){
        ArrayList<MJeton> jetons = new ArrayList<>();

        for(MColonne colonne : temp.colonnes){
            MJeton foundJeton = colonne.getJeton();
            if(foundJeton == curJeton) {
                foundJeton = colonne.getNextJeton();
            }

            jetons.add(foundJeton);
        }
        return jetons;
    }

    private int SetValue(MJeton curJeton){
        return 0;
    }*/
}
