package ca.cours5b5.charleslangevin.controleurs;

import java.util.Map;

import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.charleslangevin.exceptions.ErreurObservation;
import ca.cours5b5.charleslangevin.modeles.Modele;

public class ControleurObservation {

    private static Map<Modele, ListenerObservateur> observations;

    // L'attribut est private: la vue doit obtenir le modèle par l'observation
    static {

    }

    public static void observerModele(String nomModele, final ListenerObservateur listenerObservateur) throws ErreurObservation{
        /**
         * Enregistrer le listener dans le Map observations
         * Lancer l'observation une premiere fois quand on recoit le listener
         *
         * Note: pour l'instant, utiliser le nom pour decider quel modele utiliser
         *      - MParametres.instance ou ControleurObservation.partie
         *
         * BONUS: pourquoi le modele est identifie par son nom? (et pas son object comme dans le Map?)
         */
        Modele modele = null;
        try {
            modele = (Modele)Class.forName(nomModele).newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }

        if(modele != null)
        {
            observations.put(modele, listenerObservateur);
            lancerObservation(modele);
        }
    }

    public static void lancerObservation(Modele modele){
        /**
         * Retrouver le listenerObservateur pour ce
         * modele et l'appeler
         */
        ListenerObservateur listener = observations.get(modele);
        listener.reagirNouveauModele(modele);
    }
}
