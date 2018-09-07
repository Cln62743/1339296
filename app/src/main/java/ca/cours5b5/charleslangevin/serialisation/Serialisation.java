package ca.cours5b5.charleslangevin.serialisation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.charleslangevin.exceptions.ErreurDeSerialisation;

public class Serialisation {
    // TODO Finir la classe
    // Serialisation
    public static Map<String, Object> serialiser(Serialisable obj) throws ErreurDeSerialisation{
        Map<String, Object> objetJson = new HashMap<>();
        String chaineJson = Jsonification.enJson(obj);

        objetJson.put(chaineJson, obj);
        return objetJson;
    }

    private static void serialiserAtributs(Map<String, Object> objetJson, Serialisable obj){}

    private static boolean siAttributSerialisable(Field attribut){
        return false;
    }

    private static void serialiserAtribut(Map<String, Object> objetJson, Serialisable obj, Field attribut){}

    private static Object serialiserValeur(Class type, Object valeur){
        return null;
    }

    // Deserialisation
    public static void deserialiser(Serialisable obj, Map<String, Object> objetJson) throws ErreurDeSerialisation{}

    private static  void deserialiserAttributs(Serialisable obj, Map<String, Object> objetJson){}

    private static  void deserialiserAttribut(Serialisable obj, String nomAttribut, Object valeurAttribut){}

    private static  void deserialiserAttribut(Serialisable obj, Field attribut, Object valeurAttribut){}
}
