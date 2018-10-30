package ca.cours5b5.charleslangevin.usagers;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class UsagerCourant {

    public static boolean siUsagerConnecte(){
        /*
        * Retourne vrai si l'usager est connecte
        * Utiliser FirebaseAuth
        */
        boolean result = false;
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            result = true;
        }
        return result;
    }

    public static String getId(){
        /*
         * Retourne l'identifiant de l'usager connecte
         * (ou un id par defaut)
         *
         * Utiliser FirebaseAuth
         */
        String uid = null;
        if(siUsagerConnecte()){
            uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        return uid;
    }
}
