package ca.cours5b5.charleslangevin.donnees;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Map;

import ca.cours5b5.charleslangevin.exceptions.ErreurSerialisation;
import ca.cours5b5.charleslangevin.usagers.UsagerCourant;

public final class Serveur extends SourceDeDonnees {
    /*
     * Serveur est un singleton
     */

    private Serveur(){}
    private static final Serveur instance = new Serveur();
    public static Serveur getInstance(){ return instance; }

    @Override
    public void chargerModele(final String cheminSauvegarde, final ListenerChargement listenerChargement) {

        if(UsagerCourant.siUsagerConnecte()) {
            DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);

            noeud.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Map<String, Object> objetJson = (Map<String, Object>) dataSnapshot.getValue();
                        listenerChargement.reagirSuccess(objetJson);
                    } else {
                        listenerChargement.reagirErreur(new ErreurSerialisation("Crash au chargement - Class :: Serveur - Methode :: chargerModele"));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    listenerChargement.reagirErreur(databaseError.toException());
                }
            });
        }
    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {
        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);
        noeud.setValue(objetJson);
    }

    @Override
    public void detruireSauvegarde(String cheminSauvegarde){
        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);
        noeud.removeValue();
    }
}
