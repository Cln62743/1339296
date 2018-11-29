package ca.cours5b5.charleslangevin.activites;


import android.os.Bundle;
import android.util.Log;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.controleurs.ControleurAction;
import ca.cours5b5.charleslangevin.controleurs.ControleurModeles;
import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.charleslangevin.global.GCommande;
import ca.cours5b5.charleslangevin.modeles.MParametres;
import ca.cours5b5.charleslangevin.modeles.MPartie;
import ca.cours5b5.charleslangevin.modeles.MPartieIA;


public class AParametres extends Activite implements Fournisseur{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        fournirActions();
    }


    private void fournirActions() {
        ControleurAction.fournirAction(this,
                GCommande.EFFACER_PARTIE_COURANTE,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        ControleurModeles.detruireModele(MPartie.class.getSimpleName());
                    }
                });

        ControleurAction.fournirAction(this,
                GCommande.EFFACER_PARTIE_COURANTE_IA,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        ControleurModeles.detruireModele(MPartieIA.class.getSimpleName());
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        ControleurModeles.sauvegarderModele(MParametres.class.getSimpleName());
    }


}
