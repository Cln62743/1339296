package ca.cours5b5.charleslangevin.modeles;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @AttributSerialisable
    public List<Integer> listeCoupsJoueur;
    private final String __listeCoupsJoueur = "listeCoupsJoueur";

    @AttributSerialisable
    public List<Integer> listeCoupsIA;
    private final String __listeCoupsIA = "listeCoupsIA";

    @AttributSerialisable
    private boolean playerIsWinning;
    private final String __playerIsWinning = "playerIsWinning";

    private boolean playerIsPlaying = false;
    private MParametresPartie parametresPartie;

    public MPartieIA(MParametresPartie parametres) {
        super(parametres);
        this.parametresPartie = parametres;
    }

    @Override
    protected void initialiser() {
        listeCoupsJoueur = new ArrayList<>();
        listeCoupsIA = new ArrayList<>();
    }

    @Override
    protected void initialiserGrille() {
        grille = new MGrilleIA(parametres.getLargeur());
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
                            playerIsWinning = ((MGrilleIA) grille).evaluerGagne();
                        } catch (ClassCastException e) {
                            throw new ErreurAction(e);
                        }
                    }
                });
    }

    @Override
    protected void ajouterCoupListe(int colonne) {
        if (playerIsPlaying) {
            listeCoupsJoueur.add(colonne);
            playerIsPlaying = false;
        } else {
            listeCoupsIA.add(colonne);
            playerIsPlaying = true;
        }

        jouerCoupLegal(colonne);
    }

    public void AiJouerCoupAlea(){
        int colonneCoup = (int)(Math.random() * parametresPartie.getLargeur()) - 1;
        jouerCoup(colonneCoup);
    }

    // A partir Objet Json
    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        parametres.aPartirObjetJson((Map<String, Object>) objetJson.get(__parametres));
        initialiserCouleurCourante();
        initialiserGrille();

        List<String> listeCoupsJoueurObjetJson = (List<String>) objetJson.get(__listeCoupsJoueur);
        List<String> listeCoupsIAObjetJson = (List<String>) objetJson.get(__listeCoupsIA);

        List<Integer> coupsJoueurRejouer = null;
        List<Integer> coupsIARejouer = null;


        if (listeCoupsJoueurObjetJson != null) {
            coupsJoueurRejouer = listeCoupsAPartirJson(listeCoupsJoueurObjetJson);
        }
        if (listeCoupsIAObjetJson != null) {
            coupsIARejouer = listeCoupsAPartirJson(listeCoupsIAObjetJson);
        }

        if(coupsJoueurRejouer != null && coupsIARejouer != null){
            rejouerLesCoups(coupsJoueurRejouer, coupsIARejouer);
        }
        idJoueur = (String) objetJson.get(__idJoueur);
        playerIsWinning = (boolean) objetJson.get(__playerIsWinning);
    }

    private void rejouerLesCoups(List<Integer> coupsJoueur, List<Integer> coupsIA) {
        listeCoupsJoueur.clear();
        listeCoupsIA.clear();

        //Log.d("ProjetFinal", "coupsJoueur Size" + coupsJoueur.size());
        //Log.d("ProjetFinal", "coupsIA Size" + coupsIA.size());

        for(Integer coupJoueur : coupsJoueur){
            //Log.d("ProjetFinal", "coupJoueur : " + coupJoueur);
            jouerCoup(coupJoueur);

            Integer coupIA = coupsIA.remove(0);
            //Log.d("ProjetFinal", "coupIA : " + coupIA);
            jouerCoup(coupIA);
        }
    }


    // En Objet Json
    @Override
    public Map<String, Object> enObjetJson() {
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(__parametres, parametres.enObjetJson());

        objetJson.put(__listeCoupsJoueur, listeCoupsEnObjetJson(listeCoupsJoueur));
        objetJson.put(__listeCoupsIA, listeCoupsEnObjetJson(listeCoupsIA));

        objetJson.put(__playerIsWinning, playerIsWinning);

        Log.d("ProjetFinal", objetJson.toString());
        return objetJson;
    }
}
