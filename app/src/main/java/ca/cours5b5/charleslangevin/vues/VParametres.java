package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.telecom.Call;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.controleurs.Action;
import ca.cours5b5.charleslangevin.controleurs.ControleurAction;
import ca.cours5b5.charleslangevin.global.GCommande;
import ca.cours5b5.charleslangevin.modeles.MParametres;
import ca.cours5b5.charleslangevin.modeles.MParametresPartie;

public class VParametres extends ConstraintLayout implements Vue {
    static String classDebug;

    static {

        classDebug = VParametres.class.getSimpleName();
        Log.i("Atelier04", classDebug + "::static");
    }

    public VParametres(Context context) {
        super(context);
    }

    public VParametres(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VParametres(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        Log.i("Atelier04", classDebug + "::onFinishInflate");
        super.onFinishInflate();

        Spinner sHeight = this.findViewById(R.id.spinHeight);
        Spinner sWidth = this.findViewById(R.id.spinWidth);
        Spinner sWin = this.findViewById(R.id.spinWin);

        final MParametresPartie mParams = MParametres.instance.getParametresPartie();

        // Height
        List<Integer> adapHeight = MParametres.instance.getChoixHauteur();
        sHeight.setAdapter(new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, adapHeight));
        sHeight.setSelection(adapHeight.indexOf(mParams.getHauteur()));

        sHeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                mParams.setHauteur((int)value);
                /*Action actionHauteur = ControleurAction.demanderAction(GCommande.CHOISIR_HAUTEUR);

                // Une fois qu'on connais le choix de l'usager
                actionHauteur.setArguments(value);
                actionHauteur.executerDesQuePossible();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Width
        List<Integer> adapWidth = MParametres.instance.getChoixLargeur();
        sWidth.setAdapter(new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, adapWidth));
        sWidth.setSelection(adapWidth.indexOf(mParams.getLargeur()));

        sWidth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                mParams.setLargeur((int)value);
                /*Action actionLargeur = ControleurAction.demanderAction(GCommande.CHOISIR_LARGEUR);

                // Une fois qu'on connais le choix de l'usager
                actionLargeur.setArguments(value);
                actionLargeur.executerDesQuePossible();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Win
        List<Integer> adapWin = MParametres.instance.getChoixPourGagner();
        sWin.setAdapter(new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, adapWin));
        sWin.setSelection(adapWin.indexOf(mParams.getPourGagner()));

        sWin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                mParams.setPourGagner((int)value);
                /*Action actionPourGagner = ControleurAction.demanderAction(GCommande.CHOISIR_POUR_GAGNER);

                // Une fois qu'on connais le choix de l'usager
                actionPourGagner.setArguments(value);
                actionPourGagner.executerDesQuePossible();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
