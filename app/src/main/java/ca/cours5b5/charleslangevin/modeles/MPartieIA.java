package ca.cours5b5.charleslangevin.modeles;

import java.util.Map;

import ca.cours5b5.charleslangevin.controleurs.ControleurAction;
import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.charleslangevin.exceptions.ErreurAction;
import ca.cours5b5.charleslangevin.exceptions.ErreurSerialisation;
import ca.cours5b5.charleslangevin.global.GCommande;
import ca.cours5b5.charleslangevin.global.GConstantes;
import ca.cours5b5.charleslangevin.serialisation.AttributSerialisable;

public class MPartieIA extends MPartie implements Fournisseur {

    @AttributSerialisable
    public String idJoueur;
    private String __idJoueur = GConstantes.CLE_ID_JOUEUR;

    private MParametresPartie parametresPartie;

    public MPartieIA(MParametresPartie parametres) {
        super(parametres);
        this.parametresPartie = parametres;
    }

    protected void fournirActionPlacerJeton() {
        ControleurAction.fournirAction(this,
                GCommande.PLACER_JETON_ICI,
                new ListenerFournisseur() {

                    @Override
                    public void executer(Object... args) {
                        try {
                            int colonne = (Integer) args[0];
                            jouerCoup(colonne);

                            AiJouerCoupAlea();

                        } catch (ClassCastException e) {
                            throw new ErreurAction(e);
                        }
                    }
                });
    }

    public void AiJouerCoupAlea(){
        int colonneCoup = (int)(Math.random() * parametresPartie.getLargeur()) - 1;

        if(super.siCoupLegal(colonneCoup)){
            super.jouerCoupLegal(colonneCoup);
        }
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        super.aPartirObjetJson(objetJson);

        idJoueur = (String) objetJson.get(__idJoueur);
        //idJoueurInvite = (String) objetJson.get(__idJoueurInvite);
    }

    @Override
    public Map<String, Object> enObjetJson() {
        Map<String, Object> objetJson = super.enObjetJson();

        objetJson.put(__idJoueur, idJoueur);
        //objetJson.put(__idJoueurInvite, idJoueurInvite);

        return objetJson;
    }
}
