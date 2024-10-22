package com.example.taskelcedro;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class formularioEmpresa extends AppCompatActivity {

    static final int GALLERY_INTENT = 1;
    static final int PERMISSION_REQUEST_CODE = 100;
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_empresa);
        mStorage = FirebaseStorage.getInstance().getReference();
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

    public void cargar_imagen(View v) {
        Log.d("FormularioEmpresa", "Botón presionado, iniciando carga de imagen");

        // Comprobar si el permiso de lectura está concedido
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Si no está concedido, solicitar el permiso
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            // Si el permiso ya fue concedido, abrir la galería
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
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri uri = data.getData();
                subirImagen(uri); // Llamada al método para subir la imagen
            } else {
                Toast.makeText(this, "No se seleccionó ninguna imagen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Método para subir la imagen a Firebase Storage
    private void subirImagen(Uri uri) {
        StorageReference filePath = mStorage.child("images/" + uri.getLastPathSegment());  // Ruta donde se almacenará la imagen

        UploadTask uploadTask = filePath.putFile(uri);

        // Listener para manejar éxito de la subida
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Obtener URL de descarga de la imagen
                filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri downloadUri) {
                        Toast.makeText(getApplicationContext(), "Imagen cargada: " + downloadUri.toString(), Toast.LENGTH_LONG).show();
                        Log.d("FormularioEmpresa", "URL de descarga: " + downloadUri.toString());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al obtener la URL de la imagen", Toast.LENGTH_SHORT).show();
                        Log.e("FormularioEmpresa", "Error al obtener la URL", e);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Manejo de error en la subida
                Toast.makeText(getApplicationContext(), "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
                Log.e("FormularioEmpresa", "Error al cargar la imagen", e);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirGaleria();
            } else {
                Toast.makeText(this, "El permiso de almacenamiento fue denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}



