package com.example.taskelcedro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.principal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void login(View v) {
        EditText campo1 = this.findViewById(R.id.id_usuario);
        String usuario = campo1.getText().toString();
        EditText campo2 = this.findViewById(R.id.contrasena);
        String contrasenia = campo2.getText().toString();

        // Verificar en la colección de clientes y luego en empresas si no se encuentra en clientes
        verificarUsuario("clientes", usuario, contrasenia, encontrado -> {
            if (encontrado) {
                iniciarSesion();
            } else {
                verificarUsuario("empresas", usuario, contrasenia, encontradoEmpresa -> {
                    if (encontradoEmpresa) {
                        iniciarSesion();
                    } else {
                        Toast.makeText(MainActivity.this, "Error de acreditación", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void verificarUsuario(String coleccion, String usuario, String contrasenia, OnUsuarioEncontradoCallback callback) {
        db.collection(coleccion)
                .whereEqualTo("usuario", usuario)
                .whereEqualTo("contrasena", contrasenia)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && !task.getResult().isEmpty()) {
                            callback.onUsuarioEncontrado(true);
                        } else {
                            callback.onUsuarioEncontrado(false);
                        }
                    }
                });
    }

    private void iniciarSesion() {
        Intent i = new Intent(MainActivity.this, principal1.class);
        startActivity(i);
        Toast.makeText(MainActivity.this, "Exito", Toast.LENGTH_SHORT).show();
    }

    public void crearCuenta(View v) {
        Intent i = new Intent(this, RegistrarCuenta.class);
        startActivity(i);
    }

    // Interfaz para manejar el callback
    interface OnUsuarioEncontradoCallback {
        void onUsuarioEncontrado(boolean encontrado);
    }
}


