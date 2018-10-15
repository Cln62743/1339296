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

    private int nombreRangees;
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
        initialiserColonnes(largeur);
        ajouterEnEntetes(largeur);
        ajouterCases(hauteur, largeur);
    }

    private void initialiserColonnes(int largeur){
        for(int i = 0; i < largeur; i++) {
            colonnesDeCases.add(new Colonne());
        }
    }

    private void ajouterEnEntetes(int largeur){
        for(int i = 0; i < largeur; i++) {

            VEntete vEntete = new VEntete(getContext(), i);

            Spec specRangee = GridLayout.spec(0, 1);
            Spec specColonne = GridLayout.spec(i, 1);
            LayoutParams layout = new LayoutParams(specRangee, specColonne);

            layout.width= 0;
            layout.height = 0;
            layout.setGravity(Gravity.FILL);

            layout.rightMargin = 5;
            layout.leftMargin = 5;

            vEntete.setLayoutParams(layout);
            this.addView(vEntete, layout);
            entetes.add(new VEntete(getContext(), i));
        }
    }

    private void ajouterCases(int hauteur, int largeur){
        for(int i = 0; i < largeur; i++) {
            Colonne colonne = new Colonne();
            for(int j = 0; i < hauteur; j++) {
                VCase vCase = new VCase(getContext(), j, i);

                Spec specRangee = GridLayout.spec(j, 1);
                Spec specColonne = GridLayout.spec(i, 1);
                LayoutParams layout = new LayoutParams(specRangee, specColonne);

                layout.width= 0;
                layout.height = 0;
                layout.setGravity(Gravity.FILL);

                layout.rightMargin = 5;
                layout.leftMargin = 5;

                vCase.setLayoutParams(layout);
                this.addView(vCase, layout);
                colonne.add(vCase);
            }
            colonnesDeCases.set(i, colonne);
        }
    }

    private LayoutParams getMiseEnPageEntete(int colonne){
        return (LayoutParams) entetes.get(colonne).getLayoutParams();
    }

    private LayoutParams getMiseEnPageCase(int rangee, int colonne){
        return (LayoutParams) colonnesDeCases.get(rangee).get(colonne).getLayoutParams();
    }
}
