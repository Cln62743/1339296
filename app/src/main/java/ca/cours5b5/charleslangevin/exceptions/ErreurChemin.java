package ca.cours5b5.charleslangevin.exceptions;

import android.util.Log;

public class ErreurChemin extends RuntimeException{
    public ErreurChemin(String message) {
        Log.i("Atelier04",  "" + message);
    }
}
