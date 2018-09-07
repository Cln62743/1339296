package ca.cours5b5.charleslangevin.modeles;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.charleslangevin.donnees.Sauvegardable;
import ca.cours5b5.charleslangevin.global.GConstantes;
import ca.cours5b5.charleslangevin.serialisation.AttributSerialisable;

public class MParametres extends Modele implements Sauvegardable {

    // TODO: c'est temporaire; On va écrire un gestionnaire de modèle à l'atelier 07
    // TODO Finir la classe
    public  static MParametres instance;

    @AttributSerialisable
    public Integer hauteur;

    @AttributSerialisable
    public Integer largeur;

    @AttributSerialisable
    public Integer pourGagner;

    private List<Integer> choixHauteur;
    private List<Integer> choixLargeur;
    private List<Integer> choixPourGagner;

    public MParametres(){
        genererListesDeChoix();

        setHauteur(GConstantes.defaultHeight);
        setLargeur(GConstantes.defaultWidth);
        setPourGagner(GConstantes.defaultToWin);
    }

    public List<Integer> getChoixHauteur() {
        return choixHauteur;
    }

    public List<Integer> getChoixLargeur() {
        return choixLargeur;
    }

    public List<Integer> getChoixPourGagner() {
        return choixPourGagner;
    }

    public Integer getHauteur() {
        return hauteur;
    }

    public Integer getLargeur() {
        return largeur;
    }

    public Integer getPourGagner() {
        return pourGagner;
    }

    public void setHauteur(Integer hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(Integer largeur) {
        this.largeur = largeur;
    }

    public void setPourGagner(Integer pourGagner) {
        this.pourGagner = pourGagner;
    }

    private void genererListesDeChoix(){
        genererListeChoixHauteur();
        genererListeChoixLargeur();
        genererListeChoixPourGagner();
    }

    private List<Integer> genererListeChoix(int min, int max){
        List<Integer> listChoix = new ArrayList<>();
        for(int i = min; i<= max; i++) {
            listChoix.add(i);
        }
        return listChoix;
    }

    // Generation des choix
    private void genererListeChoixHauteur(){
        choixHauteur = genererListeChoix(GConstantes.minHeight, GConstantes.maxHeight);
    }

    private void genererListeChoixLargeur(){
        choixLargeur = genererListeChoix(GConstantes.minWidth, GConstantes.maxWidth);
    }

    private void genererListeChoixPourGagner(){
        choixPourGagner = genererListeChoix(GConstantes.minToWin, GConstantes.maxToWin);
    }
}
