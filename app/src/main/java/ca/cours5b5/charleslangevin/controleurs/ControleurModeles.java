package ca.cours5b5.charleslangevin.controleurs;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.charleslangevin.donnees.GLog;
import ca.cours5b5.charleslangevin.donnees.SauvegardeTemporaire;
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
            sourceDeDonnees.sauvegarderModele(cheminSauvegarde, objetJson);
        }
    }

    private static Modele chargerViaSequenceDeChargement(final String nomModele){
        getModele(nomModele, new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {

            }
        });

        Modele modele = modelesEnMemoire.get(nomModele);

        String cheminSauvegarde = getCheminSauvegarde(nomModele);
        for(SourceDeDonnees sourceDeDonnees : sequenceDeChargement){
            Map<String, Object> objetJson = sourceDeDonnees.chargerModele(cheminSauvegarde);

            if(objetJson != null){
                modele.aPartirObjetJson(objetJson);
                break;
            }
        }
        return modele;
    }

    // Premiere partie
    static void getModele(final String nomModele, final ListenerGetModele listenerGetModele){
        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele == null){
            creerModeleEtChargerDonnees(nomModele, listenerGetModele);
        }
        listenerGetModele.reagirAuModele(modele);
    }

    private static void creerModeleEtChargerDonnees(final String nomModele, final ListenerGetModele listenerGetModele) {
        // A revenir dessus
        modelesEnMemoire.put(nomModele, modele);
        creerModeleSelonNom(nomModele, listenerGetModele);
    }


    private static void creerModeleSelonNom(String nomModele, final ListenerGetModele listenerGetModele) throws ErreurModele {
        // A revenir dessus
        if(nomModele.equals(MParametres.class.getSimpleName())){
            listenerGetModele.reagirAuModele(new MParametres());

        }else if(nomModele.equals(MPartie.class.getSimpleName())){
            listenerGetModele.reagirAuModele(new MParametresPartie());

        }
        throw new ErreurModele("Modèle inconnu: " + nomModele);
    }

    // Deuxieme partie
    private static void chargerDonnees(Modele modele, String nomModele, ListenerGetModele listenerGetModele){

    }

    private static void chargementViaSequence(Modele modele, String chemindeSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante){

    }

    private static void chargementViaSourceCouranteOuSuivante(final Modele modele, final String cheminDeSauvegarde, final ListenerGetModele listenerGetModele, final int indiceSourceCourante){

    }

    private static void terminerChargementAvecDonnees(Map<String, Object> objetJson, Modele modele, ListenerGetModele listenerGetModele){

    }

    private static void terminerChargement(Modele modele, ListenerGetModele listenerGetModele){

    }

    private static void chargementViaSourceSuivante(Modele modele, String cheminSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante){

    }


































    public static void detruireModele(String nomModele) {
        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){
            modelesEnMemoire.remove(nomModele);
            ControleurObservation.detruireObservation(modele);

            if(modele instanceof Fournisseur){
                ControleurAction.oublierFournisseur((Fournisseur) modele);
            }
        }
    }

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

    public static void sauvegarderModele(String nomModele) throws ErreurModele {
        for(SourceDeDonnees source : listeDeSauvegardes){
            sauvegarderModeleDansCetteSource(nomModele, source);
        }
    }
}
