package android.danyk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;


public class actividad_login extends AppCompatActivity {

    private EditText usuarioVentana, contraVentana;
    private Button botonLogin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_login);

         usuarioVentana = findViewById(R.id.usuario);
         contraVentana = findViewById(R.id.contra);
         botonLogin = findViewById(R.id.boton_login);

         botonLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String usuario = usuarioVentana.getText().toString();
                 String contra = contraVentana.getText().toString();
                 if (usuario.equals("Usuario") && contra.equals("1234")){
                     Intent login = new Intent(actividad_login.this, actividad_menu.class);
                     login.putExtra("USUARIO", usuario);
                     startActivity(login);
                 }else{
                     showErrorDialog(usuario);
                 }
             }
         });
    }
    private void showErrorDialog(String usuario) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage("EL usuario y/o la contraseña no son correctos")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
