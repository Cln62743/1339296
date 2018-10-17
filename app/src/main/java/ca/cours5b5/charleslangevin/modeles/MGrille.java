package ca.cours5b5.charleslangevin.modeles;

import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.global.GCouleur;

public class MGrille extends Modele {
    private List<MColonne> colonnes;

    public MGrille(int largeur){

    }

    private void initialiserColonnes(int largeur){

    }

    public List<MColonne> getColonnes() {
        return colonnes;
    }

    public void placerJeton(int colonne, GCouleur couleur){

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
