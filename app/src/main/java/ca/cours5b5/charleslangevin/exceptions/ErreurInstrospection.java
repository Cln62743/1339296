package ca.cours5b5.charleslangevin.exceptions;

import android.util.Log;

public class ErreurInstrospection extends RuntimeException{
    public ErreurInstrospection(String message){
        Log.i("Atelier04",  "" + message);
    }
}
