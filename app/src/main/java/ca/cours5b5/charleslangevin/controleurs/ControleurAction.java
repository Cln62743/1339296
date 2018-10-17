package ca.cours5b5.charleslangevin.controleurs;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.charleslangevin.global.GCommande;
import ca.cours5b5.charleslangevin.modeles.Modele;

public class ControleurAction {

    private static Map<GCommande, Action> actions;
    private static List<Action> fileAttenteExec;

    static final String classDebug;

    /**
     * TRUC : initialiser le Map actions comme suit:
     *      - pour chaque GCommande
     *          - inserer une action vide
     *
     * (L'avantage est qu'ensuite on a plus a tester si
     * une GCommande est dans le Map... elles y sont toutes!)
     */
    static {


        classDebug = ControleurAction.class.getSimpleName();

        actions = new HashMap<>();
        fileAttenteExec = new ArrayList<>();

        for (GCommande commande : GCommande.values()) {
            Action action = new Action();
            actions.put(commande, action);
        }
    }

    /**
     * Retourner l'action au demandeur
     */
    public static Action demanderAction(GCommande commande){

        return actions.get(commande);
    }

    /**
     * Enregistrer le fournisseur
     * Appeler la methode qui execute chaque
     * action de la file d'attente (bonus: pourquoi?)
     *
     * En enregistrant un fournisseur on rent l'action executable
     * donc en executant la methode, l'action executable va etre executer.
     */
    public static void fournirAction(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur){

        enregistrerFournisseur(fournisseur, commande, listenerFournisseur);
        executerActionsExecutables();
    }

    /**
     * Mettre l'action en file d'attente
     * Appeler la methode qui execute chaque
     * action de la file d'attente
     */
    static void executerDesQuePossible(Action action){

        //Log.i("Atelier07", classDebug + "::executerDesQuePossible");
        ajouterActionEnFileDAttente(action);
        executerActionsExecutables();
    }

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
    private static void executerActionsExecutables(){
        for (Action action : fileAttenteExec) {
            if(siActionExecutable(action)){
                //Log.i("Atelier07", classDebug + "::executerActionsExecutables");
                fileAttenteExec.remove(action);
                executerMaintenant(action);

                lancerObservationSiApplicable(action);
            }
        }

    }

    /**
     * Une action est executable si:
     *      - le listenerFournisseur n'est pas null
     */
    static boolean siActionExecutable(Action action){

        boolean result = false;
        if(action.listenerFournisseur != null){
            result = true;
        }
        return result;
    }

    /**
     * Appeler le listenerFournisseur de l'action
     *
     * BONUS: a quoi sert le synchronized
     */
    private static synchronized void executerMaintenant(Action action){

        //Log.i("Atelier07", classDebug + "::executerMaintenant");
        action.listenerFournisseur.executer(action.args);
    }

    /**
     * Appeler le controleur pour lancer l'observation
     * du fournisseur (seulement si le fournisseur est un modele)
     */
    private static void lancerObservationSiApplicable(Action action){

        if(action.fournisseur.getClass().equals(Modele.class)){
            ControleurObservation.lancerObservation((Modele)action.fournisseur);
        }
    }

    /**
     * Enregistrer le fournisseur et le listenerFournisseur dans l'action
     */
    private static void enregistrerFournisseur(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur){

        Action action = actions.get(commande);
        action.fournisseur = fournisseur;
        action.listenerFournisseur = listenerFournisseur;
    }

    /**
     * Creer un clone de l'action et
     * ajouter le clone a la file d'attente
     */
    private static void ajouterActionEnFileDAttente(Action action){
        Action tempAction = action.cloner();
        fileAttenteExec.add(tempAction);
    }
}
