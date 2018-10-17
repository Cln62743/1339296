package ca.cours5b5.charleslangevin.modeles;

import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.global.GCouleur;

public class MColonne extends Modele {
    // TODO
    private List<GCouleur> jetons;

    public MColonne(){}

    public List<GCouleur> getJetons() { return jetons; }

    public void placerJeton(GCouleur couleur){

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
