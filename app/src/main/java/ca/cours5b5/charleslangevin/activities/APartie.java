package ca.cours5b5.charleslangevin.activities;

import android.os.Bundle;
import android.util.Log;

import java.util.Map;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.controleurs.ControleurObservation;
import ca.cours5b5.charleslangevin.modeles.MParametres;
import ca.cours5b5.charleslangevin.modeles.MPartie;
import ca.cours5b5.charleslangevin.serialisation.Jsonification;

public class APartie extends Activite {

    static String classDebug;

    static {

        classDebug = APartie.class.getSimpleName();
        Log.i("Atelier06", classDebug + "::static");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Atelier06", classDebug + "::onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie);
        if(savedInstanceState != null) {
            restaurerPartie(savedInstanceState);
        }
    }

    private void restaurerPartie(Bundle savedInstanceState){
        String json = savedInstanceState.getString(MPartie.class.getSimpleName());
        Map<String, Object> objetJson = Jsonification.enObjetJson(json);

        Log.i("Atelier08", classDebug + "::restaurerParametres, clé: " + MPartie.class.getSimpleName());
        Log.i("Atelier08", classDebug + "::restaurerParametres, json:\n" + json);
        ControleurObservation.partie.aPartirObjetJson(objetJson);
    }


    @Override
    protected void onResume(){
        Log.i("Atelier06", classDebug + "::onResume");
        super.onResume();

        // Juste avant d'afficher
    }


    @Override
    protected void onPause(){
        Log.i("Atelier06", classDebug + "::onPause");
        super.onPause();

        // L'activité est en pause
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        Log.i("Atelier06", classDebug + "::onSaveInstanceState");
        super.onSaveInstanceState(outState);

        // Code pour sauvegarder les données
        sauvegarderPartie(outState);
    }

    private void sauvegarderPartie(Bundle outState){
        // Code pour sauvegarder les données
        Map<String, Object> objetJson = ControleurObservation.partie.enObjetJson();
        String json = Jsonification.enChaine(objetJson);

        Log.i("Atelier08", classDebug + "::sauvegarderPartie, clé: " + MPartie.class.getSimpleName());
        Log.i("Atelier08", classDebug + "::sauvegarderPartie, json:\n" + json);

        outState.putString(MPartie.class.getSimpleName(), json);
    }

    @Override
    protected void onDestroy(){
        Log.i("Atelier06", classDebug + "::onDestroy");
        super.onDestroy();

        // Juste avant de détruire
    }
}