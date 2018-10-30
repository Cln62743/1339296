package ca.cours5b5.charleslangevin.donnees;

import android.os.Bundle;

import java.util.Map;


import ca.cours5b5.charleslangevin.serialisation.Jsonification;

public class SauvegardeTemporaire extends SourceDeDonnees {

    private Bundle bundle;

    public SauvegardeTemporaire(Bundle bundle){
        this.bundle = bundle;
    }

    @Override
    public Map<String, Object> chargerModele(String cheminSauvegarde) {
        /*
        * Utiliser getCle pour obtenir la cle de sauvegarde
        */
        if(bundle != null && bundle.containsKey(cheminSauvegarde)){
            String cle = getCle(cheminSauvegarde);

            String json = bundle.getString(cle);
            Map<String, Object> objetJson = Jsonification.aPartirChaineJson(json);

            return objetJson;
        }
        return null;
    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {
        /*
        * Utiliser getCle pour obtenir la cle de sauvegarde
        */
        if(bundle != null){
            String cle = getCle(cheminSauvegarde);

            String json = Jsonification.enChaineJson(objetJson);
            bundle.putString(cle, json);
        }
    }

    private String getCle(String cheminSauvegarde){
        /*
        * Utiliser le nomModele comme cle de sauvegarde
        * p.ex. MPartie/T1m8GxiBAlhLUcF6Ne0GV06nnEg1 => MPartie
        */
        String nomModele = null;
        if(cheminSauvegarde != null){
            nomModele = cheminSauvegarde.split("/")[0];
        }
        return nomModele;
    }
}