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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.principal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método login definido fuera de onActivityResult
    public void login(View v) {
        EditText campo1 = this.findViewById(R.id.id_usuario);
        String usuario = campo1.getText().toString();
        EditText campo2 = this.findViewById(R.id.contrasena);
        String contrasenia = campo2.getText().toString();

        if (usuario.equals("u1") && contrasenia.equals("123")) {
            Intent i = new Intent(this, principal1.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Error acreditación", Toast.LENGTH_SHORT).show();
        }

        System.out.println(usuario + " " + contrasenia);
    }

    // Método crearCuenta definido fuera de onActivityResult
    public void crearCuenta(View v) {
        Intent i = new Intent(this, RegistrarCuenta.class);
        startActivity(i);
    }
}
