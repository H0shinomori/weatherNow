package android.danyk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadRegistroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_registro);

        EditText nombre, apellido, usuario, contra, email, ciudad;
        BaseDatosAyuda db;
        Button registrarse = findViewById(R.id.boton_registro);
        
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        usuario = findViewById(R.id.usuario);
        contra = findViewById(R.id.contra);
        email = findViewById(R.id.email);
        ciudad = findViewById(R.id.ciudad);

        db = new BaseDatosAyuda(ActividadRegistroActivity.this, "usuarios",null,1);
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String persona_nombre = nombre.getText().toString();
                String persona_apellido = apellido.getText().toString();
                String persona_usuario = usuario.getText().toString();
                String persona_contraseña = contra.getText().toString();
                String persona_email = email.getText().toString();
                String persona_ciudad = ciudad.getText().toString();
                long recordid = db.personaGuardada(persona_nombre,persona_apellido,persona_usuario,persona_contraseña,persona_email,persona_ciudad);
                if(recordid > 0){
                    Toast.makeText(getApplicationContext(), "Guardado correctamente", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Guardado correctamente", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}