package ca.cours5b5.charleslangevin.controleurs;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.charleslangevin.donnees.ListenerChargement;
import ca.cours5b5.charleslangevin.donnees.Serveur;
import ca.cours5b5.charleslangevin.donnees.SourceDeDonnees;
import ca.cours5b5.charleslangevin.exceptions.ErreurModele;
import ca.cours5b5.charleslangevin.modeles.Identifiable;
import ca.cours5b5.charleslangevin.modeles.MParametres;
import ca.cours5b5.charleslangevin.modeles.MParametresPartie;
import ca.cours5b5.charleslangevin.modeles.MPartie;
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
        listeDeSauvegardes.add(Serveur.getInstance());
        listeDeSauvegardes.add(Disque.getInstance());
    }

    public static void setSequenceDeChargement(SourceDeDonnees... sequenceDeChargement){
        ControleurModeles.sequenceDeChargement = sequenceDeChargement;
    }

    public static void sauvegarderModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {
        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){
            Map<String, Object> objetJson = modele.enObjetJson();

            String cheminSauvegarde = getCheminSauvegarde(nomModele);
            sourceDeDonnees.sauvegarderModele(cheminSauvegarde, objetJson);
        }
    }

    public static void sauvegarderModele(String nomModele) throws ErreurModele {
        for(SourceDeDonnees source : listeDeSauvegardes){
            sauvegarderModeleDansCetteSource(nomModele, source);
        }
    }

    static void getModele(final String nomModele, final ListenerGetModele listenerGetModele){
        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele == null){
            creerModeleEtChargerDonnees(nomModele, listenerGetModele);
        }else{
            listenerGetModele.reagirAuModele(modele);
        }
    }

    private static void creerModeleEtChargerDonnees(final String nomModele, final ListenerGetModele listenerGetModele) {
        creerModeleSelonNom(nomModele, new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {
                modelesEnMemoire.put(nomModele, modele);

                chargerDonnees(modele, nomModele, listenerGetModele);
            }
        });
    }

    private static void creerModeleSelonNom(String nomModele, final ListenerGetModele listenerGetModele) throws ErreurModele {
        if(nomModele.equals(MParametres.class.getSimpleName())){
            listenerGetModele.reagirAuModele(new MParametres());

        }else if(nomModele.equals(MPartie.class.getSimpleName())) {
            getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
                @Override
                public void reagirAuModele(Modele modele) {

                    MParametres mParametres = (MParametres) modele;

                    MPartie mPartie = new MPartie(mParametres.getParametresPartie().cloner());

                    listenerGetModele.reagirAuModele(mPartie);
                }
            });
        }else if(nomModele.equals(MPartieReseau.class.getSimpleName())){
            getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
                @Override
                public void reagirAuModele(Modele modele) {

                    MParametres mParametres = (MParametres) modele;

                    MPartieReseau mPartieReseau = new MPartieReseau(mParametres.getParametresPartie().cloner());

                    listenerGetModele.reagirAuModele(mPartieReseau);
                }
            });
        }else {
            throw new ErreurModele("Modèle inconnu: " + nomModele);
        }
    }

    private static void chargerDonnees(Modele modele, String nomModele, ListenerGetModele listenerGetModele){
        String cheminSauvegarde = getCheminSauvegarde(nomModele);
        chargementViaSequence(modele, cheminSauvegarde, listenerGetModele, 0);
    }

    private static void chargementViaSequence(Modele modele, String cheminSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante){
        if(indiceSourceCourante < sequenceDeChargement.length){
            chargementViaSourceCouranteOuSuivante(modele, cheminSauvegarde, listenerGetModele, indiceSourceCourante);
        }else{
            terminerChargement(modele, listenerGetModele);
        }
    }

    private static void chargementViaSourceCouranteOuSuivante(final Modele modele, final String cheminSauvegarde, final ListenerGetModele listenerGetModele, final int indiceSourceCourante){
        sequenceDeChargement[indiceSourceCourante].chargerModele(cheminSauvegarde, new ListenerChargement(){

            @Override
            public void reagirSuccess(Map<String, Object> objetJson) {
                terminerChargementAvecDonnees(objetJson, modele, listenerGetModele);
            }

            @Override
            public void reagirErreur(Exception e) {
                chargementViaSourceSuivante(modele, cheminSauvegarde, listenerGetModele,indiceSourceCourante);
            }
        });
    }

    private static void terminerChargementAvecDonnees(Map<String, Object> objetJson, Modele modele, ListenerGetModele listenerGetModele){
        if(objetJson!= null){
            modele.aPartirObjetJson(objetJson);
        }

        terminerChargement(modele, listenerGetModele);
    }

    private static void terminerChargement(Modele modele, ListenerGetModele listenerGetModele){
        listenerGetModele.reagirAuModele(modele);
    }

    private static void chargementViaSourceSuivante(Modele modele, String cheminSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante){
        indiceSourceCourante++;
        chargementViaSequence(modele, cheminSauvegarde, listenerGetModele, indiceSourceCourante);
    }

    private static String getCheminSauvegarde(final String nomModele){
        Modele modele = modelesEnMemoire.get(nomModele);
        String cheminSauvegarde = nomModele;

        if(modele instanceof Identifiable){
            cheminSauvegarde += "/" + ((Identifiable) modele).getId();

        }else{
            String idUsager = UsagerCourant.getId();
            cheminSauvegarde += "/" + idUsager;
        }

        return cheminSauvegarde;
    }


}
