package ca.cours5b5.charleslangevin.controleurs;

import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.charleslangevin.global.GCommande;
import ca.cours5b5.charleslangevin.modeles.Modele;

public class ControleurAction {

    private static Map<GCommande, Action> actions;
    private static List<Action> fileAttenteExec;

    static {
        /**
         * TRUC : initialiser le Map actions comme suit:
         *      - pour chaque GCommande
         *          - inserer une action vide
         *
         * (L'avantage est qu'ensuite on a plus a tester si
         * une GCommande est dans le Map... elles y sont toutes!)
        */

        for (Map.Entry<GCommande, Action> commande : actions.entrySet()) {
            Action action = new Action();
            actions.put(commande.getKey(), action);
        }
    }

    public static Action demanderAction(GCommande commande){
        /**
         * Retourner l'action au demandeur
        */
        Action action = actions.get(commande);
        return action;
    }

    public static void fournirAction(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur){
        /**
         * Enregistrer le fournisseur
         * Appeler la methode qui execute chaque
         * action de la file d'attente (bonus: pourquoi?)
         *
         * En enregistrant un fournisseur on rent l'action executable
         * donc en executant la methode l'action executable va etre executer.
        */
        enregistrerFournisseur(fournisseur, commande, listenerFournisseur);
        executerActionsExecutables();
    }

    static void executerDesQuePossible(Action action){
        /**
         * Mettre l'action en file d'attente
         * Appeler la methode qui execute chaque
         * action de la file d'attente
         */
        ajouterActionEnFileDAttente(action);
        executerActionsExecutables();
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
        for (Action action : fileAttenteExec) {
            if(siActionExecutable(action)){

                fileAttenteExec.remove(action);
                executerMaintenant(action);

                lancerObservationSiApplicable(action);
            }
        }

    }

    static boolean siActionExecutable(Action action){
        /**
         * Une action est executable si:
         *      - le listenerFournisseur n'est pas null
         */
        boolean result = false;
        if(action.listenerFournisseur != null){
            result = true;
        }
        return result;
    }

    private static synchronized void executerMaintenant(Action action){
        /**
         * Appeler le listenerFournisseur de l'action
         *
         * BONUS: a quoi sert le synchronized
         */
        action.listenerFournisseur.executer();
    }

    private static void lancerObservationSiApplicable(Action action){
        /**
         * Appeler le controleur pour lancer l'observation
         * du fournisseur (seulement si le fournisseur est un modele)
         */
        // TODO vérifier si la condition est bonne
        /*if(action.listenerFournisseur.getClass() == Modele.class){
            lancerObservationSiApplicable(action);
        }*/
    }

    private static void enregistrerFournisseur(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur){
        /**
         * Enregistrer le fournisseur et le listenerFournisseur dans l'action
         */
        Action actionTemp = actions.get(commande);
        actionTemp.fournisseur = fournisseur;
        actionTemp.listenerFournisseur = listenerFournisseur;

        actions.put(commande, actionTemp);
    }

    private static void ajouterActionEnFileDAttente(Action action){
        /**
         * Creer un clone de l'action et
         * ajouter le clone a la file d'attente
         */
        Action tempAction = action.cloner();
        fileAttenteExec.add(tempAction);
    }
}
