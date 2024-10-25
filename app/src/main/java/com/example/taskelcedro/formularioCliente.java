package com.example.taskelcedro;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class formularioCliente extends AppCompatActivity {
    private FirebaseFirestore db;
    private Button ingresarbtn;
    private EditText campo1, campo2, campo3, campo4, campo5, campo6, campo7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cliente);

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        // Inicializar los campos y el botón
        campo1 = findViewById(R.id.campoNombreCliente);
        campo2 = findViewById(R.id.campoApellido);
        campo3 = findViewById(R.id.campoDireccion);
        campo4 = findViewById(R.id.campoEmail);
        campo5 = findViewById(R.id.campoTelefono);
        campo6 = findViewById(R.id.campoUsuarioCL);
        campo7 = findViewById(R.id.campoContrasenaCL);
        ingresarbtn = findViewById(R.id.ingresarbtn);

        // Configurar listener para el botón
        ingresarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Capturar los datos de los campos
                String nombre = campo1.getText().toString().trim();
                String apellido = campo2.getText().toString().trim();
                String direccion = campo3.getText().toString().trim();
                String email = campo4.getText().toString().trim();
                String telefono = campo5.getText().toString().trim();
                String usuario = campo6.getText().toString().trim();
                String contrasena = campo7.getText().toString().trim();

                // Validar que los campos no estén vacíos
                if (nombre.isEmpty() || apellido.isEmpty() || direccion.isEmpty() || email.isEmpty() || telefono.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(formularioCliente.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Llamar a la función para guardar los datos en Firestore
                    guardarDatosEnFirestore(nombre, apellido, direccion, email, telefono, usuario, contrasena);
                }
            }
        });
    }

    // Función para guardar los datos en Firebase Firestore
    private void guardarDatosEnFirestore(String nombre, String apellido, String direccion, String email, String telefono, String usuario, String contrasena) {
        // Crear un mapa con los datos del formulario
        Map<String, Object> cliente = new HashMap<>();
        cliente.put("nombre", nombre);
        cliente.put("apellido", apellido);
        cliente.put("direccion", direccion);
        cliente.put("email", email);
        cliente.put("telefono", telefono);
        cliente.put("usuario", usuario);
        cliente.put("contrasena", contrasena);

        // Guardar los datos en Firestore en la colección "clientes"
        db.collection("clientes").add(cliente).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(formularioCliente.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                // Opcional: cerrar la actividad o limpiar los campos después de guardar
                limpiarCampos();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(formularioCliente.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para limpiar los campos después de guardar los datos
    private void limpiarCampos() {
        campo1.setText("");
        campo2.setText("");
        campo3.setText("");
        campo4.setText("");
        campo5.setText("");
        campo6.setText("");
        campo7.setText("");
    }
}
