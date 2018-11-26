package ca.cours5b5.charleslangevin.modeles;


import java.util.Map;
import ca.cours5b5.charleslangevin.controleurs.ControleurAction;
import ca.cours5b5.charleslangevin.controleurs.ControleurPartieReseau;
import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.charleslangevin.exceptions.ErreurAction;
import ca.cours5b5.charleslangevin.exceptions.ErreurSerialisation;
import ca.cours5b5.charleslangevin.global.GCommande;
import ca.cours5b5.charleslangevin.global.GConstantes;
import ca.cours5b5.charleslangevin.global.GCouleur;
import ca.cours5b5.charleslangevin.serialisation.AttributSerialisable;
import ca.cours5b5.charleslangevin.usagers.UsagerCourant;

public class MPartieReseau extends MPartie implements Fournisseur, Identifiable {

    @AttributSerialisable
    public String idJoueurInvite;
    private String __idJoueurInvite = GConstantes.CLE_ID_JOUEUR_INVITE;

    @AttributSerialisable
    public String idJoueurHote;
    private String __idJoueurHote = GConstantes.CLE_ID_JOUEUR_HOTE;

    public MPartieReseau(MParametresPartie parametres) {
        super(parametres);

        fournirActionRecevoirCoup();
    }


    private void fournirActionRecevoirCoup() {
        ControleurAction.fournirAction(this,
                GCommande.RECEVOIR_COUP_RESEAU,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        String idColonne = (String) args[0];
                        recevoirCoupReseau(Integer.valueOf(idColonne));

                    }
                });
    }


    @Override
    protected void fournirActionPlacerJeton() {
        ControleurAction.fournirAction(this,
                GCommande.PLACER_JETON_ICI,
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
        if(super.siCoupLegal(colonne)){
            super.jouerCoupLegal(colonne);
        }
    }

    
    @Override
    protected boolean siCoupLegal(int colonne) { return super.siCoupLegal(colonne); }


    public void setIdJoueurs(String idJoueurHote, String idJoueurInvite){
        this.idJoueurHote = idJoueurHote;
        this.idJoueurInvite = idJoueurInvite;
    }


    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        super.aPartirObjetJson(objetJson);

        idJoueurHote = (String) objetJson.get(__idJoueurHote);
        idJoueurInvite = (String) objetJson.get(__idJoueurInvite);
    }


    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        Map<String, Object> objetJson = super.enObjetJson();

        objetJson.put(__idJoueurHote, idJoueurHote);
        objetJson.put(__idJoueurInvite, idJoueurInvite);

        return objetJson;
    }


    public String getId() {
        return idJoueurHote;
    }
}
