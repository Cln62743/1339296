package ca.cours5b5.charleslangevin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.charleslangevin.R;

public class AMenuPrincipal extends Activite {
    static String classDebug;

    static {

        classDebug = AMenuPrincipal.class.getSimpleName();
        Log.i("Atelier04", classDebug + "::static");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Atelier04", classDebug + "::onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);

        Button btnSettings = this.findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                // Reaction

                Intent myIntent = new Intent(getApplicationContext(), AParametres.class);
                startActivity(myIntent);
            }
        });
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

        // Code pour sauvegarder les données
    }

    @Override
    protected void onDestroy(){
        Log.i("Atelier04", classDebug + "::onDestroy");
        super.onDestroy();

        // Juste avant de détruire
    }

}
