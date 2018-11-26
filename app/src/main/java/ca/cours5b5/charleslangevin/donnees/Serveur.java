package ca.cours5b5.charleslangevin.donnees;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import ca.cours5b5.charleslangevin.controleurs.ControleurModeles;
import ca.cours5b5.charleslangevin.exceptions.ErreurModele;
import ca.cours5b5.charleslangevin.serialisation.Jsonification;

public final class Serveur extends SourceDeDonnees {

    private Serveur(){}

    private static final Serveur instance = new Serveur();

    public static Serveur getInstance(){return instance;}


    @Override
    public void chargerModele(final String cheminSauvegarde, final ListenerChargement listenerChargement) {

        DatabaseReference noeudModele = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);

        noeudModele.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    Map<String, Object> objetJson = (Map<String, Object>) dataSnapshot.getValue();

                    listenerChargement.reagirSucces(objetJson);

                }else{

                    listenerChargement.reagirErreur(new ErreurModele("noeudInexistant: " + cheminSauvegarde));

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                listenerChargement.reagirErreur(databaseError.toException());

            }
        });
    }


    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {

        DatabaseReference noeudModele = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);
        noeudModele.setValue(objetJson);

    }

    @Override
    public void detruireSauvegarde(String cheminSauvegarde) {

        DatabaseReference noeudModele = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);
        noeudModele.removeValue();

    }


}
