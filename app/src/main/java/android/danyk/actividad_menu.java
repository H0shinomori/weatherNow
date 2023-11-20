package android.danyk;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class actividad_menu extends AppCompatActivity {
    private TextView textoBienvenida;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_menu);

        textoBienvenida = findViewById(R.id.textoEjemplo);

        Intent intent = getIntent();
        if (intent !=null && intent.hasExtra("USUARIO")) {
            String usuario = intent.getStringExtra("USUARIO");
            showWelcomeDialog(usuario);
            textoBienvenida.setText("Bienvenido, " + usuario);
        }
    }
    private void showWelcomeDialog(String usuario) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¡Bienvenido!")
                .setMessage("Hola, " + usuario + "! Bienvenido a nuestra aplicación.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}