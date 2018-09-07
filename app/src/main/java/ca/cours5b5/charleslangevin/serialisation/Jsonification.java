package ca.cours5b5.charleslangevin.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.Map;

public class Jsonification {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T extends Serialisable> T aPartirJson(Class<T> classAImplanter, String json){
        return gson.fromJson(json, classAImplanter);
    }

    public static String enJson(Serialisable obj){
        return gson.toJson(obj);
    }

    private static <T extends Serialisable> T aPartirJson(Serialisable obj, String json){
        return gson.fromJson(json, (Type) obj);
    }

    private static <T extends Serialisable> T aPartirObjetJson(Serialisable obj, Map<String, Object> objetJson){
        return gson.fromJson((JsonElement) objetJson, (Type) obj);
    }
}
