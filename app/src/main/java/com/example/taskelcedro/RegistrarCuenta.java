package com.example.taskelcedro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class RegistrarCuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar_cuenta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.principal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }public void registrarEmpresa(View v){
        Intent i = new Intent(this, formularioEmpresa.class);
        startActivity(i);
    }public void salirPrincipal(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);}
    public void registrarCliente(View v){
        Intent i = new Intent(this, formularioCliente.class);
        startActivity(i);}
}

