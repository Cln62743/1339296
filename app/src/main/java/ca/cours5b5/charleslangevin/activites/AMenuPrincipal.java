package ca.cours5b5.charleslangevin.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.controleurs.ControleurAction;
import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.charleslangevin.global.GCommande;

import static ca.cours5b5.charleslangevin.global.GConstantes.CODE_CONNEXION_FIREBASE;

public class AMenuPrincipal extends Activite implements Fournisseur {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        fournirActions();
    }


    private void fournirActions() {
        fournirActionParametres();

        fournirActionConnexion();
        fournirActionDeconnexion();

        fournirActionDemarrerPartie();
        fournirActionDemarrerPartieContreIA();
        fournirActionJoindreOuCreerPartieReseau();
    }

    // Action Parametres
    private void fournirActionParametres() {
        ControleurAction.fournirAction(this,
                GCommande.OUVRIR_MENU_PARAMETRES,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        transitionParametres();
                    }
                });
    }

    // Action Connexion
    private void fournirActionConnexion() {
        ControleurAction.fournirAction(this,
                GCommande.CONNEXION,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        effectuerConnexion();
                    }
                });
    }

    private void fournirActionDeconnexion() {
        ControleurAction.fournirAction(this,
                GCommande.DECONNEXION,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        effectuerDeconnexion();
                    }
                });
    }

    // Action Parties
    private void fournirActionDemarrerPartie() {
        ControleurAction.fournirAction(this,
                GCommande.DEMARRER_PARTIE,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        transitionPartie();
                    }
                });
    }

    private void fournirActionDemarrerPartieContreIA() {
        ControleurAction.fournirAction(this,
                GCommande.DEMARRER_PARTIE_IA,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        transitionPartieIA();
                    }
                });
    }

    private void fournirActionJoindreOuCreerPartieReseau() {
        ControleurAction.fournirAction(this,
                GCommande.JOINDRE_OU_CREER_PARTIE_RESEAU,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                            transitionAttendreAdversaire();
                    }
                });
    }

    // Transition Parametres
    private void transitionParametres() {
        Intent intentionParametres = new Intent(this, AParametres.class);
        startActivity(intentionParametres);
    }

    // Transition Connexion
    private void effectuerConnexion() {
        List<AuthUI.IdpConfig> fournisseursDeConnexion = new ArrayList<>();

        fournisseursDeConnexion.add(new AuthUI.IdpConfig.GoogleBuilder().build());
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.EmailBuilder().build());
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.PhoneBuilder().build());

        Intent intentionConnexion = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(fournisseursDeConnexion)
                .build();

        this.startActivityForResult(intentionConnexion, CODE_CONNEXION_FIREBASE);
    }


    public void effectuerDeconnexion() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {

                        // Rien

                    }
                });
    }

    // Transition Parties
    private void transitionPartie() {
        Intent intentionPartie = new Intent(this, APartie.class);
        startActivity(intentionPartie);
    }

    private void transitionPartieIA() {
        // TODO est ce que je dois ajouter une classe activite pour l'IA
        Intent intentionPartieIA = new Intent(this, APartieIA.class);
        startActivity(intentionPartieIA);
    }

    private void transitionAttendreAdversaire() {
        Intent intentionAttendreAdversaire = new Intent(this, AEnAttenteAdversaire.class);
        startActivity(intentionAttendreAdversaire);
    }


    // Autre
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_CONNEXION_FIREBASE) {
            //IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                // Connexion réussie
            } else {
                // connexion échouée
            }
        }
    }
}
