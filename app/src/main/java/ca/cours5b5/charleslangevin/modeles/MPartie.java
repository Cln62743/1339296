package ca.cours5b5.charleslangevin.modeles;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.controleurs.ControleurAction;
import ca.cours5b5.charleslangevin.controleurs.ControleurPartie;
import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.charleslangevin.exceptions.ErreurAction;
import ca.cours5b5.charleslangevin.exceptions.ErreurSerialisation;
import ca.cours5b5.charleslangevin.global.GCommande;
import ca.cours5b5.charleslangevin.global.GCouleur;
import ca.cours5b5.charleslangevin.serialisation.AttributSerialisable;

public class MPartie extends Modele implements Fournisseur {

    @AttributSerialisable
    public MParametresPartie parametres;
    protected final String __parametres = "parametres";

    @AttributSerialisable
    public List<Integer> listeCoups;
    private final String __listeCoups = "listeCoups";

    protected MGrille grille;
    private GCouleur couleurCourante;

    public MPartie(MParametresPartie parametres) {
        this.parametres = parametres;

        initialiser();
        initialiserCouleurCourante();
        initialiserGrille();

        fournirActionPlacerJeton();
    }

    protected void initialiser() {
        listeCoups = new ArrayList<>();
    }

    protected void initialiserCouleurCourante() {
        couleurCourante = GCouleur.ROUGE;
    }

    protected void initialiserGrille() {
        grille = new MGrille(parametres.getLargeur());
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

                        } catch (ClassCastException e) {
                            throw new ErreurAction(e);
                        }
                    }
                });
    }


    protected void jouerCoup(int colonne) {
        if (siCoupLegal(colonne)) {
            ajouterCoupListe(colonne);
        }
    }

    protected void ajouterCoupListe(int colonne){
        listeCoups.add(colonne);
        jouerCoupLegal(colonne);
    }

    protected void jouerCoupLegal(int colonne) {
        grille.placerJeton(colonne, couleurCourante);
        
        if (grille.siCouleurGagne(couleurCourante, parametres.getPourGagner())) {
            appelGagnerPartie(couleurCourante);
        } else {
            prochaineCouleurCourante();
        }
    }

    protected void appelGagnerPartie(GCouleur couleur) {
        ControleurPartie.getInstance().gagnerPartie(couleur);
    }

    protected boolean siCoupLegal(int colonne) {
        //Log.d("ProjetFinal", "Size: " + grille.getColonnes().size() + "     Colonne: " + colonne);
        MColonne mColonne = grille.getColonnes().get(colonne);

        return mColonne.getJetons().size() < parametres.getHauteur();
    }

    private void prochaineCouleurCourante() {
        switch (couleurCourante) {
            case ROUGE:
                couleurCourante = GCouleur.JAUNE;
                break;
            case JAUNE:
                couleurCourante = GCouleur.ROUGE;
        }
    }

    public MGrille getGrille() {
        return grille;
    }

    public MParametresPartie getParametres() {
        return parametres;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {

        parametres.aPartirObjetJson((Map<String, Object>) objetJson.get(__parametres));
        initialiserCouleurCourante();
        initialiserGrille();

        List<String> listeCoupsObjetJson = (List<String>) objetJson.get(__listeCoups);

        if (listeCoupsObjetJson != null) {
            List<Integer> coupsARejouer = listeCoupsAPartirJson(listeCoupsObjetJson);
            rejouerLesCoups(coupsARejouer);
        }
    }

    protected List<Integer> listeCoupsAPartirJson(List<String> listeCoupsObjetJson) {
        List<Integer> listeCoups = new ArrayList<>();

        for (String coupChaine : listeCoupsObjetJson) {
            listeCoups.add(Integer.valueOf(coupChaine));
        }
        return listeCoups;
    }

    private void rejouerLesCoups(List<Integer> coupsARejouer) {
        listeCoups.clear();

        for(Integer coup : coupsARejouer){
            jouerCoup(coup);
        }
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(__parametres, parametres.enObjetJson());
        objetJson.put(__listeCoups, listeCoupsEnObjetJson(listeCoups));

        return objetJson;
    }

    protected List<String> listeCoupsEnObjetJson(List<Integer> listeCoups) {
        List<String> listeCoupsObjetJson = new ArrayList<>();

        for (Integer coup : listeCoups) {
            listeCoupsObjetJson.add(coup.toString());
        }
        return listeCoupsObjetJson;
    }

    public GCouleur getCouleurCourante() {
        return couleurCourante;
    }
}
