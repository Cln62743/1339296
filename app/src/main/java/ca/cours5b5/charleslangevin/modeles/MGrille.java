package ca.cours5b5.charleslangevin.modeles;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.global.GCouleur;

public class MGrille extends Modele {
    private List<MColonne> colonnes;
    private int maxJetons;
    static String classDebug;

    static {
        classDebug = MGrille.class.getSimpleName();
    }

    public MGrille(int largeur, int hauteur){
        maxJetons = hauteur;
        initialiserColonnes(largeur);
    }

    private void initialiserColonnes(int largeur){
        colonnes = new ArrayList<>();
        for(int i = 0; i < largeur; i++) {
            colonnes.add(new MColonne());
        }
    }

    public List<MColonne> getColonnes() {
        return colonnes;
    }

    public void placerJeton(int colonne, GCouleur couleur){
        //Log.i("Atelier07", classDebug + "::jouerCoup$" + colonne + " Couleur -> " + couleur);
        MColonne curColonne = colonnes.get(colonne);
        int columnSize = curColonne.getJetons().size();
        if(columnSize < maxJetons) {
            curColonne.placerJeton(couleur);
        }
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {
        /**
         * Inutilisee
         */
    }

    @Override
    public Map<String, Object> enObjetJson() {
        /**
         * Inutilisee
         */
        return null;
    }
}
