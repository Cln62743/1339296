package ca.cours5b5.charleslangevin.controleurs;

import android.util.Log;

import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerFournisseur;

public class Action {

    Fournisseur fournisseur;
    ListenerFournisseur listenerFournisseur;

    Object[] args;
    static final String classDebug;

    static {
        classDebug = Action.class.getSimpleName();
    }

    public void setArguments(Object... args){
        if(args != null){
            this.args = args;
        }
    }

    public void executerDesQuePossible(){
        /**
         *  Appeler le controleur.
         *  C'est au controleur de gerer l'action
         *  (Mettre en file d'attente, executer si possible, etc.)
        */
        //Log.i("Atelier07", classDebug + "::executerDesQuePossible");
        ControleurAction controleurAction = new ControleurAction();
        controleurAction.executerDesQuePossible(this);
    }

    Action cloner(){
        /**
         *  Ne pas oublier de cloner le tableau!
         *  (a moins qu'il soit null)
         */
        Action action = new Action();
        action.fournisseur = this.fournisseur;
        action.listenerFournisseur = this.listenerFournisseur;

        if(this.args != null) {
            action.args = this.args.clone();
        }

        return action;
    }
}
