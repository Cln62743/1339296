package ca.cours5b5.charleslangevin.modeles;

import java.util.Map;

import ca.cours5b5.charleslangevin.exceptions.ErreurSerialisation;
import ca.cours5b5.charleslangevin.global.GCouleur;
import ca.cours5b5.charleslangevin.serialisation.AttributSerialisable;

public class MJeton extends Modele {

    private GCouleur couleur;

    public MJeton(GCouleur couleur){

        this.couleur = couleur;

    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {

        throw new UnsupportedOperationException();

    }

    @Override
    public Map<String, Object> enObjetJson() {

        throw new UnsupportedOperationException();

    }

    public boolean siMemeCouleur(GCouleur couleur) {
        return this.couleur.equals(couleur);
    }

    public GCouleur getCouleur() {
        return couleur;
    }

}
