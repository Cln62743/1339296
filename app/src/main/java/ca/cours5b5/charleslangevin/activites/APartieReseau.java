package ca.cours5b5.charleslangevin.activites;

import android.os.Bundle;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;

public class APartieReseau extends Activite implements Fournisseur {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO
        setContentView(R.layout.activity_partie_reseau);
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*
         * TODO
         * Avec ControleurPartieReseau, détruire la partie sur le serveur
         * Déconnecter ControleurPartieReseau du serveur
         */
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
         * TODO
         * Connecter le ControleurPartieReseau au serveur
         */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*
         * TODO
         * BONUS: avec ControleurModeles, détruire le modèle MPartieReseau
         */
    }
}
