package ca.cours5b5.charleslangevin.activites;

import android.os.Bundle;
import android.util.Log;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.controleurs.ControleurModeles;
import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.donnees.SauvegardeTemporaire;
import ca.cours5b5.charleslangevin.modeles.MParametres;
import ca.cours5b5.charleslangevin.modeles.MPartie;

public class AParametres extends Activite implements Fournisseur{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Atelier","AParametres::onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
    }

    @Override
    protected void onPause() {
        Log.d("Atelier","AParametres::onPause");
        super.onPause();

        ControleurModeles.sauvegarderModele(MParametres.class.getSimpleName());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("Atelier","AParametres::onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

}
