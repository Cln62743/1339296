package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.controleurs.Action;
import ca.cours5b5.charleslangevin.controleurs.ControleurAction;
import ca.cours5b5.charleslangevin.global.GCommande;


public class VMenuPrincipal extends Vue {

    private Button boutonPartie;
    private Action actionPartie;

    private Button boutonParametres;
    private Action actionParametres;

    private Button boutonConnexion;
    private Action actionConnexion;

    public VMenuPrincipal(Context context) {
        super(context);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate(){
        Log.d("Atelier","VMenuPrincipal::onFinishInflate");
        super.onFinishInflate();

        recupererControles();
        demanderActions();
        installerListeners();
    }


    private void recupererControles() {
        boutonPartie = findViewById(R.id.bouton_partie);
        boutonParametres = findViewById(R.id.bouton_parametres);
        boutonConnexion = findViewById(R.id.bouton_connexion);
    }

    private void demanderActions() {
        actionPartie = ControleurAction.demanderAction(GCommande.DEMARRER_PARTIE);
        actionParametres = ControleurAction.demanderAction(GCommande.OUVRIR_MENU_PARAMETRES);
        actionConnexion = ControleurAction.demanderAction(GCommande.CONNEXION);
    }


    private void installerListeners() {
        installerListenerPartie();
        installerListenerParametres();
        installerListenerConnexion();
    }

    private void installerListenerPartie() {
        boutonPartie.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionPartie.executerDesQuePossible();
            }
        });
    }

    private void installerListenerParametres() {
        boutonParametres.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionParametres.executerDesQuePossible();
            }
        });
    }

    private void installerListenerConnexion() {
        boutonConnexion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionConnexion.executerDesQuePossible();
            }
        });
    }
}
