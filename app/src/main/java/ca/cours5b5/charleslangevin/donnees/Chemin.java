package ca.cours5b5.charleslangevin.donnees;

import java.util.ArrayList;

import ca.cours5b5.charleslangevin.exceptions.ErreurChemin;

public class Chemin extends ArrayList<String> {
    // TODO Finir la classe
    private final char separateur = '/';
    String chemin = null;

    public Chemin(String... segments) {
        for(String segment : segments){
            chemin += segment + separateur;
        }
    }

    @Override
    public String toString() throws ErreurChemin {
        super.toString();
        return null;
    }
}
