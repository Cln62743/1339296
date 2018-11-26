package ca.cours5b5.charleslangevin.exceptions;


public class ErreurObservation extends RuntimeException {

    public ErreurObservation(Exception e){
        super(e);
    }

    public ErreurObservation(String message){
        super(message);
    }


}
