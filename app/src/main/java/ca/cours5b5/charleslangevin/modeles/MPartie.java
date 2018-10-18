package ca.cours5b5.charleslangevin.modeles;

import android.util.Log;

import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.controleurs.ControleurAction;
import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.charleslangevin.exceptions.ErreurDeSerialisation;
import ca.cours5b5.charleslangevin.global.GCommande;
import ca.cours5b5.charleslangevin.global.GCouleur;
import ca.cours5b5.charleslangevin.serialisation.AttributSerialisable;
import ca.cours5b5.charleslangevin.vues.VPartie;

public class MPartie extends Modele implements Fournisseur{

    @AttributSerialisable
    public List<Integer> coups;
    private final String __coups = "coups";

    @AttributSerialisable
    public MParametresPartie parametres;
    private final String __parametres = "parametres";
    private MGrille grille;
    private GCouleur couleurCourante;

    static String classDebug;

    static {
        classDebug = MPartie.class.getSimpleName();
    }

    public MPartie(MParametresPartie parametres){
        //Log.i("Atelier07", classDebug + "::Constructeur");
        this.parametres = parametres;
        grille = new MGrille(parametres.getLargeur(), parametres.getHauteur());
        initialiserCouleurCourante();
        fournirActionPlacerJeton();
    }

    public MParametresPartie getParametres(){
        return parametres;
    }

    public MGrille getGrille() { return grille; }

    private void initialiserCouleurCourante(){
        couleurCourante = GCouleur.JAUNE;
    }

    /**
     * Appeler fournirAction
     */
    private void fournirActionPlacerJeton(){

        //Log.i("Atelier07", classDebug + "::fournirActionPlacerJeton");
        ControleurAction.fournirAction(this, GCommande.JOUER_COUP_ICI, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                //Log.i("Atelier07", classDebug + "::executer");
                jouerCoup((int) args[0]);
            }
        });
    }

    protected void jouerCoup(int colonne){

        //Log.i("Atelier07", classDebug + "::jouerCoup$" + colonne);
        grille.placerJeton(colonne, couleurCourante);
        prochaineCouleurCourante();
    }

    private void prochaineCouleurCourante(){
        if(couleurCourante.equals(GCouleur.ROUGE)){
            couleurCourante = GCouleur.JAUNE;
        }else{
            couleurCourante = GCouleur.ROUGE;
        }
    }

    /**
     * ATTENTION: Lire les parametresPartie d'abord
     *
     * - initialiser la grille
     *
     * - initialiser la couleur courante
     *
     * - lire la liste de coup
     *
     * - rejouer les coups
     */
    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurDeSerialisation{

    }

    /**
     * Inutilisee pour l'instant
     *
     */
    @Override
    public Map<String, Object> enObjetJson() throws ErreurDeSerialisation{
        return null;
    }

    private void rejouerLesCoups(List<Integer> coupsARejouer){}

    private List<Integer> listeCoupsAPartierJson(List<String> listeCoupsObjetJson){ return null; }

    private List<String> listeCoupsEnObjetJson(List<Integer> listeCoups){ return null; }

}
