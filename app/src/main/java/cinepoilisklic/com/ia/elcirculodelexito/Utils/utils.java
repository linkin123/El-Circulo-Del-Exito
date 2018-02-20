package cinepoilisklic.com.ia.elcirculodelexito.Utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import cinepoilisklic.com.ia.elcirculodelexito.R;

/**
 * Created by lrangel on 08/02/2018.
 */

public class utils {

    public static void printToast(Context context , String msj){
        Toast.makeText( context, msj , Toast.LENGTH_SHORT).show();
    }

    public static int getImageMateria(String Materia) {
        if (Materia.equals("Administración"))
            return R.drawable.administracion;
        if (Materia.equals("Biología"))
            return R.drawable.biologiax;
        if (Materia.equals("Comprensión Lectora"))
            return R.drawable.comprension_lectora;
        if (Materia.equals("Economía"))
            return R.drawable.economia;
        if (Materia.equals("Español"))
            return R.drawable.espaniolx;
        if (Materia.equals("Estadistica"))
            return R.drawable.estadistica;
        if (Materia.equals("Física"))
            return R.drawable.fisica;
        if (Materia.equals("Geografía"))
            return R.drawable.geografiax;
        if (Materia.equals("Historia Universal"))
            return R.drawable.historia_universal;
        if (Materia.equals("Historia de México"))
            return R.drawable.hitoriamexicox;
        if (Materia.equals("Inglés"))
            return R.drawable.ingles;
        if (Materia.equals("Literatura"))
            return R.drawable.literaturax;
        if (Materia.equals("Matemáticas"))
            return R.drawable.matematicasx;
        if (Materia.equals("Pensamiento Analítico"))
            return R.drawable.pensamiento_analitico;
        if (Materia.equals("Psicología"))
            return R.drawable.psicologia;
        if (Materia.equals("Química"))
            return R.drawable.quimicax;
        return 0;
    }

    public static String geMateriaById(int id) {
        String materia = "";
        switch (id) {
            case 1: materia = "Administracion";
                break;
            case 2: materia ="Biología";
                break;
            case 3: materia = "Comprensión lectora";
                break;
            case 4: materia = "Economía";
                break;
            case 5: materia = "Español";
                break;
            case 6: materia = "Estadistica";
                break;
            case 7: materia = "Física";
                break;
            case 8: materia = "Geografía";
                break;
            case 9: materia = "Historia Universal";
                break;
            case 10: materia = "Historia de México";
                break;
            case 11: materia = "Ingles";
                break;
            case 12: materia = "Literatura";
                break;
            case 13: materia = "Matemáticas";
                break;
            case 14: materia = "Pensamiento Analítico";
                break;
            case 15: materia = "Psicología";
                break;
            case 16: materia = "Química";
                break;
            case 17: materia = "TICS";
                break;
        }
        return materia;
    }


}
