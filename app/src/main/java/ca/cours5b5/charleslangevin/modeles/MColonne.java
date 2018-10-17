package ca.cours5b5.charleslangevin.modeles;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.global.GCouleur;

public class MColonne extends Modele {
    private List<GCouleur> jetons;
    static String classDebug;

    static {
        classDebug = MColonne.class.getSimpleName();
    }

    public MColonne(){
        jetons = new ArrayList<>();
    }

    public void placerJeton(GCouleur couleur){
        //Log.i("Atelier07", classDebug + "::jouerCoup$" + couleur);
        jetons.add(couleur);
    }

    public List<GCouleur> getJetons() { return jetons; }

    /**
     * Inutilisee
     */
    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {

    }

    /**
     * Inutilisee
     */
    @Override
    public Map<String, Object> enObjetJson() {

        return null;
    }
}
