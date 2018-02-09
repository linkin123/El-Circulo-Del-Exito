package cinepoilisklic.com.ia.elcirculodelexito.Utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by lrangel on 08/02/2018.
 */

public class utils {

    public static void printToast(Context context , String msj){
        Toast.makeText( context, msj , Toast.LENGTH_SHORT).show();
    }
}
