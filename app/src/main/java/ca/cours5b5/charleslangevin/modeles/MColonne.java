package ca.cours5b5.charleslangevin.modeles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.global.GCouleur;

public class MColonne extends Modele {
    private List<GCouleur> jetons;

    public MColonne(){
        jetons = new ArrayList<>();
    }

    public List<GCouleur> getJetons() { return jetons; }

    public void placerJeton(GCouleur couleur){
        // TODO
        jetons.add(couleur);
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
