package com.example.echagaray.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.loopj.android.http.AsyncHttpClient;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.auth.AuthScope;
import com.loopj.android.http.*;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String Usuario,Contraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void formalOnClick(View view){
        enviarDatos("industrial","prusoft");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void enviarDatos(String Usuario, String Contraseña){

        AsyncHttpClient cliente = new AsyncHttpClient();
        String url = "http://itculiacan.edu.mx/prusoft/Mantenimiento/data/api/entrada.php?usuario=industrial&clave=prusoft";
        String parametros = "usuario=" +Usuario + "password="+Contraseña ;
        cliente.post(url + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                //Aqui va el # de status

                if (statusCode == 200) {
                    String resultado = new String(responseBody);
                    Toast.makeText(MainActivity.this, "SUCCESS" + resultado, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),LoginMain2Activity.class));



                }
                else {
                    String resultado = new String(responseBody);
                    Toast.makeText(MainActivity.this, "Contraseña incorrecta" + resultado, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }

            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });


    }

}
