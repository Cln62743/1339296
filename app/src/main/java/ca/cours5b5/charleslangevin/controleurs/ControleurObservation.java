package ca.cours5b5.charleslangevin.controleurs;

import java.util.HashMap;
import java.util.Map;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.charleslangevin.exceptions.ErreurObservation;
import ca.cours5b5.charleslangevin.modeles.MParametres;
import ca.cours5b5.charleslangevin.modeles.MParametresPartie;
import ca.cours5b5.charleslangevin.modeles.MPartie;
import ca.cours5b5.charleslangevin.modeles.Modele;

public class ControleurObservation {

    private static Map<Modele, ListenerObservateur> observations;

    // L'attribut est private: la vue doit obtenir le modèle par l'observation
    private static MPartie partie;
    static String classDebug;

    static {
        classDebug = ControleurObservation.class.getSimpleName();
        observations = new HashMap<>();
    }

    /**
     * Enregistrer le listener dans le Map observations
     * Lancer l'observation une premiere fois quand on recoit le listener
     *
     * Note: pour l'instant, utiliser le nom pour decider quel modele utiliser
     *      - MParametres.instance ou ControleurObservation.partie
     *
     * BONUS: pourquoi le modele est identifie par son nom? (et pas son object comme dans le Map?)
     */
    public static void observerModele(String nomModele, final ListenerObservateur listenerObservateur) throws ErreurObservation{
        Modele modele = null;

        if(nomModele.equals("MPartie")){
            partie = new MPartie(MParametresPartie.aPartirMParametres(MParametres.instance));
            observations.put(ControleurObservation.partie, listenerObservateur);
            modele = ControleurObservation.partie;
        }else if(nomModele.equals("MParametres")){
            modele = MParametres.instance;
            observations.put(modele, listenerObservateur);
        }
        lancerObservation(modele);
    }

    /**
     * Verifier si le listener existe pour ce modele
     * Appeler le listener
     */
    public static void lancerObservation(Modele modele){

        ListenerObservateur listener = observations.get(modele);
        if(listener != null){
            listener.reagirNouveauModele(modele);
        }
    }
}
