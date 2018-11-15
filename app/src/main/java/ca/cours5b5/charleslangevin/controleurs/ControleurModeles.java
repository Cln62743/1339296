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
import ca.cours5b5.charleslangevin.modeles.MParametres;
import ca.cours5b5.charleslangevin.modeles.MParametresPartie;
import ca.cours5b5.charleslangevin.modeles.MPartie;
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
            Log.d("Debug Atelier 12","Methode: sauvegarderModeleDansCetteSource | CheminSauvegarde: " + cheminSauvegarde);
            sourceDeDonnees.sauvegarderModele(cheminSauvegarde, objetJson);
        }
    }

    public static void sauvegarderModele(String nomModele) throws ErreurModele {
        for(SourceDeDonnees source : listeDeSauvegardes){
            sauvegarderModeleDansCetteSource(nomModele, source);
        }
    }

    // Premiere partie
    // DONE
    static void getModele(final String nomModele, final ListenerGetModele listenerGetModele){
        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele == null){
            creerModeleEtChargerDonnees(nomModele, listenerGetModele);
        }else{
            listenerGetModele.reagirAuModele(modele);
        }
    }

    // DONE
    private static void creerModeleEtChargerDonnees(final String nomModele, final ListenerGetModele listenerGetModele) {
        creerModeleSelonNom(nomModele, new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {
                modelesEnMemoire.put(nomModele, modele);

                chargerDonnees(modele, nomModele, listenerGetModele);
            }
        });
    }


    // DONE
    private static void creerModeleSelonNom(String nomModele, final ListenerGetModele listenerGetModele) throws ErreurModele {
        if(nomModele.equals(MParametres.class.getSimpleName())){
            listenerGetModele.reagirAuModele(new MParametres());

        }else if(nomModele.equals(MPartie.class.getSimpleName())){

            getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
                @Override
                public void reagirAuModele(Modele modele) {

                    MParametres mParametres = (MParametres) modele;

                    MPartie mPartie = new MPartie(mParametres.getParametresPartie().cloner());

                    listenerGetModele.reagirAuModele(mPartie);
                }
            });
        }else {
            throw new ErreurModele("Modèle inconnu: " + nomModele);
        }
    }

    // DONE
    private static void chargerDonnees(Modele modele, String nomModele, ListenerGetModele listenerGetModele){
        String cheminSauvegarde = getCheminSauvegarde(nomModele);
        Log.d("Debug Atelier 12","Methode: chargerDonnees | CheminSauvegarde: " + cheminSauvegarde);
        chargementViaSequence(modele, cheminSauvegarde, listenerGetModele, 0);
    }

    // DONE
    private static void chargementViaSequence(Modele modele, String cheminSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante){
        Log.d("Debug Atelier 12","Methode: chargementViaSequence | CheminSauvegarde: " + cheminSauvegarde);
        if(indiceSourceCourante < sequenceDeChargement.length - 1){
            chargementViaSourceCouranteOuSuivante(modele, cheminSauvegarde, listenerGetModele, indiceSourceCourante);
        }

        terminerChargement(modele, listenerGetModele);
    }

    // DONE
    private static void chargementViaSourceCouranteOuSuivante(final Modele modele, final String cheminSauvegarde, final ListenerGetModele listenerGetModele, final int indiceSourceCourante){
        Log.d("Debug Atelier 12","Methode: chargementViaSourceCouranteOuSuivante | CheminSauvegarde: " + cheminSauvegarde);
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

    // DONE
    private static void terminerChargementAvecDonnees(Map<String, Object> objetJson, Modele modele, ListenerGetModele listenerGetModele){
        if(objetJson!= null){
            modele.aPartirObjetJson(objetJson);
        }

        terminerChargement(modele, listenerGetModele);
    }

    // DONE
    private static void terminerChargement(Modele modele, ListenerGetModele listenerGetModele){
        listenerGetModele.reagirAuModele(modele);
    }

    // DONE
    private static void chargementViaSourceSuivante(Modele modele, String cheminSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante){
        Log.d("Debug Atelier 12","Methode: chargementViaSourceSuivante | CheminSauvegarde: " + cheminSauvegarde);
        indiceSourceCourante++;
        chargementViaSequence(modele, cheminSauvegarde, listenerGetModele, indiceSourceCourante);
    }

    /* static void detruireModele(String nomModele) {
        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){
            modelesEnMemoire.remove(nomModele);
            ControleurObservation.detruireObservation(modele);

            if(modele instanceof Fournisseur){
                ControleurAction.oublierFournisseur((Fournisseur) modele);
            }
        }
    }*/

    private static String getCheminSauvegarde(String nomModele){
        /*
        * Le chemin est de la forme:
        * nomModele/idUsager
        *
        * Par exemple:
        * MPartie/T1m8GxiBAlhLUcF6Ne0GV06nnE1
        */
        String idUsager = UsagerCourant.getId();
        String cheminSauvegarde = "";

        if(idUsager != null && nomModele != null){
            cheminSauvegarde = nomModele + "/" + idUsager;
        }
        return cheminSauvegarde;
    }


}
