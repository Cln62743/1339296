package ca.cours5b5.charleslangevin.controleurs;

import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerFournisseur;

public class Action {

    Fournisseur fournisseur;
    ListenerFournisseur listenerFournisseur;

    Object[] args;

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
        ControleurAction controleurAction = new ControleurAction();
        controleurAction.executerDesQuePossible(this);
    }

    Action cloner(){
        /**
         *  Ne pas oublier de cloner le tableau!
         *  (a moins qu'il soit null)
         */
        Action action = new Action();

        if(this.args != null) {
            action.args = this.args.clone();
        }

        return action;
    }
}
