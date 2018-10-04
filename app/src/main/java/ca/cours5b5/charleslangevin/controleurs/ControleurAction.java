package ca.cours5b5.charleslangevin.controleurs;

import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.charleslangevin.global.GCommande;

public class ControleurAction {

    private static Map<GCommande, Action> actions;
    private static List<Action> fileAttenteExec;

    static {
        /**
         * TRUC : initialiser le Map actions comme suit:
         *      - pour chaque GCommande
         *          - inserer une action vide
         *
         * (L'avante est qu'ensuite on a plus a tester si
         * une GCommande est dans le Map... elles y sont toutes!)
        */
    }

    public static Action demanderAction(GCommande commande){
        Action action = new Action();
        /**
         * Retourner l'action au demandeur
        */
        return action;
    }

    public static void fournirAction(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur){
        /**
         * Enregistrer le fournisseur
         * Appeler la methode qui execute chaque
         * action de la file d'attente (bonus: pourquoi?)
        */

    }

    static void executerDesQuePossible(Action action){
        /**
         * Mettre l'action en file d'attente
         * Appeler la methode qui execute chaque
         * action de la fille d'attente
         */
    }

    private static void executerActionsExecutables(){
        /**
         * Iterer la file d'attente
         *      si l'action est executable:
         *
         *          Avant d'executer l'action:
         *              - l'enlever de la file d'attente
         *
         *          Appeler la methode pour executer l'action maintenant
         *
         *          Apres avoir executer l'action:
         *              - Lancer l'observation du fournisseur de cette action(si possible)
         */
    }

    static boolean siActionExecutiable(Action action){
        /**
         * Une action est executable si:
         *      - le listenerFournisseur n'est pas null
         */
        return false;
    }

    private static synchronized void executerMaintenant(Action action){
        /**
         * Appeler le listenerFournisseur de l'action
         *
         * BONUS: a quoi sert le synchronized
         */
    }

    private static void lancerObservationSiApplicable(Action action){
        /**
         * Appeler le controleur pour lancer l'observation
         * du fournisseur (seulement si le fournisseur est un modele)
         */
    }

    private static void enregistrerFournisseur(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur){
        /**
         * Enregistrer le fournisseur et le listenerFournisseur dans l'action
         */
    }

    private static void ajouterActionEnFileDAttente(Action action){
        /**
         * Creer un clone de l'action et
         * ajouter le clone a la file d'attente
         */
    }
}
