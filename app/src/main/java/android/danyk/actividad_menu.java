package android.danyk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class actividad_menu extends AppCompatActivity {
    TextView temp, cielo, lluvia, hum, vient;
    Button botonWebView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_menu);
        verificacionInternet();
        datos();
        temp= findViewById(R.id.temperatura);
        cielo = findViewById(R.id.estadoCielo);
        lluvia = findViewById(R.id.precipitacion);
        hum = findViewById(R.id.humedad);
        vient = findViewById(R.id.viento);
        botonWebView = findViewById(R.id.boton_webverView);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView volverInicio = findViewById(R.id.imagenFlecha);
        volverInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(actividad_menu.this, actividad_login.class);
                startActivity(intent);
            }
        });
        botonWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(actividad_menu.this, actividad_webView.class);
                startActivity(intent);
            }
        });

    }


    public void datos() {
        RequestQueue cola = Volley.newRequestQueue(this);
        String url = "https://www.el-tiempo.net/api/json/v2/provincias/08/municipios/08101";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        cola.add(stringRequest);
    }

    private void parseJson(String response) {

        try {

            JSONObject objetosObj = new JSONObject(response);

            String temperatura = objetosObj.getString("temperatura_actual");
            String temperaturaFinal = String.format(getString(R.string.tempGrados), temperatura);

            String humedad = objetosObj.getString("humedad");
            @SuppressLint({"StringFormatInvalid", "LocalSuppress"})
            String humedadFinal = String.format(getString(R.string.porHumedad), humedad);

            JSONObject estadoCielo = objetosObj.getJSONObject("stateSky");
            ImageView imagenEstado = findViewById(R.id.imagenCielo);
            if("Despejado".equals(estadoCielo)){
                imagenEstado.setImageResource(R.drawable.sol);
            }else{
                imagenEstado.setImageResource(R.drawable.estadocielo);
            }

            String subetiquetaCielo = estadoCielo.getString("description");

            String precipitacion = objetosObj.getString("precipitacion");
            @SuppressLint({"StringFormatInvalid", "LocalSuppress"})
            String precipitacionFinal = String.format(getString(R.string.porPrecipitacion), precipitacion);

            String viento = objetosObj.getString("viento");
            @SuppressLint({"StringFormatInvalid", "LocalSuppress"})
            String vientoFinal = String.format(getString(R.string.kmViento), viento);



            temp.setText(temperaturaFinal);
            cielo.setText(subetiquetaCielo);
            lluvia.setText(precipitacionFinal);
            hum.setText(humedadFinal);
            vient.setText(vientoFinal);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void verificacionInternet() {
        ConnectivityManager conectividad = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo internetInfo = conectividad.getActiveNetworkInfo();
        if (internetInfo != null && internetInfo.isConnected()) {
            Toast.makeText(getApplicationContext(), "Conexion ON", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Conexion OFF", Toast.LENGTH_SHORT).show();
        }
    }
}

