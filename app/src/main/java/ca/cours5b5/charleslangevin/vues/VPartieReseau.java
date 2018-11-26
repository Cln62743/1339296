package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.controleurs.ControleurObservation;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.charleslangevin.exceptions.ErreurObservation;
import ca.cours5b5.charleslangevin.modeles.MParametresPartie;
import ca.cours5b5.charleslangevin.modeles.MPartie;
import ca.cours5b5.charleslangevin.modeles.MPartieReseau;
import ca.cours5b5.charleslangevin.modeles.Modele;


public class VPartieReseau extends VPartie {


    private VGrille grille;


    public VPartieReseau(Context context) {
        super(context);
    }

    public VPartieReseau(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartieReseau(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected String getNomModele(){
        return MPartieReseau.class.getSimpleName();
    }

}
