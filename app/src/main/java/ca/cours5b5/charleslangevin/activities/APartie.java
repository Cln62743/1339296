package ca.cours5b5.charleslangevin.activities;

import android.os.Bundle;
import android.util.Log;
import ca.cours5b5.charleslangevin.R;

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
    }

    @Override
    protected void onDestroy(){
        Log.i("Atelier06", classDebug + "::onDestroy");
        super.onDestroy();

        // Juste avant de détruire
    }
}