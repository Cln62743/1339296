package ca.cours5b5.charleslangevin.exceptions;

import android.util.Log;

public class ErreurAction extends RuntimeException{

    public ErreurAction(String message){
        Log.i("Atelier07",  "" + message);
    }

    public ErreurAction(Exception e){
        e.getMessage();
    }
}
