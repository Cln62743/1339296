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
    public void chargerModele(final String cheminSauvegarde, final ListenerChargement listenerChargement) {
        if(bundle != null && bundle.containsKey(cheminSauvegarde)){
            String cle = getCle(cheminSauvegarde);

            String json = bundle.getString(cle);
            Map<String, Object> objetJson = Jsonification.aPartirChaineJson(json);

            listenerChargement.reagirSuccess(objetJson);
        }
        listenerChargement.reagirErreur(new Exception());
    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {
        if(bundle != null){
            String cle = getCle(cheminSauvegarde);

            String json = Jsonification.enChaineJson(objetJson);
            bundle.putString(cle, json);
        }
    }

    private String getCle(String cheminSauvegarde){
        String nomModele = null;
        if(cheminSauvegarde != null){
            nomModele = cheminSauvegarde.split("/")[0];
        }
        return nomModele;
    }
}