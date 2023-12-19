package android.danyk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class actividad_login extends AppCompatActivity {

    private EditText usuarioVentana, contraVentana;
    private Button botonLogin, botonRegistro;
    private BaseDatosAyuda dbAyuda;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_login);

        dbAyuda = new BaseDatosAyuda(this, "usuarios", null, 1);

        usuarioVentana = findViewById(R.id.usuario);
        contraVentana = findViewById(R.id.contra);
        botonLogin = findViewById(R.id.boton_login);
        botonRegistro = findViewById(R.id.boton_registro);

        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuarioIntroducido = usuarioVentana.getText().toString();
                String contraIntroducida = contraVentana.getText().toString();

                boolean inicioCorrecto = comprobarInicio(usuarioIntroducido, contraIntroducida);

                if (inicioCorrecto) {
                    Intent intent = new Intent(actividad_login.this, actividad_menu.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Inicio Correcto", Toast.LENGTH_SHORT).show();
                    usuarioVentana.setText("");
                    contraVentana.setText("");

                } else {
                    Toast.makeText(getApplicationContext(), "Usuario o contraseña no validos", Toast.LENGTH_SHORT).show();
                }


            }
        });
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro = new Intent(actividad_login.this, ActividadRegistroActivity.class);
                startActivity(registro);
            }
        });
    }

    @SuppressLint("Recycle")
    public boolean comprobarInicio(String usuario, String contraseña) {
        SQLiteDatabase db = dbAyuda.getReadableDatabase();
        if (db == null) {
            return false;
        }

        Cursor cursor = dbAyuda.getUsuarioPorDatos(usuario, contraseña);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
