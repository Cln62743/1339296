package ca.cours5b5.charleslangevin.modeles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.exceptions.ErreurSerialisation;
import ca.cours5b5.charleslangevin.global.GCouleur;
import ca.cours5b5.charleslangevin.serialisation.AttributSerialisable;

public class MColonne extends Modele {

    private List<MJeton> jetons;

    public MColonne(){
        jetons = new ArrayList<>();
    }


    public List<MJeton> getJetons() {
        return jetons;
    }

    /*public MJeton getJeton(){
        MJeton selectedJeton = null;
        for (MJeton jeton: jetons) {
            GCouleur couleur = jeton.getCouleur();
            if(couleur == null){
                return jeton;
            }
        }
        return selectedJeton;
    }*/

    /*public MJeton getNextJeton(){
        MJeton selectedJeton = null;
        boolean found = false;
        for (MJeton jeton: jetons) {
            GCouleur couleur = jeton.getCouleur();
            if(found){
                return jeton;
            }
            if(couleur == null){
                found = true;
            }
        }
        return selectedJeton;
    }*/


    public void placerJeton(GCouleur couleur) {
        jetons.add(new MJeton(couleur));
    }


    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        throw new UnsupportedOperationException();
    }


    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        throw new UnsupportedOperationException();
    }


}
