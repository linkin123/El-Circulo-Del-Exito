package cinepoilisklic.com.ia.elcirculodelexito.ui.activities.registroMaestro;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.opcionesAlumno.OpcionesActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.registroAlumno.AltaAlumnActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import static cinepoilisklic.com.ia.elcirculodelexito.Utils.utils.printToast;
import static cinepoilisklic.com.ia.elcirculodelexito.data.Niveles.PREPARATORIA_PAQUETE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.Niveles.PRIMARIA_PAQUETE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.Niveles.SECUNDARIA_PAQUETE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.Niveles.UNIVERSIDAD_PAQUETE;

public class AltaMaestroActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private CircleImageView civMaestro;
    private ImageButton ibCamera;
    private EditText etName;
    private EditText etDomicilio;
    private EditText etTelefonoMaestro;
    private RadioGroup rgHorario;
    private RadioButton rbtnHMatutino;
    private RadioButton rbtnHVespertino;
    private Button btnRegistrar;

    private String APP_DIRECTORY = "myPicture";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jpg";

    private String MATUTINO = "matutino";
    private String VERPERTINO = "verpertino";
    private String horario;

    private  final int PHOTO_CODE = 100;
    private  final int SELECTED_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_maestro);

        civMaestro= (CircleImageView) findViewById(R.id.profile_cv_contact_maestro);
        ibCamera = (ImageButton) findViewById(R.id.ib_camera_maestro);
        etName = (EditText) findViewById(R.id.et_alta_maestro_nombre);
        etDomicilio = (EditText) findViewById(R.id.et_alta_maestro_domicilio);
        etTelefonoMaestro = (EditText) findViewById(R.id.et_alta_maestro_telefono);
        rgHorario = (RadioGroup) findViewById(R.id.rg_maestro_horario);
        rgHorario.setOnCheckedChangeListener(this);
        btnRegistrar = (Button) findViewById(R.id.btn_registrar_maestro);
        rgHorario.setOnCheckedChangeListener(this);
        btnRegistrar.setOnClickListener(onClickListener);
        ibCamera.setOnClickListener(onClickListener);
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_registrar_maestro: botonRegistrar(); break;
                case R.id.ib_camera_maestro: botonCamara(); break;
            }
        }
    };

    private void botonCamara() {
        final CharSequence[] options = {"tomar foto ", "elegir de galeria", "cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(AltaMaestroActivity.this);
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

    private void botonRegistrar() {
            if(etName == null || etName == null || etDomicilio == null || etTelefonoMaestro == null){
                    /*getAplicationContext : devuelve el contexto del objeto Aplication único y global del proceso actual el cual es onclick*/
                printToast(getApplicationContext() , "debe llenar todos los campos");
            }
            else{
                guardar(etName.getText().toString() , etDomicilio.getText().toString() , etTelefonoMaestro.getText().toString());
                Intent intentAltaAlumno = new Intent(AltaMaestroActivity.this , OpcionesActivity.class);
                startActivity(intentAltaAlumno);

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
                }
                break;

            case SELECTED_PICTURE:
                if (resultCode == RESULT_OK) {
                    Uri path = data.getData();
                    civMaestro.setImageURI(path);
                }
                break;
        }
    }

    private void guardar(String nombre, String domicilio,  String telefonoMaestro) {
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
/*            ContentValues c = new ContentValues();
            c.put("Nombre", nombre);
            c.put("domicilio", domicilio);
            c.put("telefono", telefonoMaestro);
            c.put("horario", verpertino);
            //c.put("foto", foto);
            db.insert("MAESTROS", null, c);
            db.close();*/
            Toast.makeText(this, "registro insersato", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    private void decodeBitmap(String dir) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);
        civMaestro.setImageBitmap(bitmap);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rbtn_matutino:
                horario = MATUTINO; break;
            case R.id.rbtn_vespertino: horario = VERPERTINO; break;
        }
    }

}
