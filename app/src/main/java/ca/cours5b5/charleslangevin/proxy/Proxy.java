package ca.cours5b5.charleslangevin.proxy;

import com.google.firebase.database.DatabaseReference;

public abstract class Proxy {
    private String cheminServeur;
    protected DatabaseReference noeudServeur;


    //TODO
    public Proxy(String cheminServeur){

    }

    public void connecterAuServeur(){
        /*
        * Obtenir le noeud
        *
        */
    }

    public void deconnecterDuServeur(){
        /*
        * Oublier le noeud
        *
        */
    }

    public abstract void detruireValeurs();
}
