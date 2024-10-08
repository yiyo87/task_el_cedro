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
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class formularioEmpresa extends AppCompatActivity {
    static final int GALLERY_INTENT = 1;
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //FirebaseApp.initializeApp(this);
        //setContentView(R.layout.activity_formulario_empresa);
        mStorage = FirebaseStorage.getInstance().getReference();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.principal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if(uri != null){
                StorageReference filePath = mStorage.child("fotos").child(uri.getLastPathSegment());
                filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "Exito al subir foto", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Toast.makeText(getApplicationContext(), "Error al subir foto", Toast.LENGTH_SHORT).show();
            }
        }
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

    /*public void cargar_imagen(View v) {
        permisos permiso = new permisos(this);
        if (permiso.checkPermissionREAD_EXTERNAL_STORAGE(this)) {
            Intent openPictureIntent = new Intent(Intent.ACTION_GET_CONTENT);
            openPictureIntent.setType("image/*");
            startActivityForResult(openPictureIntent, GALLERY_INTENT);
        }
    }*/
}
