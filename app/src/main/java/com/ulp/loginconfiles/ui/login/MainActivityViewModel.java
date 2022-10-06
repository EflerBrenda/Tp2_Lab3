package com.ulp.loginconfiles.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.loginconfiles.modelo.Usuario;
import com.ulp.loginconfiles.request.ApiClient;
import com.ulp.loginconfiles.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {

    private Context contexto;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        contexto= application.getApplicationContext();
    }



    public void login (String email,String password){

            Usuario usuario = ApiClient.login(contexto, email, password);
            if (usuario != null) {

                Intent intent = new Intent(contexto, RegistroActivity.class);
                intent.putExtra("registro",0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                contexto.startActivity(intent);
            } else {
                Toast.makeText(contexto, "Email o password incorrectas.", Toast.LENGTH_SHORT).show();
            }

        }
    public void registrarse (){

        Intent intent = new Intent(contexto, RegistroActivity.class);
        intent.putExtra("registro",1);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        contexto.startActivity(intent);

    }



}
