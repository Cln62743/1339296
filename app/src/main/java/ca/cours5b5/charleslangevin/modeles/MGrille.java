package ca.cours5b5.charleslangevin.modeles;


import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.global.GCouleur;

public class MGrille extends Modele {
    private List<MColonne> colonnes;

    public MGrille(int largeur){
        // TODO
        initialiserColonnes(largeur);
    }

    private void initialiserColonnes(int largeur){
        // TODO
        for(int i = 0; i < largeur; i++) {
            colonnes.add(new MColonne());
        }
    }

    public List<MColonne> getColonnes() {
        return colonnes;
    }

    public void placerJeton(int colonne, GCouleur couleur){
        MColonne curColonne = colonnes.get(colonne);
        curColonne.placerJeton(couleur);
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
