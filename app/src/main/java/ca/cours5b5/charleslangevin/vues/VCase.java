package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.global.GCouleur;

public class VCase extends AppCompatButton {
    public VCase(Context context) {
        super(context);
    }

    public VCase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VCase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Afficher la rangee et la colonne
     *
     * Change la couleur de fond si desire
     *
     */
    public VCase(Context context, int rangee, int colonne){

        super(context);
        setText(rangee + "," + colonne);
    }

    /**
     * Changer la couleur de fond selon
     * le jeton à afficher
     */
    public void afficherJeton(GCouleur jeton){

        if(jeton.equals(GCouleur.JAUNE)){
            this.setBackgroundColor(getResources().getColor(R.color.jaune, null));
        }else if(jeton.equals(GCouleur.ROUGE)){
            this.setBackgroundColor(getResources().getColor(R.color.rouge, null));
        }

    }
}
