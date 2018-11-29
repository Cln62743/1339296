package ca.cours5b5.charleslangevin.controleurs;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.charleslangevin.donnees.ListenerChargement;
import ca.cours5b5.charleslangevin.donnees.Serveur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.donnees.SourceDeDonnees;
import ca.cours5b5.charleslangevin.exceptions.ErreurModele;
import ca.cours5b5.charleslangevin.global.GConstantes;
import ca.cours5b5.charleslangevin.modeles.Identifiable;
import ca.cours5b5.charleslangevin.modeles.MParametres;
import ca.cours5b5.charleslangevin.modeles.MPartie;
import ca.cours5b5.charleslangevin.modeles.MPartieIA;
import ca.cours5b5.charleslangevin.modeles.MPartieReseau;
import ca.cours5b5.charleslangevin.modeles.Modele;
import ca.cours5b5.charleslangevin.donnees.Disque;
import ca.cours5b5.charleslangevin.usagers.UsagerCourant;

public final class ControleurModeles {

    private ControleurModeles(){}

    private static Map<String, Modele> modelesEnMemoire;
    private static SourceDeDonnees[] sequenceDeChargement;
    private static List<SourceDeDonnees> listeDeSauvegardes;

    static {
        modelesEnMemoire = new HashMap<>();

        listeDeSauvegardes = new ArrayList<>();
        listeDeSauvegardes.add(Disque.getInstance());
        listeDeSauvegardes.add(Serveur.getInstance());
    }


    public static void setSequenceDeChargement(SourceDeDonnees... sequenceDeChargement){
        ControleurModeles.sequenceDeChargement = sequenceDeChargement;
    }


    public static void sauvegarderModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {
        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){
            String cheminSauvegarde = getCheminSauvegarde(nomModele);
            Map<String, Object> objetJson = modele.enObjetJson();

            sourceDeDonnees.sauvegarderModele(cheminSauvegarde, objetJson);
        }
    }


    public static void prechargerModele(String nomModele) {

        getModele(nomModele, new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {
                // Rien
            }
        });
    }


    static void getModele(String nomModele, ListenerGetModele listenerGetModele){
        //Log.d("ProjetFinal","ControleurModeles :: getModele :: " + nomModele);
        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){
            listenerGetModele.reagirAuModele(modele);

        }else {
            creerModeleEtChargerDonnees(nomModele, listenerGetModele);
        }
    }


    private static void creerModeleEtChargerDonnees(final String nomModele,
                                                    final ListenerGetModele listenerGetModele) {
        //Log.d("ProjetFinal","ControleurModeles :: creerModeleEtChargerDonnees :: " + nomModele);
        creerModeleSelonNom(nomModele, new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {
                modelesEnMemoire.put(nomModele, modele);

                chargerDonnees(modele, nomModele, listenerGetModele);
            }
        });
    }

    private static void chargerDonnees(Modele modele,
                                       String nomModele,
                                       ListenerGetModele listenerGetModele) {
        //Log.d("ProjetFinal","ControleurModeles :: chargerDonnees :: " + nomModele);
        String cheminSauvegarde = getCheminSauvegarde(nomModele);
        int indicePremiereSource = 0;

        chargementViaSequence(
                modele,
                cheminSauvegarde,
                listenerGetModele,
                indicePremiereSource);
    }


    private static void chargementViaSequence(Modele modele,
                                              String cheminDeSauvegarde,
                                              ListenerGetModele listenerGetModele,
                                              int indiceSourceCourante){
        //Log.d("ProjetFinal","ControleurModeles :: chargementViaSequence");
        if(indiceSourceCourante < sequenceDeChargement.length){
            chargementViaSourceCouranteOuSuivante(modele,
                    cheminDeSauvegarde,
                    listenerGetModele,
                    indiceSourceCourante);

        }else{
            terminerChargement(modele, listenerGetModele);
        }
    }


    private static void chargementViaSourceCouranteOuSuivante(final Modele modele,
                                                              final String cheminDeSauvegarde,
                                                              final ListenerGetModele listenerGetModele,
                                                              final int indiceSourceCourante) {
        //Log.d("ProjetFinal","ControleurModeles :: chargementViaSourceCouranteOuSuivante");
        SourceDeDonnees sourceCourante = sequenceDeChargement[indiceSourceCourante];

        sourceCourante.chargerModele(cheminDeSauvegarde,
                new ListenerChargement() {

                    @Override
                    public void reagirSucces(final Map<String, Object> objetJson) {
                        terminerChargementAvecDonnees(objetJson, modele, listenerGetModele);
                    }

                    @Override
                    public void reagirErreur(Exception e) {
                        chargementViaSourceSuivante(modele,
                                cheminDeSauvegarde,
                                listenerGetModele,
                                indiceSourceCourante);
                    }
                });
    }


    private static void terminerChargementAvecDonnees(Map<String, Object> objetJson,
                                                      Modele modele,
                                                      ListenerGetModele listenerGetModele) {
        //Log.d("ProjetFinal","ControleurModeles :: terminerChargementAvecDonnees");
        modele.aPartirObjetJson(objetJson);

        terminerChargement(modele, listenerGetModele);
    }

    private static void terminerChargement(Modele modele,
                                           ListenerGetModele listenerGetModele) {
        //Log.d("ProjetFinal","ControleurModeles :: terminerChargement");
        listenerGetModele.reagirAuModele(modele);
    }


    private static void chargementViaSourceSuivante(Modele modele,
                                                    String cheminDeSauvegarde,
                                                    ListenerGetModele listenerGetModele,
                                                    int indiceSourceCourante) {
        //Log.d("ProjetFinal","ControleurModeles :: chargementViaSourceSuivante");
        int indiceSourceSuivante = indiceSourceCourante + 1;

        chargementViaSequence(modele,
                cheminDeSauvegarde,
                listenerGetModele,
                indiceSourceSuivante);
    }


    public static void sauvegarderModele(String nomModele) throws ErreurModele {
        //Log.d("ProjetFinal","ControleurModeles :: sauvegarderModele :: " + nomModele);
        for(SourceDeDonnees source : listeDeSauvegardes){
            sauvegarderModeleDansCetteSource(nomModele, source);
        }
    }


    private static void creerModeleSelonNom(String nomModele, final ListenerGetModele listenerGetModele) throws ErreurModele {
        //Log.d("ProjetFinal","ControleurModeles :: creerModeleSelonNom :: " + nomModele);
        if(nomModele.equals(MParametres.class.getSimpleName())){
            listenerGetModele.reagirAuModele(new MParametres());

        }else if(nomModele.equals(MPartie.class.getSimpleName())){
            creerPartie(listenerGetModele);

        }else if(nomModele.equals(MPartieIA.class.getSimpleName())){
            creerPartieIA(listenerGetModele);

        }else if(nomModele.equals(MPartieReseau.class.getSimpleName())){
            creerPartieReseau(listenerGetModele);

        }else{
            throw new ErreurModele("nomModèle inconnu: " + nomModele);
        }
    }


    private static void creerPartie(final ListenerGetModele listenerGetModele) {
        //Log.d("ProjetFinal","ControleurModeles :: creerPartie");
        getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {
                MParametres mParametres = (MParametres) modele;

                listenerGetModele.reagirAuModele(new MPartie(mParametres.getParametresPartie().cloner()));

            }
        });
    }

    private static void creerPartieIA(final ListenerGetModele listenerGetModele) {
        //Log.d("ProjetFinal","ControleurModeles :: creerPartieIA");
        getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {
                MParametres mParametres = (MParametres) modele;

                listenerGetModele.reagirAuModele(new MPartieIA(mParametres.getParametresPartie().cloner()));

            }
        });
    }


    private static void creerPartieReseau(final ListenerGetModele listenerGetModele) {
        getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {
                MParametres mParametres = (MParametres) modele;

                listenerGetModele.reagirAuModele(new MPartieReseau(mParametres.getParametresPartie().cloner()));
            }
        });
    }


    public static void detruireModele(String nomModele) {
        detruireSauvegardes(nomModele);
        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){
            modelesEnMemoire.remove(nomModele);
            ControleurObservation.detruireObservation(modele);

            if(modele instanceof Fournisseur){
                ControleurAction.oublierFournisseur((Fournisseur) modele);
            }
        }
    }


    private static void detruireSauvegardes(String nomModele) {
        String cheminSauvegarde = getCheminSauvegarde(nomModele);

        for(SourceDeDonnees sourceDeDonnees : listeDeSauvegardes){
            sourceDeDonnees.detruireSauvegarde(cheminSauvegarde);
        }
    }


    static String getCheminSauvegarde(String nomModele){
        //Log.d("ProjetFinal","ControleurModeles :: getCheminSauvegarde :: " + nomModele);
        String cheminParDefaut = nomModele + GConstantes.SEPARATEUR_DE_CHEMIN + UsagerCourant.getId();
        String chemin = cheminParDefaut;

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null && modele instanceof Identifiable){
            chemin = getCheminSpecifique(nomModele, (Identifiable) modele);
        }
        return chemin;
    }


    @NonNull
    private static String getCheminSpecifique(String nomModele, Identifiable modele) {
        //Log.d("ProjetFinal","ControleurModeles :: getCheminSpecifique :: " + nomModele);
        String cheminSpecifique = nomModele + GConstantes.SEPARATEUR_DE_CHEMIN + modele.getId();

        return cheminSpecifique;
    }
}
