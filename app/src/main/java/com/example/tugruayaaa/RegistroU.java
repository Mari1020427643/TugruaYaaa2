package com.example.tugruayaaa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroU extends AppCompatActivity implements View.OnClickListener {
    EditText etnombre, etemail, etcontrasena, etdocumento, ettelefono, etplaca;
    Button btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_u);
        etnombre = (EditText) findViewById(R.id.editT_nombre);
        etemail = (EditText) findViewById(R.id.editT_email);
        etcontrasena = (EditText) findViewById(R.id.editT_pass);
        etdocumento = (EditText) findViewById(R.id.edit_doc);
        ettelefono = (EditText) findViewById(R.id.editT_tel);
        etplaca= (EditText) findViewById(R.id.editT_placa);

        btn_registrar = (Button) findViewById(R.id.btn_Registrar);

        btn_registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String nombreUsuario = etnombre.getText().toString();
        final String email = etemail.getText().toString();
        final String contrasena = etcontrasena.getText().toString();
        final int documento = Integer.parseInt(etdocumento.getText().toString());
        final int telefono = Integer.parseInt(ettelefono.getText().toString());
        final String placa = etplaca.getText().toString();

        Response.Listener<String> respolistener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    boolean succes;
                    if(success) {
                        Intent intent = new Intent(RegistroU.this, MainActivity.class);
                        RegistroU.this.startActivity(intent);

                    }else {
                        AlertDialog.Builder builder= new AlertDialog.Builder(RegistroU.this);
                        builder.setMessage("Error registro")
                                .setNegativeButton("Retry",null)
                                .create().show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        RegisterRequest registerRequest = new RegisterRequest(nombreUsuario, email, contrasena, documento, telefono, placa, respolistener);
        RequestQueue queue = Volley.newRequestQueue(RegistroU.this);
        queue.add(registerRequest);



    }
}