package ca.cours5b5.charleslangevin.donnees;

import java.util.Map;


public abstract class SourceDeDonnees {

    public abstract Map<String, Object> chargerModele(final String cheminSauvegarde);

    public abstract void sauvegarderModele(final String cheminSauvegarde, final Map<String, Object> objetJson);

    //public abstract void detruireSauvegarde(String cheminSauvegarde);

    protected String getNomModele(String cheminSauvegarde){
        /*
        * Pour un chemin de sauvegarde de la forme nomModele/ID
        * retourner le nomModele
        */
        String nomModele = null;
        if(cheminSauvegarde != null){
            nomModele = cheminSauvegarde.split("/")[0];
        }
        return nomModele;
    }
}
