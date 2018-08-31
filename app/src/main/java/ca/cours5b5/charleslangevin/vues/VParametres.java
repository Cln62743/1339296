package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ca.cours5b5.charleslangevin.R;
import ca.cours5b5.charleslangevin.global.GConstantes;

public class VParametres extends ConstraintLayout {


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
        super.onFinishInflate();

        Spinner sHeight = this.findViewById(R.id.spinHeight);
        Spinner sWidth = this.findViewById(R.id.spinWidth);
        Spinner sWin = this.findViewById(R.id.spinWin);

        // Height
        ArrayAdapter<Integer> adapHeight = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        sHeight.setAdapter(adapHeight);

        for(int i = GConstantes.minHeight; i<= GConstantes.maxHeight; i++) {
            adapHeight.add(i);
            if(i == GConstantes.defaultHeight){
                sHeight.setSelection(i-GConstantes.minHeight);
            }
        }

        // Width
        ArrayAdapter<Integer> adapWidth = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        sWidth.setAdapter(adapWidth);

        for(int i = GConstantes.minWidth; i<= GConstantes.maxWidth; i++) {
            adapWidth.add(i);
            if(i == GConstantes.defaultWidth){
                sWidth.setSelection(i-GConstantes.minWidth);
            }
        }

        // Win
        ArrayAdapter<Integer> adapWin = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);
        sWin.setAdapter(adapWin);

        for(int i = GConstantes.minToWin; i<= GConstantes.maxToWin; i++) {
            adapWin.add(i);
            if(i == GConstantes.defaultToWin){
                sWin.setSelection(i-GConstantes.minToWin);
            }
        }
    }
}
