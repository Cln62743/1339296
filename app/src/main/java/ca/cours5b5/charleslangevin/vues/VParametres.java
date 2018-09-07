package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.modeles.MParametres;

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
        MParametres modeleParam = new MParametres();

        Spinner sHeight = this.findViewById(R.id.spinHeight);
        Spinner sWidth = this.findViewById(R.id.spinWidth);
        Spinner sWin = this.findViewById(R.id.spinWin);

        // Height
        List<Integer> adapHeight = modeleParam.getChoixHauteur();
        sHeight.setAdapter(new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, adapHeight));
        sHeight.setSelection(modeleParam.getHauteur() - adapHeight.get(0));

        // Width
        List<Integer> adapWidth = modeleParam.getChoixLargeur();
        sWidth.setAdapter(new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, adapWidth));
        sWidth.setSelection(modeleParam.getLargeur() - adapWidth.get(0));

        // Win
        List<Integer> adapWin = modeleParam.getChoixPourGagner();
        sWin.setAdapter(new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, adapWin));
        sWin.setSelection(modeleParam.getPourGagner() - adapWin.get(0));

    }
}
