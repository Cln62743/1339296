package ca.cours5b5.charleslangevin.donnees;

import java.util.Map;

public interface ListenerChargement {

    void reagirSuccess(Map<String, Object> objetJson);
    void reagirErreur(Exception e);
}
