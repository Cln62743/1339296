package ca.cours5b5.charleslangevin.exceptions;

import android.util.Log;

public class ErreurDeSerialisation extends RuntimeException{
    public  ErreurDeSerialisation(Exception e){
        Log.i("Atelier04",  "" + e);
    }
}
