package ca.cours5b5.charleslangevin.activities;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.Locale;

import ca.cours5b5.charleslangevin.R;

public class AParametres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        // en francais = fr
        // en anglais = en
        //String lang = Locale.getDefault().getLanguage();

        //Log.d("msgLang", this.getResources().getString(R.string.greeting));

        /*int deviceOrientation = this.getResources().getConfiguration().orientation;
        if(deviceOrientation == Configuration.ORIENTATION_PORTRAIT){
            Log.d("msgOri", ""+this.getResources().getString(R.string.portrait));
        }else{
            Log.d("msgOri", ""+this.getResources().getString(R.string.landscape));
        }*/
    }
}
