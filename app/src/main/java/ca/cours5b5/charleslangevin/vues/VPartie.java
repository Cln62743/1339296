package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.TextView;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.controleurs.ControleurObservation;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.charleslangevin.modeles.MParametresPartie;
import ca.cours5b5.charleslangevin.modeles.MPartie;
import ca.cours5b5.charleslangevin.modeles.Modele;

public class VPartie extends ConstraintLayout implements Vue {
    /**
     * TODO modifier le .xml du layout pour que la classe VPartie soit utilisee
     */

    static String classDebug;

    static {

        classDebug = VParametres.class.getSimpleName();
        Log.i("Atelier06", classDebug + "::static");
    }

    private VGrille grille;

    public VPartie(Context context) {
        super(context);
    }

    public VPartie(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartie(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate(){
        Log.i("Atelier06", classDebug + "::onFinishInflate");
        super.onFinishInflate();

        TextView txtP1 = this.findViewById(R.id.txtPlayer1);
        TextView txtP2 = this.findViewById(R.id.txtPlayer2);

        txtP1.setText("Nom du joueur 1");
        txtP2.setText("Nom du joueur 2");

        grille = this.findViewById(R.id.idGrid);
    }

    private void observerPartie(){
        /**
         * Appeler observer pour obtenir le modele
         *
         * Une fois le modele obtenu, creer la grille d'affichage
         *
         */
        ControleurObservation.observerModele(MPartie.class.getSimpleName(),
                new ListenerObservateur() {
                    @Override
                    public void reagirNouveauModele(Modele modele){
                        MPartie mPartie = getPartie(modele);
                        initialiserGrille(mPartie);
                    }
                    @Override
                    public void reagirChangementAuModele(Modele modele) {
                    }
                });
    }

    private MPartie getPartie(Modele modele){

        return (MPartie)modele;
    }

    private void initialiserGrille(MPartie partie){
        MParametresPartie parametres = partie.getParametres();
        grille.creerGrille(parametres.getHauteur(), parametres.getLargeur());
    }


}
