package cinepoilisklic.com.ia.elcirculodelexito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AsesoriasAlumnoActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "id";
    public int id;
    TextView txtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asesorias_alumno);

        Intent intent = getIntent();
        if(savedInstanceState == null){
            if(intent.hasExtra(EXTRA_ID)){
                id = intent.getIntExtra(EXTRA_ID , 22);
                txtId = (TextView)findViewById(R.id.ash);
                txtId.setText(String.valueOf(id));
            }
        }


    }


}
