package ca.cours5b5.charleslangevin.modeles;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.charleslangevin.controleurs.ControleurAction;
import ca.cours5b5.charleslangevin.controleurs.ControleurPartieReseau;
import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.charleslangevin.exceptions.ErreurAction;
import ca.cours5b5.charleslangevin.exceptions.ErreurSerialisation;
import ca.cours5b5.charleslangevin.global.GCommande;
import ca.cours5b5.charleslangevin.global.GConstantes;
import ca.cours5b5.charleslangevin.serialisation.AttributSerialisable;

public class MPartieReseau extends MPartie implements Fournisseur, Identifiable {

    @AttributSerialisable
    public String idJoueurInvite;
    private String __idJoueurInvite = GConstantes.CLE_ID_JOUEUR_INVITE;

    @AttributSerialisable
    public String idJoueurHote;
    private String __idJoueurHote = GConstantes.CLE_ID_JOUEUR_HOTE;

    public MPartieReseau(MParametresPartie parametres) {
        super(parametres);

        fournirActionPlacerJeton();
        fournirActionRecevoirCoup();
    }

    public String getId() {
        /*
         * utiliser l'id du joueur hôte
         */
        return idJoueurHote;
    }

    private void fournirActionRecevoirCoup() {
        ControleurAction.fournirAction(this,
                GCommande.RECEVOIR_COUP_RESEAU,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        //verifier
                        try{
                            int colonne = (Integer) args[0];
                            recevoirCoupReseau(colonne);
                        }catch(ClassCastException e){
                            throw new ErreurAction(e);
                        }
                    }
                });
    }

    @Override
    protected void fournirActionPlacerJeton() {
        /*
         * En plus de jouer le coup, le transmettre via
         *  le ControleurPartieReseau
         *
         *  ATTENTION au @Override. Le code qui fournit l'action
         *  PLACER_JETON dans la classe parent MPartie
         *  ne doit **pas** s'exécuter
         */

        ControleurAction.fournirAction(this,
                GCommande.JOUER_COUP_ICI,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        try{
                            int colonne = (Integer) args[0];
                            jouerCoup(colonne);
                            ControleurPartieReseau.getInstance().transmettreCoup(colonne);
                        }catch(ClassCastException e){
                            throw new ErreurAction(e);
                        }
                    }
                });
    }

    private void recevoirCoupReseau(int colonne){
        jouerCoup(colonne);
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        /*
         * charger les champs
         * appeler aussi super
         */
        Log.d("" ,"" + objetJson);
        super.aPartirObjetJson(objetJson);

        idJoueurInvite = (String) objetJson.get(__idJoueurInvite);
        idJoueurHote = (String) objetJson.get(__idJoueurHote);
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        /*
         * sauvegarder les champs
         * appeler aussi super
         */
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(__idJoueurInvite, idJoueurInvite);
        objetJson.put(__idJoueurHote, idJoueurHote);

        objetJson = super.enObjetJson();
        return objetJson;
    }
}
