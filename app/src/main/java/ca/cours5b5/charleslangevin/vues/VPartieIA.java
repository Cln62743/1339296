package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.util.AttributeSet;
import ca.cours5b5.charleslangevin.modeles.MPartieIA;

public class VPartieIA extends VPartie{

    private VGrille grille;

    public VPartieIA(Context context) {
        super(context);
    }

    public VPartieIA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartieIA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected String getNomModele(){
        return MPartieIA.class.getSimpleName();
    }
}
