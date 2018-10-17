package ca.cours5b5.charleslangevin.modeles;

import java.util.Map;

import ca.cours5b5.charleslangevin.exceptions.ErreurDeSerialisation;
import ca.cours5b5.charleslangevin.global.GCouleur;
import ca.cours5b5.charleslangevin.serialisation.AttributSerialisable;

public class MPartie extends Modele{

    @AttributSerialisable
    public MParametresPartie parametres;
    private final  String __parametres = "parametres";
    private MGrille grille;
    private GCouleur couleurCourante;

    public MPartie(MParametresPartie parametres){
        this.parametres = parametres;
    }

    public MParametresPartie getParametres(){
        return parametres;
    }

    public MGrille getGrille() { return grille; }

    private void initialiserCouleurCourante(){}

    private void fournirActionPlacerJeton(){
        /**
         * Appeler fournirAction
         *
         */
    }

    protected void jouerCoup(int colonne){
        /**
         *
         *
         */
    }

    private void prochaineCouleurCourante(){}

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurDeSerialisation{
        /**
         * Inutilisee pour l'instant
         *
         */
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurDeSerialisation{
        /**
         * Inutilisee pour l'instant
         *
         */
        return null;
    }

}
