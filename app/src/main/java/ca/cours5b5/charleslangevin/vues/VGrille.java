package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.charleslangevin.controleurs.Action;
import ca.cours5b5.charleslangevin.controleurs.ControleurAction;
import ca.cours5b5.charleslangevin.global.GCommande;
import ca.cours5b5.charleslangevin.global.GConstantes;
import ca.cours5b5.charleslangevin.global.GCouleur;
import ca.cours5b5.charleslangevin.modeles.MGrille;

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

    private VCase[][] lesCases;

    static String classDebug;
    private Action actionEntete;

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

        initialiserTableauDeCase(hauteur, largeur);
        initialiserColonnes(largeur);
        ajouterEnTetes(largeur);
        ajouterCases(hauteur, largeur);
        demanderActionEntete();
    }

    private void initialiserColonnes(int largeur){
        for(int colonne = 0; colonne < largeur; colonne++) {
            colonnesDeCases.add(new Colonne());
        }
    }

    private void initialiserTableauDeCase(int hauteur, int largeur){
        lesCases = new VCase[hauteur][largeur];
    }

    /**
     * On demande l'action JOUER_COUP_ICI
     *
     * l'action est à exécuter quand l'usager
     * clique sur une en-tête
     */
    private void demanderActionEntete(){

        actionEntete = ControleurAction.demanderAction(GCommande.JOUER_COUP_ICI);
    }

    private void ajouterEnTetes(int largeur){
        /*
         * Pour chaque en-tête, on veut
         * installer le listener
         */
        for(int colonne = 0; colonne < largeur; colonne++) {
            VEntete entete = new VEntete(getContext(), colonne);

            this.addView(entete, getMiseEnPageEntete(colonne));
            installerListenerEntete(entete, colonne);

            entetes.add(entete);
        }
    }

    private void installerListenerEntete(VEntete entete, final int colonne){
        entete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                demanderActionEntete();
                actionEntete.setArguments(colonne);
                //Log.i("Atelier07", classDebug + "::onClick$" + colonne);
                actionEntete.executerDesQuePossible();
            }
        });
    }

    private void ajouterCases(int hauteur, int largeur){
        for(int colonne = 0; colonne < largeur; colonne++) {
            Colonne objColonne = new Colonne();
            for(int ligne = hauteur; ligne > 0; ligne--) {

                VCase vCase = new VCase(getContext(), ligne - (2 * (ligne - hauteur)) - hauteur, colonne);

                this.addView(vCase, getMiseEnPageCase(ligne, colonne));
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

    private void afficherJetons(MGrille grille){ /*TODO*/ }

    private void afficherJeton(int colonne, int rangee, GCouleur jeton){
        this.lesCases[colonne][rangee].afficherJeton(jeton);
    }
}
