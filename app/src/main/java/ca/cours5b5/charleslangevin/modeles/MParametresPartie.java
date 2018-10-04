package ca.cours5b5.charleslangevin.modeles;

import java.util.Map;

import ca.cours5b5.charleslangevin.exceptions.ErreurDeSerialisation;
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
        return null;
    }

    public MParametresPartie cloner(){
        /**
         * Retourne une instance de MParametresPartie avec
         * exactement les memes hauteur/largeur/pourGagner
         * que l'objet courant
         *
         */
        return null;
    }

    public MParametresPartie(){}

    // GET & SET
    public Integer getHauteur() { return hauteur; }
    public Integer getLargeur() { return largeur; }
    public Integer getPourGagner() { return pourGagner; }

    public void setHauteur(int hauteur){}
    public void setLargeur(int largeur){}
    public void setPourGagner(int pourGagner){}

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurDeSerialisation{

    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurDeSerialisation{
        return null;
    }
}
