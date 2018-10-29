package ca.cours5b5.charleslangevin.activites;


import android.os.Bundle;
import android.util.Log;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.controleurs.ControleurModeles;
import ca.cours5b5.charleslangevin.donnees.SauvegardeTemporaire;
import ca.cours5b5.charleslangevin.modeles.MParametres;
import ca.cours5b5.charleslangevin.modeles.MPartie;

public class APartie extends Activite {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Atelier","APartie::onCreate");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_partie);
    }

    @Override
    protected void onPause() {
        Log.d("Atelier","APartie::onPause");
        super.onPause();

        ControleurModeles.sauvegarderModele(MPartie.class.getSimpleName());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("Atelier","APartie::onSaveInstanceState");
        super.onSaveInstanceState(outState);

        ControleurModeles.sauvegarderModeleDansCetteSource(MPartie.class.getSimpleName(),
                new SauvegardeTemporaire(outState));
    }
}