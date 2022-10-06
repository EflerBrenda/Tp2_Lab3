package com.ulp.loginconfiles.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ulp.loginconfiles.R;
import com.ulp.loginconfiles.modelo.Usuario;
import com.ulp.loginconfiles.ui.login.MainActivityViewModel;

public class RegistroActivity extends AppCompatActivity {
    private EditText etDni,etNombre,etApellido,etEmailEditar,etPasswordEditar;
    private Button btGuardar;
    private RegistroActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        vm.getDniMutable().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                etDni.setText(aLong + "");
            }
        });
        vm.getNombreMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etNombre.setText(s);
            }
        });
        vm.getApellidoMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etApellido.setText(s);
            }
        });
        vm.getEmailMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etEmailEditar.setText(s);
            }
        });
        vm.getPasswordMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etPasswordEditar.setText(s);
            }
        });
        inicializarVista();
    }

    private void inicializarVista() {
        etDni= findViewById(R.id.etDni);
        etNombre= findViewById(R.id.etNombre);
        etApellido= findViewById(R.id.etApellido);
        etEmailEditar= findViewById(R.id.etEmailEditar);
        etPasswordEditar= findViewById(R.id.etPasswordEditar);
        btGuardar= findViewById(R.id.btGuardar);
        vm.leerUsuario(getIntent());
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long dni;
                if(etDni.getText().toString().equals("")){
                     dni =0;
                }
                else{ dni= Long.parseLong(etDni.getText().toString());}
                String nombre= etNombre.getText().toString();
                String apellido= etApellido.getText().toString();
                String email= etEmailEditar.getText().toString();
                String password= etPasswordEditar.getText().toString();
                Usuario u = new Usuario(dni,nombre,apellido,email,password);
                vm.guardarUsuario(u);
            }
        });
    }
}