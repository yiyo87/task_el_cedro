package com.example.taskelcedro;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class principal1 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal1);

        //referencia al toolbar, la seccion de arriba va a funcionar como un toolbar
        Toolbar tb =(Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(tb);
        //referencia al tableloyout
        TabLayout tl = (TabLayout) findViewById(R.id.tablayout);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // codificar cosas a ajecutar cuando le das tab un tab
                int posicion = tab.getPosition();

                switch (posicion){
                    case 0:
                        //llamar al fragmento informacion
                        fragmentoInformacion f =new fragmentoInformacion();
                        //hacemos que se muestre el framgmento dentro de contenedor
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,f).commit();
                        break;

                        case 1:
                        //llamar al fragmento op2
                            op2 op2 = new op2();
                            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,op2).commit();
                        break;

                        case 2:
                        //llamar al fragmento op3
                        op3 op3 = new op3();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,op3).commit();
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //evento cuando codificar cosas a ejecutar untab deja de esta selccionado
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //codificar cosas a ejecutar cuando un tab se vuelve a seleccionar
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

    }

    @Override//vamos a incorporar dentro de nuestra vista el menu
    public boolean onCreateOptionsMenu(Menu menu) {//incorporar el menu dentro del activity
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();// recuperar el id de la opcion seleccionada
        if (id == R.id.buscar ){
            Toast.makeText(this,"buscando",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}