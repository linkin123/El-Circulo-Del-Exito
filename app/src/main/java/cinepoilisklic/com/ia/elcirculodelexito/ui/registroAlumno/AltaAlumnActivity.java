package cinepoilisklic.com.ia.elcirculodelexito.ui.registroAlumno;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper;
import cinepoilisklic.com.ia.elcirculodelexito.ui.opcionesAlumno.OpcionesActivity;

import static cinepoilisklic.com.ia.elcirculodelexito.Utils.utils.printToast;

public class AltaAlumnActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etNamePadre;
    private EditText etTelefonoPadre;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_alumn);

        etName = (EditText) findViewById(R.id.et_alta_alumno_nombre);
        etNamePadre = (EditText) findViewById(R.id.et_alta_alumno_nombre_padre);
        etTelefonoPadre = (EditText) findViewById(R.id.et_alta_alumno_telefono_padre);
        btnRegistrar = (Button) findViewById(R.id.btn_registrar_alumno);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName == null || etNamePadre == null || etTelefonoPadre == null){
                    guardar(etName.getText().toString() , etNamePadre.getText().toString() , etTelefonoPadre.getText().toString());
                    Intent intentAltaAlumno = new Intent(AltaAlumnActivity.this , OpcionesActivity.class);
                    startActivity(intentAltaAlumno);
                }
                else{
                    /*getAplicationContext : devuelve el contexto del objeto Aplication único y global del proceso actual el cual es onclick*/
                    printToast(getApplicationContext() , "debe llenar todos los campos");
                }

            }
        });

    }

    private void guardar(String Nombre , String NombrePadre , String telefonoPadre){
        BaseHelper helper = new BaseHelper(this , "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("Nombre", Nombre);
            c.put("nombrePadre" , NombrePadre);
            c.put("telefono" , telefonoPadre);
            db.insert("ALUMNOS", null , c);
            db.close();
            Toast.makeText(this , "registro insersato", Toast.LENGTH_SHORT).show();
        }catch( Exception e){
            Toast.makeText(this , "Error:"+ e.getMessage() , Toast.LENGTH_SHORT).show();

        }
    }
}
