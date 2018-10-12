package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class VGrille extends GridLayout {
    public VGrille(Context context) {
        super(context);
    }

    public VGrille(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VGrille(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int nombreRangees;
    private class Colonne extends ArrayList<VCase> {}

    private List<Colonne> colonnesDeCases;
    private List<VEntete> entetes;

    @Override
    protected void onFinishInflate(){
        Log.i("Atelier06","VGrille::onFinishInflate");
        super.onFinishInflate();
    }

    void creerGrille(int hauteur, int largeur){}

    private void initialiserColonnes(int largeur){}

    private void ajouterEnEntetes(int largeur){}
    private void ajouterCases(int hauteur, int largeur){}

    private LayoutParams getMiseEnPageEntete(int colonne){ return null; }
    private LayoutParams getMiseEnPageCase(int rangee, int colonne){ return null;}
}
