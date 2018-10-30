package ca.cours5b5.charleslangevin.donnees;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

import ca.cours5b5.charleslangevin.serialisation.Jsonification;

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
        String cle = getNomModele(cheminSauvegarde);
        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cle);
        if(noeud != null){
            String json = noeud.getKey();
            Map<String, Object> objetJson = Jsonification.aPartirChaineJson(json);

            return objetJson;
        }
        return null;
    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {
        /*
         * Sauvegarde sur le serveur
         * Utiliser FirebaseDatabase et DatabaseReference
         */
        String cle = getNomModele(cheminSauvegarde);
        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cle);

        String json = Jsonification.enChaineJson(objetJson);
        noeud.setValue(json);
    }

    /*@Override
    public void detruireSauvegarde(String cheminSauvegarde){

        BONUS

    }*/

    /*
    * Obtenir le chemin vers le noeud dans la base de donnee
    * String chemin = //
    * DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(chemin);
    *
    * Ecrire dans la base de donnee
    * Map<String, Object> objetJson = //
    * noeud.setValue(objetJson);
    *
    * Obtenir le chemin vers le noeud dans la base de donnee
    * String chemin = //
    * DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(chemin);
    *
    * Pour detruire le noeud
    * noeud.removeValue();
    */
}
