package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.charleslangevin.modeles.MPartie;
import ca.cours5b5.charleslangevin.modeles.Modele;

public class VPartie implements Vue {
    /**
     * TODO modifier le .xml du layout pour que la classe VPartie soit utilisee
     */

    private VGrille grille;

    public VPartie(Context context){}

    public VPartie(Context context, AttributeSet attrs){}

    public VPartie(Context context, AttributeSet attrs, int defStyleAttr){}

    @Override
    protected void onFinishInflate(){}

    private void observerPartie(){
        /**
         * Appeler observer pour obtenir le modele
         *
         * Une fois le modele obtenu, creer la grille d'affichage
         *
         */
    }

    private MPartie getPartie(Modele modele){ return null; }

    private void initialiserGrille(MPartie partie){}


}
