package ca.cours5b5.charleslangevin.global;

public enum GCommande {

    // Action de parametres
    CHOISIR_HAUTEUR,
    CHOISIR_LARGEUR,
    CHOISIR_POUR_GAGNER,

    // Action de partie
    PLACER_JETON_ICI,
    RECEVOIR_COUP_RESEAU,

    // Intent parametres
    OUVRIR_MENU_PARAMETRES,

    // Intent connexion
    CONNEXION,
    DECONNEXION,

    // Intent des parties
    DEMARRER_PARTIE,
    DEMARRER_PARTIE_IA,
    DEMARRER_PARTIE_RESEAU,

    // Connexion des joueurs pour partie reseau
    JOINDRE_OU_CREER_PARTIE_RESEAU,
    RECEVOIR_JOUEUR_EN_ATTENTE,
    RECEVOIR_JOUEUR_INVITE,

    // Out come possible
    AFFICHER_MESSAGE_GAGNANT,
    EFFACER_PARTIE_COURANTE,
    EFFACER_PARTIE_COURANTE_IA,
    TERMINER_PARTIE,
}
