package ca.cours5b5.charleslangevin.exceptions;

import android.util.Log;

public class ErreurDeConstruction extends RuntimeException {
    public  ErreurDeConstruction(Exception e){
        Log.i("Atelier04",  "" + e);
    }
}
