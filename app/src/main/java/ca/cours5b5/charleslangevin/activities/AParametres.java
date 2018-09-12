package ca.cours5b5.charleslangevin.activities;

import android.os.Bundle;
import android.util.Log;

import java.util.Map;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.modeles.MParametres;
import ca.cours5b5.charleslangevin.serialisation.Jsonification;

public class AParametres extends Activite {
    static String classDebug;

    static {

        classDebug = AParametres.class.getSimpleName();
        Log.i("Atelier04", classDebug + "::static");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Atelier04", classDebug + "::onCreate");
        super.onCreate(savedInstanceState);

        restaurerParametres(savedInstanceState);
        setContentView(R.layout.activity_parametres);
    }

    private void restaurerParametres(Bundle savedInstanceState){
        if(savedInstanceState != null){
            String json = savedInstanceState.getString(MParametres.class.getSimpleName());
            Map<String, Object> objetJson = Jsonification.enObjetJson(json);

            Log.i("Atelier05", classDebug + "::restaurerParametres, clé: " + MParametres.class.getSimpleName());
            Log.i("Atelier05", classDebug + "::restaurerParametres, json:\n" + json);

            MParametres.instance.aPartirObjetJson(objetJson);
        }
    }

    @Override
    protected void onResume(){
        Log.i("Atelier04", classDebug + "::onResume");
        super.onResume();

        // Juste avant d'afficher
    }

    @Override
    protected void onPause(){
        Log.i("Atelier04", classDebug + "::onPause");
        super.onPause();


        // L'activité est en pause
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        Log.i("Atelier04", classDebug + "::onSaveInstanceState");
        super.onSaveInstanceState(outState);

        sauvegarderParametres(outState);
    }

    private void sauvegarderParametres(Bundle outState){
        // Code pour sauvegarder les données
        Map<String, Object> objetJson = MParametres.instance.enObjetJson();
        String json = Jsonification.enChaine(objetJson);

        Log.i("Atelier05", classDebug + "::sauvegarderParametres, clé: " + MParametres.class.getSimpleName());
        Log.i("Atelier05", classDebug + "::sauvegarderParametres, json:\n" + json);

        outState.putString(MParametres.class.getSimpleName(), json);
    }

    @Override
    protected void onDestroy(){
        Log.i("Atelier04", classDebug + "::onDestroy");
        super.onDestroy();

        // Juste avant de détruire
    }
}
