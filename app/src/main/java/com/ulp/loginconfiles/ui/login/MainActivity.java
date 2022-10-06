package com.ulp.loginconfiles.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ulp.loginconfiles.R;
import com.ulp.loginconfiles.modelo.Usuario;
import com.ulp.loginconfiles.request.ApiClient;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail,etPassword;
    private Button btLogin, btRegistrarse;
    private MainActivityViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        inicializarVista();
    }

    private void inicializarVista() {
        etEmail= findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        btLogin =findViewById(R.id.btLogin);
        btRegistrarse =findViewById(R.id.btRegistrarse);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.login(etEmail.getText().toString(),etPassword.getText().toString());
            }
        });
        btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mv.registrarse();
            }
        });

    }
}