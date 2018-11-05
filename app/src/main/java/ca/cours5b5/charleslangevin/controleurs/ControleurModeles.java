package ca.cours5b5.charleslangevin.controleurs;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.donnees.GLog;
import ca.cours5b5.charleslangevin.donnees.SauvegardeTemporaire;
import ca.cours5b5.charleslangevin.donnees.Serveur;
import ca.cours5b5.charleslangevin.donnees.SourceDeDonnees;
import ca.cours5b5.charleslangevin.exceptions.ErreurModele;
import ca.cours5b5.charleslangevin.modeles.MParametres;
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

    static Modele getModele(final String nomModele){
        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele == null){
            modele =  chargerViaSequenceDeChargement(nomModele);
        }
        return modele;
    }


    private static Modele chargerViaSequenceDeChargement(final String nomModele){
        Modele modele = creerModeleSelonNom(nomModele);
        modelesEnMemoire.put(nomModele, modele);

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

    public static void sauvegarderModele(String nomModele) throws ErreurModele {
        for(SourceDeDonnees source : listeDeSauvegardes){
            sauvegarderModeleDansCetteSource(nomModele, source);
        }
    }


    private static Modele creerModeleSelonNom(String nomModele) throws ErreurModele {
        if(nomModele.equals(MParametres.class.getSimpleName())){
            return new MParametres();

        }else if(nomModele.equals(MPartie.class.getSimpleName())){
            MParametres mParametres = (MParametres) getModele(MParametres.class.getSimpleName());
            return new MPartie(mParametres.getParametresPartie().cloner());

        }else{
            throw new ErreurModele("Modèle inconnu: " + nomModele);
        }
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

}
