package ca.cours5b5.charleslangevin.proxy;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.List;

import ca.cours5b5.charleslangevin.controleurs.Action;
import ca.cours5b5.charleslangevin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.charleslangevin.global.GCommande;

public class ProxyListe extends Proxy implements Fournisseur {
    private ChildEventListener childEventListener;
    private Query requete;
    private Action actionNouvelItem;
    private List<DatabaseReference> noeudsAjoutes;

    // TODO
    public ProxyListe(String cheminServeur) {

    }

    public void setActionNouvelItem(GCommande commande){

    }

    public void ajouterValeur(Object valeur) {
        /*
        * Créer un sous-noeud avec push()
        * Mémoriser le noeud ajouté
        * Ajouter la valeur avec setValue()
        */
    }

    @Override
    public void connecterAuServeur() {
        super.connecterAuServeur();
        /*
         * Créer le listener
         * sauvegarder la requête
         * ajouter le listener
         */
    }

    private void creerListener(){

    }

    protected Query getRequete(){
        /*
         * On veut trier par clé et limiter à un nombre max (utiliser une constante)
         */
    }

    @Override
    public void deconnecterDuServeur() {
        /*
         * retirer le listener
         * oublier les noeuds ajoutés
         * déconnecter via super
         */
    }

    @Override
    public void detruireValeurs() {

    }
}
