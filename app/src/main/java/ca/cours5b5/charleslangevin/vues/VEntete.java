package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import ca.cours5b5.charleslangevin.R;

public class VEntete extends AppCompatButton{
    public VEntete(Context context) {
        super(context);
    }

    public VEntete(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VEntete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int colonne;

    /**
     * Afficher le numero de colonne!
     */
    public VEntete(Context context, int colonne){

        super(context);
        this.colonne = colonne;
        this.setText(colonne + "");
        this.setBackgroundColor(getResources().getColor(R.color.bleuEntete, null));
        //this.setHighlightColor(getResources().getColor(R.color.bleuClickEntete, null));
    }
}
