package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.controleurs.ControleurObservation;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.charleslangevin.exceptions.ErreurObservation;
import ca.cours5b5.charleslangevin.modeles.MParametresPartie;
import ca.cours5b5.charleslangevin.modeles.MPartie;
import ca.cours5b5.charleslangevin.modeles.Modele;


public class VPartie extends Vue {

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
    protected void onFinishInflate() {
        Log.d("Atelier","VPartie::onFinishInflate");
        super.onFinishInflate();

        initialiser();
        observerPartie();
    }

    private void initialiser() {
        grille = findViewById(R.id.grille);
    }

    private void observerPartie() {
        String nomModele = getNomModele();

        ControleurObservation.observerModele(MPartie.class.getSimpleName(),
                new ListenerObservateur() {
                    @Override
                    public void reagirNouveauModele(Modele modele) {
                        Log.d("VueObservation","VPartie | reagirNouveauModele | " + modele.getClass().getSimpleName());

                        MPartie partie = getPartie(modele);
                        preparerAffichage(partie);
                        miseAJourGrille(partie);
                    }

                    @Override
                    public void reagirChangementAuModele(Modele modele) {
                        Log.d("VueObservation","VPartie | reagirChangementAuModele | " + modele.getClass().getSimpleName());

                        MPartie partie = getPartie(modele);
                        miseAJourGrille(partie);
                    }
                });
    }

    protected String getNomModele(){
        // TODO
        return null;
    }

    private void preparerAffichage(MPartie partie) {
        MParametresPartie parametresPartie = partie.getParametres();
        grille.creerGrille(parametresPartie.getHauteur(), parametresPartie.getLargeur());
    }

    private MPartie getPartie(Modele modele){
        try{
            return (MPartie) modele;

        }catch(ClassCastException e){
            throw new ErreurObservation(e);
        }
    }

    private void miseAJourGrille(MPartie partie){
        grille.afficherJetons(partie.getGrille());
    }

}
