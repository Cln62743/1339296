package ca.cours5b5.charleslangevin.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.Map;

public class Jsonification {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Map<String, Object> enObjetJson(String json){ return gson.fromJson(json, Map.class); }

    public static String enChaine(Map<String, Object> objetJson){ return gson.toJson(objetJson); }
}
