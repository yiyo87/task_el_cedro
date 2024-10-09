package com.example.taskelcedro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;




public class formularioEmpresa extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_empresa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.principal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void paginaPrincipal(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void camposFormularioEmpresa(View v) {
        EditText campo1 = findViewById(R.id.campoNombreCliente);
        String nombre = campo1.getText().toString().trim();
        EditText campo2 = findViewById(R.id.campoDireccion);
        String direccion = campo2.getText().toString().trim();
        EditText campo3 = findViewById(R.id.campoEmail);
        String email = campo3.getText().toString().trim();
        EditText campo4 = findViewById(R.id.campoTelefono);
        String telefono = campo4.getText().toString().trim();
        EditText campo5 = findViewById(R.id.campoValor);
        String valor = campo5.getText().toString().trim();
        EditText campo6 = findViewById(R.id.campoUsuarioCL);
        String usuario = campo6.getText().toString().trim();
        EditText campo7 = findViewById(R.id.campoContrasena);
        String contrasena = campo7.getText().toString().trim();

        if (nombre.isEmpty() || direccion.isEmpty() || email.isEmpty() || telefono.isEmpty() || valor.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Los datos han sido ingresados con éxito", Toast.LENGTH_SHORT).show();
        }
    }
}
