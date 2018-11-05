package ca.cours5b5.charleslangevin.donnees;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public final class Serveur extends SourceDeDonnees {
    /*
     * Serveur est un singleton
     */

    private Serveur(){}

    private static final Serveur instance = new Serveur();

    public static Serveur getInstance(){ return instance; }

    @Override
    public Map<String, Object> chargerModele(String cheminSauvegarde) {
        /*
        * BONUS: est-ce possible d'implanter cette methode avec cette signature?
        */
        return null;
    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {
        /*
         * Sauvegarde sur le serveur
         * Utiliser FirebaseDatabase et DatabaseReference
         */
        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);

        noeud.setValue(objetJson);
    }

    /*@Override
    public void detruireSauvegarde(String cheminSauvegarde){

        BONUS

    }*/
}
