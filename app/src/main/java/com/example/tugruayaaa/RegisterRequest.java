package com.example.tugruayaaa;

import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import com.android.volley.RequestTask;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL ="https://192.168.1.76/tugrua/registrarse.php";
    private Map<String,String> params;
    public RegisterRequest(String nombreUsuario, String email, String contrasena, int documento, int telefono, String placa, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("nombreUsuario",nombreUsuario);
        params.put("email",email);
        params.put("contrasena",contrasena);
        params.put("documento",documento+"");
        params.put("telefono",telefono+"");
        params.put("placa",placa);

    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
