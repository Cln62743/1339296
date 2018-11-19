package ca.cours5b5.charleslangevin.controleurs;

import android.util.Log;

import ca.cours5b5.charleslangevin.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.charleslangevin.donnees.Serveur;
import ca.cours5b5.charleslangevin.global.GCommande;
import ca.cours5b5.charleslangevin.global.GConstantes;
import ca.cours5b5.charleslangevin.modeles.Identifiable;
import ca.cours5b5.charleslangevin.modeles.MPartieReseau;
import ca.cours5b5.charleslangevin.modeles.Modele;
import ca.cours5b5.charleslangevin.proxy.ProxyListe;
import ca.cours5b5.charleslangevin.usagers.UsagerCourant;

public class ControleurPartieReseau {

    private static final ControleurPartieReseau instance = new ControleurPartieReseau();
    public static ControleurPartieReseau getInstance() { return instance; }

    private ProxyListe proxyEmettreCoups;
    private ProxyListe proxyRecevoirCoups;

    private ControleurPartieReseau(){}

    public void connecterAuServeur(){
        /*
         * Obtenir le modèle MPartieReseau
         * Obtenir le id du modèle (qui est l'id du joueur hôte)
         * Appeler connecterAuServeur(String idJouerHote)
         */
        ControleurModeles.getModele(MPartieReseau.class.getSimpleName(), new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {
                Log.d("Atelier13","Classe: " + ((Identifiable)modele).getClass().getSimpleName());
                Log.d("Atelier13","idJoueurHote: " + ((Identifiable)modele).getId());

                String idJoueurHote = ((MPartieReseau)modele).getId();
                connecterAuServeur(idJoueurHote);
            }
        });
    }

    private void connecterAuServeur(String idJoueurHote) {
        /*
         * Connecter en tant que joueur hôte OU en tant qu'invité, selon le cas
         * Connecter les deux proxy au serveur
         * Ajouter l'action RECEVOIR_COUP_RESEAU au proxyRecevoirCoups
         */
        String idUtilisateur = UsagerCourant.getId();

        String cheminHote = getCheminCoupsJoueurHote(idJoueurHote);
        String cheminInv = getCheminCoupsJoueurInvite(idJoueurHote);

        //Log.d("Atelier13","idUtilisateur: " + idUtilisateur);
        //Log.d("Atelier13","idJoueurHote: " + idJoueurHote);
        if(idUtilisateur.equals(idJoueurHote)){
            connecterEnTantQueJoueurHote(cheminHote, cheminInv);
        }else{
            connecterEnTantQueJoueurInvite(cheminHote, cheminInv);
        }

        proxyRecevoirCoups.setActionNouvelItem(GCommande.RECEVOIR_COUP_RESEAU);
        proxyRecevoirCoups.connecterAuServeur();

        proxyEmettreCoups.connecterAuServeur();
    }

    private void connecterEnTantQueJoueurHote(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {
        /*
         * Créer les proxy... avec les bons chemins
         */
        proxyEmettreCoups = new ProxyListe(cheminCoupsJoueurHote);
        proxyRecevoirCoups = new ProxyListe(cheminCoupsJoueurInvite);
    }

    private void connecterEnTantQueJoueurInvite(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {
        /*
         * Créer les proxy... avec les bons chemins
         */
        proxyEmettreCoups = new ProxyListe(cheminCoupsJoueurInvite);
        proxyRecevoirCoups = new ProxyListe(cheminCoupsJoueurHote);
    }

    public void deconnecterDuServeur(){
        /*
         * Détruire les valeurs du proxyEmettreCoups
         * Déconnecter les deux proxy
         */
        proxyEmettreCoups.detruireValeurs();

        proxyEmettreCoups.deconnecterDuServeur();
        proxyRecevoirCoups.deconnecterDuServeur();
    }

    public void transmettreCoup(Integer idColonne){
        /*
         * Transmettre avec proxyEmettreCoups
         */
        proxyEmettreCoups.ajouterValeur(idColonne);
    }

    private String getCheminPartie(String idJoueurHote){
        /*
         * Le chemin contient l'id de la partie (id du joueur hote)
         */
        String cheminPartie = MPartieReseau.class.getSimpleName() + "/" + idJoueurHote;
        return cheminPartie;
    }

    private String getCheminCoupsJoueurHote(String idJoueurHote){
        /*
         * Utiliser p.ex. la constante CLE_COUPS_JOUR_HOTE
         */
        String cheminHote = getCheminPartie(idJoueurHote);
        cheminHote += "/" + GConstantes.CLE_COUPS_JOUEUR_HOTE;
        return cheminHote;
    }

    private String getCheminCoupsJoueurInvite(String idJoueurHote){
        /*
         * Utiliser p.ex. la constante CLE_COUPS_JOUR_INVITE
         */
        String cheminInv = getCheminPartie(idJoueurHote);
        cheminInv += "/" + GConstantes.CLE_COUPS_JOUEUR_INVITE;
        return cheminInv;
    }

    public void detruireSauvegardeServeur() {
        /*
         * Appeler p.ex. le detruireSauvegarde de Serveur (avec le bon chemin)
         */
        Serveur.getInstance().detruireSauvegarde(getCheminPartie(UsagerCourant.getId()));
    }
}