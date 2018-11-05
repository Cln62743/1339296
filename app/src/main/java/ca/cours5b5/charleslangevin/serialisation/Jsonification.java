package ca.cours5b5.charleslangevin.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import ca.cours5b5.charleslangevin.donnees.GLog;
import ca.cours5b5.charleslangevin.exceptions.ErreurSerialisation;

public final class Jsonification {

    private Jsonification(){}

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Map<String, Object> aPartirChaineJson(String json) throws ErreurSerialisation {
        return gson.fromJson(json, Map.class);
    }

    public static String enChaineJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        return gson.toJson(objetJson);
    }
}
