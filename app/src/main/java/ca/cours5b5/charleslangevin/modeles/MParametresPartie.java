package ca.cours5b5.charleslangevin.modeles;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.charleslangevin.exceptions.ErreurDeSerialisation;
import ca.cours5b5.charleslangevin.global.GConstantes;
import ca.cours5b5.charleslangevin.serialisation.AttributSerialisable;

public class MParametresPartie extends Modele {

    @AttributSerialisable
    public Integer hauteur;
    protected final String __hauteur = "hauteur";

    @AttributSerialisable
    public Integer largeur;
    protected final String __largeur = "largeur";

    @AttributSerialisable
    public Integer pourGagner;
    protected final String __pourGagner = "pourGagner";

    public static MParametresPartie aPartirMParametres(MParametres mParametres){
        /**
         * Retourne une instance de MParametresPartie avec
         * les memes parametres partie que mParametres
         *
         * TRUC: utiliser cloner() ci-dessous
         *
         */
        MParametresPartie mParametresPartie = mParametres.getParametresPartie().cloner();
        return mParametresPartie;
    }

    public MParametresPartie cloner(){
        /**
         * Retourne une instance de MParametresPartie avec
         * exactement les memes hauteur/largeur/pourGagner
         * que l'objet courant
         *
         */
        MParametresPartie mParametresPartie = this;
        return mParametresPartie;
    }

    public MParametresPartie(){
        hauteur = GConstantes.defaultHeight;
        largeur = GConstantes.defaultWidth;
        pourGagner = GConstantes.defaultToWin;
    }

    // GET & SET
    public Integer getHauteur() { return hauteur; }
    public Integer getLargeur() { return largeur; }
    public Integer getPourGagner() { return pourGagner; }

    public void setHauteur(int hauteur){ this.hauteur = hauteur; }
    public void setLargeur(int largeur){ this.largeur = largeur; }
    public void setPourGagner(int pourGagner){ this.pourGagner = pourGagner; }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurDeSerialisation{
        for(Map.Entry<String, Object> entry : objetJson.entrySet()) {
            String cle = entry.getKey();
            Object valeur = entry.getValue();

            if (cle == this.__hauteur) {
                this.setHauteur((Integer) valeur);
            } else if (cle == this.__largeur){
                this.setLargeur((Integer) valeur);
            }else if (cle == this.__pourGagner){
                this.setPourGagner((Integer) valeur);
            }
        }
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurDeSerialisation{
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(this.__hauteur, this.hauteur);
        objetJson.put(this.__largeur, this.largeur);
        objetJson.put(this.__pourGagner, this.pourGagner);
        return objetJson;
    }
}
