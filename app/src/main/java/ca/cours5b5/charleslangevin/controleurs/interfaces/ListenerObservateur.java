package ca.cours5b5.charleslangevin.controleurs.interfaces;

import ca.cours5b5.charleslangevin.modeles.Modele;

public abstract class ListenerObservateur {

    public void reagirNouveauModele(Modele modele){
        /**
         * L'implantation par défaut est d'appeler reagirChangementAuModele
         *
         */
        reagirChangementAuModele(modele);
    }

    public abstract void reagirChangementAuModele(Modele modele);
}
