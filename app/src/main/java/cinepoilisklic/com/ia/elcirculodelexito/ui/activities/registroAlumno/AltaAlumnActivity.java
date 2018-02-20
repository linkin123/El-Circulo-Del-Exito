package cinepoilisklic.com.ia.elcirculodelexito.ui.activities.registroAlumno;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.listaAlumnos.ListaAlumnosActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.menu.MenuActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.opcionesAlumno.OpcionesActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.registroMaestro.AltaMaestroActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import static cinepoilisklic.com.ia.elcirculodelexito.Utils.utils.printToast;

public class AltaAlumnActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etNamePadre;
    private EditText etTelefonoPadre;
    private Button btnRegistrar;
    private CircleImageView civAlumno;
    private ImageButton ibCamera;

    private String APP_DIRECTORY = "myPicture";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jpg";

    private final int PHOTO_CODE = 100;
    private final int SELECTED_PICTURE = 200;
    private boolean insertoImagen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_alumn);

        etName = (EditText) findViewById(R.id.et_alta_alumno_nombre);
        etNamePadre = (EditText) findViewById(R.id.et_alta_alumno_nombre_padre);
        etTelefonoPadre = (EditText) findViewById(R.id.et_alta_alumno_telefono_padre);
        btnRegistrar = (Button) findViewById(R.id.btn_registrar_alumno);
        civAlumno = (CircleImageView) findViewById(R.id.profile_cv_contact);
        ibCamera = (ImageButton) findViewById(R.id.ib_camera);
        btnRegistrar.setOnClickListener(onClickListener);
        ibCamera.setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_registrar_alumno: botonRegistrar(); break;
                case R.id.ib_camera: botonCamara(); break;
            }
        }
    };

    private void botonCamara() {
        final CharSequence[] options = {"tomar foto ", "elegir de galeria", "cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(AltaAlumnActivity.this);
        builder.setTitle("elige una opcion");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int seleccion) {
                if (options[seleccion] == "tomar foto ") {
                    openCamera();
                } else if (options[seleccion] == "elegir de galeria") {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "selecciona app de la imagen"), SELECTED_PICTURE);
                } else if (options[seleccion] == "cancelar") {
                    dialog.dismiss();

                }
            }
        });
        builder.show();

    }

    private void botonRegistrar() {
        if(camposNoVacios()){
            guardar(etName.getText().toString() , etNamePadre.getText().toString() , etTelefonoPadre.getText().toString());
            Intent intentAltaAlumno = new Intent(AltaAlumnActivity.this , OpcionesActivity.class);
            startActivity(intentAltaAlumno);
        }
    }

    private boolean camposNoVacios() {
        if(etName.length() > 0 && etNamePadre.length() > 0 && etTelefonoPadre.length() > 0){
            if(insertoImagen == true){
                return true;
            }
            else{
                printToast(getApplicationContext() , "debe seleccionar una imagen para el alumno");
                return false;
            }
        }
        else{
            /*getAplicationContext : devuelve el contexto del objeto Aplication único y global del proceso actual el cual es onclick*/
            printToast(getApplicationContext() , "debe llenar todos los campos");
            return false;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_CODE:
                if (resultCode == RESULT_OK) {
                    String dir = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;
                    decodeBitmap(dir);
                    insertoImagen = true;
                }
                break;

            case SELECTED_PICTURE:
                if (resultCode == RESULT_OK) {
                    Uri path = data.getData();
                    civAlumno.setImageURI(path);
                    insertoImagen = true;
                }
                break;
        }
    }

    private void guardar(String Nombre, String NombrePadre, String telefonoPadre) {
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            ContentValues c = new ContentValues();
            c.put("Nombre", Nombre);
            c.put("nombrePadre", NombrePadre);
            c.put("telefonoPadre", telefonoPadre);
            //c.put("foto" , foto);
            db.insert("ALUMNOS", null, c);
            db.close();
            Toast.makeText(this, "registro insertado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    private void decodeBitmap(String dir) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);
        civAlumno.setImageBitmap(bitmap);
    }

    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdirs();

        String path = Environment.getExternalStorageDirectory() + File.separator
                + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;

        File newFile = new File(path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
        startActivityForResult(intent, PHOTO_CODE);

    }


}
