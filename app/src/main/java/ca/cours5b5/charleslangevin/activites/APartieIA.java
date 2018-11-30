package ca.cours5b5.charleslangevin.activites;

import android.os.Bundle;
import android.util.Log;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.controleurs.Action;
import ca.cours5b5.charleslangevin.controleurs.ControleurAction;
import ca.cours5b5.charleslangevin.controleurs.ControleurModeles;
import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.charleslangevin.global.GCommande;
import ca.cours5b5.charleslangevin.modeles.MPartieIA;

public class APartieIA extends Activite implements Fournisseur {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie_ia);

        fournirActionTerminerPartie();
    }


    private void fournirActionTerminerPartie() {
        ControleurAction.fournirAction(this,
                GCommande.TERMINER_PARTIE_IA,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        quitterCetteActivite();

                        Action actionEffacerPartieIA = ControleurAction.demanderAction(GCommande.EFFACER_PARTIE_COURANTE_IA);
                        actionEffacerPartieIA.executerDesQuePossible();
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
        sauvegarderPartie();
    }


    protected void sauvegarderPartie(){
        ControleurModeles.sauvegarderModele(MPartieIA.class.getSimpleName());
    }
}
