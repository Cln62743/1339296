package ca.cours5b5.charleslangevin.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ca.cours5b5.charleslangevin.donnees.SauvegardeTemporaire;
import ca.cours5b5.charleslangevin.donnees.Transition;
import ca.cours5b5.charleslangevin.controleurs.ControleurModeles;
import ca.cours5b5.charleslangevin.donnees.Disque;
import ca.cours5b5.charleslangevin.donnees.Serveur;
import ca.cours5b5.charleslangevin.modeles.MParametres;


public abstract class Activite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialiserControleurModeles(savedInstanceState);

        initialiserApplication();

        prechargerLesModeles();

    }


    private void prechargerLesModeles() {

        ControleurModeles.prechargerModele(MParametres.class.getSimpleName());

    }


    protected void initialiserControleurModeles(Bundle savedInstanceState) {

        ControleurModeles.setSequenceDeChargement(
                new SauvegardeTemporaire(savedInstanceState),
                new Transition(getIntent().getExtras()),
                Serveur.getInstance(),
                Disque.getInstance());

    }


    protected void initialiserApplication(){

        Disque.getInstance().setRepertoireRacine(getFilesDir());

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ControleurModeles.sauvegarderModeleDansCetteSource(MParametres.class.getSimpleName(),
                new SauvegardeTemporaire(outState));

    }


    protected void quitterCetteActivite(){
        finish();
    }

}