package ca.cours5b5.charleslangevin.exceptions;

import android.util.Log;

public class ErreurObservation extends RuntimeException{

    public ErreurObservation(String message){
        Log.i("Atelier04",  "" + message);
    }

    public ErreurObservation(Exception e){
        e.getMessage();
    }
}