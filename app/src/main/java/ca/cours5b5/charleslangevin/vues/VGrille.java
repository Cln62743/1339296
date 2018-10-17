package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
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

    private int nombreLignes;
    private class Colonne extends ArrayList<VCase> {}

    private List<Colonne> colonnesDeCases;
    private List<VEntete> entetes;

    static String classDebug;

    static {
        classDebug = VGrille.class.getSimpleName();
        Log.i("Atelier06", classDebug + "::static");
    }

    @Override
    protected void onFinishInflate(){
        Log.i("Atelier06",classDebug + "::onFinishInflate");
        super.onFinishInflate();
    }

    void creerGrille(int hauteur, int largeur){
        entetes = new ArrayList<>();
        colonnesDeCases = new ArrayList<>();
        nombreLignes = hauteur;

        Log.i("Atelier06","Hauteur:: " + hauteur);
        Log.i("Atelier06","Largeur:: " + largeur);

        initialiserColonnes(largeur);
        ajouterEnTetes(largeur);
        ajouterCases(hauteur, largeur);
    }

    private void initialiserColonnes(int largeur){
        for(int colonne = 0; colonne < largeur; colonne++) {
            Log.i("Atelier06","Nouvelle colonne num:: " + colonne);
            colonnesDeCases.add(new Colonne());
        }
    }

    private void ajouterEnTetes(int largeur){
        for(int colonne = 0; colonne < largeur; colonne++) {
            VEntete vEntete = new VEntete(getContext(), colonne);

            this.addView(vEntete, getMiseEnPageEntete(colonne));
            Log.i("Atelier06","Nouvelle entete num:: " + colonne);
            entetes.add(vEntete);
        }
    }

    private void ajouterCases(int hauteur, int largeur){
        for(int colonne = 0; colonne < largeur; colonne++) {
            Colonne objColonne = new Colonne();
            for(int ligne = hauteur; ligne > 0; ligne--) {

                VCase vCase = new VCase(getContext(), ligne - (2 * (ligne - hauteur)) - hauteur, colonne);

                this.addView(vCase, getMiseEnPageCase(ligne, colonne));
                Log.i("Atelier06","Nouvelle case colonne:: " + colonne + "& ligne:: " + ligne);
                objColonne.add(vCase);
            }
            colonnesDeCases.add(colonne, objColonne);
        }
    }

    private LayoutParams getMiseEnPageEntete(int colonne){

        Spec specRangee = GridLayout.spec(0, 2.0f);
        Spec specColonne = GridLayout.spec(colonne, 1.0f);
        LayoutParams layout = new LayoutParams(specRangee, specColonne);

        layout.width= 0;
        layout.height = 0;
        layout.setGravity(Gravity.FILL);

        layout.rightMargin = 1;
        layout.leftMargin = 1;

        return layout;
    }

    private LayoutParams getMiseEnPageCase(int ligne, int colonne){

        Spec specRangee = GridLayout.spec(ligne, 1.0f);
        Spec specColonne = GridLayout.spec(colonne, 1.0f);
        LayoutParams layout = new LayoutParams(specRangee, specColonne);

        layout.width= 0;
        layout.height = 0;
        layout.setGravity(Gravity.FILL);

        layout.rightMargin = 1;
        layout.leftMargin = 1;

        return layout;
    }
}
