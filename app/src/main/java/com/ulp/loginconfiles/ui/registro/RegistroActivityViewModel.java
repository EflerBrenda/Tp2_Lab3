package com.ulp.loginconfiles.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.loginconfiles.modelo.Usuario;
import com.ulp.loginconfiles.request.ApiClient;

public class RegistroActivityViewModel extends AndroidViewModel {
    private Context contexto;
    private MutableLiveData<Long> dniMutable;
    private MutableLiveData<String> nombreMutable;
    private MutableLiveData<String> apellidoMutable;
    private MutableLiveData<String> emailMutable;
    private MutableLiveData<String> passwordMutable;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        contexto= application.getApplicationContext();
    }
    public LiveData<String> getNombreMutable(){
        if(this.nombreMutable == null){
            this.nombreMutable = new MutableLiveData<>();
        }
        return nombreMutable;
    }
    public LiveData<String> getApellidoMutable(){
        if(this.apellidoMutable == null){
            this.apellidoMutable = new MutableLiveData<>();
        }
        return apellidoMutable;
    }
    public LiveData<String> getEmailMutable(){
        if(this.emailMutable == null){
            this.emailMutable = new MutableLiveData<>();
        }
        return emailMutable;
    }
    public LiveData<String> getPasswordMutable(){
        if(this.passwordMutable == null){
            this.passwordMutable = new MutableLiveData<>();
        }
        return passwordMutable;
    }
    public LiveData<Long> getDniMutable(){
        if(this.dniMutable == null){
            this.dniMutable = new MutableLiveData<>();
        }
        return dniMutable;
    }

    public void leerUsuario(Intent intent){
        Usuario usuario = ApiClient.leer(contexto);
        if(intent.getIntExtra("registro",-1) == 0){
        if (usuario.getApellido() != "null"){
            long dni = usuario.getDni();
            String nombre = usuario.getNombre();
            String apellido = usuario.getApellido();
            String email = usuario.getEmail();
            String contraseña = usuario.getPassword();
            this.dniMutable.setValue(dni);
            this.nombreMutable.setValue(nombre);
            this.apellidoMutable.setValue(apellido);
            this.emailMutable.setValue(email);
            this.passwordMutable.setValue(contraseña);
        }}
    }

    public void guardarUsuario(Usuario usuario){
        long dni= usuario.getDni();
        if(usuario.getApellido().equals("") || usuario.getNombre().equals("") || usuario.getPassword().equals("") || usuario.getEmail().equals("") || dni == 0){
            Toast.makeText(contexto, "Por favor, complete los campos antes de guardar.", Toast.LENGTH_SHORT).show();
        }
        else{
            ApiClient.guardar(contexto,usuario);
            Toast.makeText(contexto, "Usuario creado con exito.", Toast.LENGTH_SHORT).show();
        }
    }

}
