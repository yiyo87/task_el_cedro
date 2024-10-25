package com.example.taskelcedro;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskelcedro.MainActivity;
import com.example.taskelcedro.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.util.HashMap;
import java.util.Map;

public class formularioCliente extends AppCompatActivity {
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_cliente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.principal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = FirebaseFirestore.getInstance();

    }public void paginaPrincipal(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }public void camposFormularioCliente(View v) {
        EditText campo1 = findViewById(R.id.campoNombreCliente);
        String nombre = campo1.getText().toString().trim();  // .trim() elimina espacios en blanco al inicio y final
        EditText campo2 = findViewById(R.id.campoApellido);
        String apellido = campo2.getText().toString().trim();
        EditText campo3 = findViewById(R.id.campoDireccion);
        String direccion = campo3.getText().toString().trim();
        EditText campo4 = findViewById(R.id.campoEmail);
        String email = campo4.getText().toString().trim();
        EditText campo5 = findViewById(R.id.campoTelefono);
        String telefono = campo5.getText().toString().trim();
        EditText campo6 = findViewById(R.id.campoUsuarioCL);
        String usuario = campo6.getText().toString().trim();
        EditText campo7 = findViewById(R.id.campoContrasenaCL);
        String contrasena = campo7.getText().toString().trim();

        // si los campos no estan llenos sale este mensaje
        if (nombre.isEmpty() || apellido.isEmpty() || direccion.isEmpty() || email.isEmpty() || telefono.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            Map<String, Object> cliente = new HashMap<>();
            cliente.put("nombre", nombre);
            cliente.put("apellido", apellido);
            cliente.put("direccion", direccion);
            cliente.put("email", email);
            cliente.put("telefono", telefono);
            cliente.put("usuario", usuario);
            cliente.put("contrasena", contrasena);

            db.collection("clientes") // Reemplaza "clientes" con el nombre de tu colección
                    .add(cliente)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(formularioCliente.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();

                            // Puedes limpiar los campos EditText aquí si es necesario
                                              }
                                          }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(formularioCliente.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                    // Maneja el error (por ejemplo, regístralo)
                }
            });

            // si los campos fueron rellenados sale el mensaje
            Toast.makeText(this, "Los datos han sido ingresados con éxito", Toast.LENGTH_SHORT).show();

            // Opcionalmente puedes imprimir los valores en la consola
            System.out.println("Datos ingresados: " + nombre + " " + apellido + " " + direccion + " " + email + " " + telefono + " " + usuario + " " + contrasena);
        }

    }}
