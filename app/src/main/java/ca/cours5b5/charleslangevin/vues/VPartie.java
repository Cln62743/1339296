package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.charleslangevin.controleurs.ControleurObservation;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.charleslangevin.modeles.MParametresPartie;
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
    protected void onFinishInflate(){
        super.onFinishInflate();
    }

    private void observerPartie(){
        /**
         * Appeler observer pour obtenir le modele
         *
         * Une fois le modele obtenu, creer la grille d'affichage
         *
         */
        MPartie mPartie = null;

        ControleurObservation.observerModele(MPartie.class.getSimpleName(),
                new ListenerObservateur() {

                    @Override
                    public void reagirChangementAuModele(Modele modele) {
                    }
                });

        initialiserGrille(mPartie);
    }

    private MPartie getPartie(Modele modele){

        return null;
    }

    private void initialiserGrille(MPartie partie){
        MParametresPartie parametres = partie.getParametres();
        grille.creerGrille(parametres.getHauteur(), parametres.getLargeur());
    }


}
