package ca.cours5b5.charleslangevin.vues;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;

public class VMenuPrincipal extends ConstraintLayout implements Vue {
    static String classDebug;

    static {

        classDebug = VMenuPrincipal.class.getSimpleName();
        Log.i("Atelier04", classDebug + "::static");
    }
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
    protected void onFinishInflate() {
        Log.i("Atelier04", classDebug + "::onFinishInflate");
        super.onFinishInflate();
    }
}
