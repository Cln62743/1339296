package ca.cours5b5.charleslangevin.modeles;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ca.cours5b5.charleslangevin.global.GConstantes;
import ca.cours5b5.charleslangevin.serialisation.AttributSerialisable;

public class MParametres extends Modele {

    // TODO: c'est temporaire; On va écrire un gestionnaire de modèle à l'atelier 07
    public static MParametres instance = new MParametres();

    @AttributSerialisable
    public Integer hauteur;
    private final String _hauteur = "hauteur";

    @AttributSerialisable
    public Integer largeur;
    private final String _largeur = "largeur";

    @AttributSerialisable
    public Integer pourGagner;
    private final String _pourGagner = "pourGagner";

    private List<Integer> choixHauteur;
    private List<Integer> choixLargeur;
    private List<Integer> choixPourGagner;

    public MParametres(){
        genererListesDeChoix();

        hauteur = GConstantes.defaultHeight;
        largeur = GConstantes.defaultWidth;
        pourGagner = GConstantes.defaultToWin;
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

    public int getHauteur(){ return hauteur; }
    public int getLargeur(){ return largeur; }
    public int getPourGagner(){ return pourGagner; }

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

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson){
        for(Map.Entry<String, Object> entry : objetJson.entrySet()) {
            String cle = entry.getKey();
            Object valeur = entry.getValue();

            if (cle == _hauteur) {
                instance.hauteur = (Integer) valeur;
            } else if (cle == _largeur){
                instance.largeur = (Integer) valeur;
            }else if (cle == _pourGagner){
                instance.pourGagner = (Integer) valeur;
            }
        }
    }

    @Override
    public Map<String, Object> enObjetJson(){
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(_hauteur, instance.hauteur);
        objetJson.put(_largeur, instance.largeur);
        objetJson.put(_pourGagner, instance.pourGagner);
        return objetJson;
    }
}
