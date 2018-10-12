package ca.cours5b5.charleslangevin.modeles;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.exceptions.ErreurDeSerialisation;
import ca.cours5b5.charleslangevin.global.GConstantes;
import ca.cours5b5.charleslangevin.serialisation.AttributSerialisable;

public class MParametres extends Modele {

    // TODO: c'est temporaire; On va écrire un gestionnaire de modèle à l'atelier 07
    public static MParametres instance = new MParametres();

    @AttributSerialisable
    public MParametresPartie parametresPartie;
    private String __parametrePartie = "parametresPartie";

    private List<Integer> choixHauteur;
    private List<Integer> choixLargeur;
    private List<Integer> choixPourGagner;

    public MParametres(){
        genererListesDeChoix();
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

    public MParametresPartie getParametresPartie() { return parametresPartie; }

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
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurDeSerialisation{
        for(Map.Entry<String, Object> entry : objetJson.entrySet()) {
            String cle = entry.getKey();
            Object valeur = entry.getValue();

            if (cle == parametresPartie.__hauteur) {
                parametresPartie.setHauteur((Integer) valeur);
            } else if (cle == parametresPartie.__largeur){
                parametresPartie.setLargeur((Integer) valeur);
            }else if (cle == parametresPartie.__pourGagner){
                parametresPartie.setPourGagner((Integer) valeur);
            }
        }
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurDeSerialisation{
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(parametresPartie.__hauteur, parametresPartie.getHauteur());
        objetJson.put(parametresPartie.__largeur, parametresPartie.getLargeur());
        objetJson.put(parametresPartie.__pourGagner, parametresPartie.getPourGagner());
        return objetJson;
    }
}
