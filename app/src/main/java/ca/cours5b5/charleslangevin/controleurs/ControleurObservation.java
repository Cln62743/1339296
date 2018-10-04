package ca.cours5b5.charleslangevin.controleurs;

import java.util.Map;

import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.charleslangevin.modeles.Modele;

public class ControleurObservation {

    private static Map<Modele, ListenerObservateur> observations;

    static {

    }

    public static void observerModele(String nomModele, final ListenerObservateur listenerObservateur){
        /**
         * Enregistrer le listener dans le Map observations
         * Lancer l'observation une premiere fois quand on recoit le listener
         *
         * Note: pour l'instant il y a un seul modele, le nomModele est ignore (FIXME atelier07!)
         *
         * BONUS: pourquoi le modele est identifie par son nom? (et pas son object comme dans le Map?)
         */
    }

    public static void lancerObservation(Modele modele){
        /**
         * Retrouver le listerObservateur pour ce
         * modele et l'appeler
         */
    }
}
