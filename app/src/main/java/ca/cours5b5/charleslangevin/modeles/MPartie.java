package ca.cours5b5.charleslangevin.modeles;

import java.util.Map;

import ca.cours5b5.charleslangevin.exceptions.ErreurDeSerialisation;
import ca.cours5b5.charleslangevin.serialisation.AttributSerialisable;

public class MPartie extends Modele{

    @AttributSerialisable
    public MParametresPartie parametres;
    private final  String __parametres = "parametres";

    public MPartie(MParametresPartie parametres){

    }

    public MParametresPartie getParametres(){
        return null;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurDeSerialisation{
        /**
         * Inutilisee pour l'instant
         *
         */
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurDeSerialisation{
        /**
         * Inutilisee pour l'instant
         *
         */
        return null;
    }

}
