package ca.cours5b5.charleslangevin.controleurs;

import ca.cours5b5.charleslangevin.proxy.ProxyListe;

public class ControleurPartieReseau {
    private static final ControleurPartieReseau instance = null;
    public static ControleurPartieReseau getInstance() {return instance;}

    private ProxyListe proxyEmettreCoups;
    private ProxyListe proxyRecevoirCoups;

    // TODO
    public void connecterAuServeur(){
        /*
         * Obtenir le modèle MPartieReseau
         * Obtenir le id du modèle (qui est l'id du joueur hôte)
         * Appeler connecterAuServeur(String idJouerHote)
         */
    }

    private void connecterAuServeur(String idJoueurHote) {
        /*
         * Connecter en tant que joueur hôte OU en tant qu'invité, selon le cas
         * Connecter les deux proxy au serveur
         * Ajouter l'action RECEVOIR_COUP_RESEAU au proxyRecevoirCoups
         */
    }

    private void connecterEnTantQueJoueurHote(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {
        /*
         * Créer les proxy... avec les bons chemins
         */
    }

    private void connecterEnTantQueJoueurInvite(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {
        /*
         * Créer les proxy... avec les bons chemins
         */
    }

    public void deconnecterDuServeur(){
        /*
         * Détruire les valeurs du proxyEmettreCoups
         * Déconnecter les deux proxy
         */
    }

    public void transmettreCoup(Integer idColonne){
        /*
         * Transmettre avec proxyEmettreCoups
         */
    }

    private String getCheminCoupsJoueurInvite(String idJoueurHote){
        /*
         * Utiliser p.ex. la constante CLE_COUPS_JOUR_INVITE
         */
        return null;
    }

    private String getCheminCoupsJoueurHote(String idJoueurHote){
        /*
         * Utiliser p.ex. la constante CLE_COUPS_JOUR_HOTE
         */
        return null;
    }

    private String getCheminPartie(String idJoueurHote){
        /*
         * Le chemin contient l'id de la partie (id du joueur hote)
         */
        return null;
    }

    public void detruireSauvegardeServeur() {
        /*
         * Appeler p.ex. le detruireSauvegarde de Serveur (avec le bon chemin)
         */
    }
}