package ca.cours5b5.charleslangevin.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

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
import ca.cours5b5.charleslangevin.global.GConstantes;

public class AMenuPrincipal extends Activite implements Fournisseur {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Atelier","AMenuPrincipal::onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        fournirActions();
    }

    private void fournirActions() {
        fournirActionOuvrirMenuParametres();
        fournirActionDemarrerPartie();
        fournirActionConnexion();
    }

    private void fournirActionOuvrirMenuParametres() {
        ControleurAction.fournirAction(this,
                GCommande.OUVRIR_MENU_PARAMETRES,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        transitionParametres();
                    }
                });
    }

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

    private void fournirActionConnexion() {
        ControleurAction.fournirAction(this,
                GCommande.CONNEXION,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        connexion();
                    }
                });
    }

    private void transitionParametres(){
        Intent intentionParametres = new Intent(this, AParametres.class);
        startActivity(intentionParametres);
    }

    private void transitionPartie(){
        Intent intentionParametres = new Intent(this, APartie.class);
        startActivity(intentionParametres);
    }

    private void connexion(){
        List<AuthUI.IdpConfig> fournisseursDeConnexion = new ArrayList<>();

        fournisseursDeConnexion.add(new AuthUI.IdpConfig.GoogleBuilder().build());
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.EmailBuilder().build());
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.PhoneBuilder().build());

        Intent intentionConnexion = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(fournisseursDeConnexion)
                .build();

        this.startActivityForResult(intentionConnexion, GConstantes.MA_CONSTANTE_CODE_CONNEXION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == GConstantes.MA_CONSTANTE_CODE_CONNEXION){
            if(resultCode == RESULT_OK){
                Log.d("Atelier11","AMenuPrincipal::Connexion reussie");
                // Connexion reussie
            }else{
                Log.d("Atelier11","AMenuPrincipal::Connexion echouee");
                // Connexion echouee
            }
        }
    }

    private void deconnexion(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("Atelier11","AMenuPrincipal::Deconnexion echouee");
                        // Deconnexion reussie
                    }
                });
    }
}
