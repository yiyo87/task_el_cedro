package com.example.taskelcedro;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class formularioEmpresa extends AppCompatActivity {
    static final int GALLERY_INTENT = 1;
    static final int PERMISSION_REQUEST_CODE = 100;
    private StorageReference mStorage;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_empresa);
        mStorage = FirebaseStorage.getInstance().getReference();
        db = FirebaseFirestore.getInstance();

    }
    public void camposFormularioEmpresa(View v) {
        EditText campo1 = findViewById(R.id.campoNombreCliente);
        String nombre = campo1.getText().toString().trim();  // .trim() elimina espacios en blanco al inicio y final
        EditText campo2 = findViewById(R.id.campoDireccion);
        String direccion = campo2.getText().toString().trim();
        EditText campo3 = findViewById(R.id.campoEmail);
        String email = campo3.getText().toString().trim();
        EditText campo4 = findViewById(R.id.campoTelefono);
        String telefono = campo4.getText().toString().trim();
        EditText campo5 = findViewById(R.id.campoValor);
        String valor = campo5.getText().toString().trim();
        EditText campo6  = findViewById(R.id.campoUsuarioCL);
        String usuario = campo6.getText().toString().trim();
        EditText campo7 = findViewById(R.id.campoContrasena);
        String contrasena = campo7.getText().toString().trim();

        // si los campos no estan llenos sale este mensaje
        if (nombre.isEmpty() || direccion.isEmpty() || email.isEmpty() || telefono.isEmpty() || valor.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            Map<String, Object> empresa = new HashMap<>();
            empresa.put("nombre", nombre);
            empresa.put("direccion", direccion);
            empresa.put("email", email);
            empresa.put("telefono", telefono);
            empresa.put("valor", valor);
            empresa.put("usuario", usuario);
            empresa.put("contrasena", contrasena);

            db.collection("empresas") // Reemplaza "clientes" con el nombre de tu colección
                    .add(empresa)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(formularioEmpresa.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();

                            // Puedes limpiar los campos EditText aquí si es necesario
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(formularioEmpresa.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                            // Maneja el error (por ejemplo, regístralo)
                        }
                    });

            // si los campos fueron rellenados sale el mensaje
            Toast.makeText(this, "Los datos han sido ingresados con éxito", Toast.LENGTH_SHORT).show();

            // Opcionalmente puedes imprimir los valores en la consola
            System.out.println("Datos ingresados: " + nombre + " " + direccion + " " + email+ " " + telefono+ " " + valor + " " + usuario + " " + contrasena);
        }

    }

    public void cargar_imagen(View v) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            abrirGaleria();
        }
    }

    private void abrirGaleria() {
        Intent openPictureIntent = new Intent(Intent.ACTION_PICK);
        openPictureIntent.setType("image/*");
        startActivityForResult(openPictureIntent, GALLERY_INTENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK && data != null) {
            subirImagen(data.getData());
        }
    }

    public void subirImagen(Uri uri) {
        String fileName = uri.getLastPathSegment();
        StorageReference filePath = mStorage.child("images/" + fileName);
        filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri downloadUri) {
                        Toast.makeText(getApplicationContext(), "Imagen cargada: " + downloadUri.toString(), Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al obtener la URL de la imagen", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            abrirGaleria();
        } else {
            Toast.makeText(this, "El permiso de almacenamiento fue denegado", Toast.LENGTH_SHORT).show();
        }
    }
}



