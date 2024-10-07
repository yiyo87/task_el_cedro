package com.example.taskelcedro;

import static androidx.core.app.PendingIntentCompat.getActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class principal1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal1);


        // Referencia al toolbar
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(tb);

        // Referencia al TabLayout
        TabLayout tl = (TabLayout) findViewById(R.id.tablayout);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int posicion = tab.getPosition();

                switch (posicion) {
                    case 0:
                        fragmento_crud_principal f = new fragmento_crud_principal();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, f).commit();
                        break;
                    case 1:
                        op2 op2 = new op2();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, op2).commit();
                        break;
                    case 2:
                        op3 op3 = new op3();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, op3).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        // Otras configuraciones de tu toolbar, drawer,
        NavigationView nav = (NavigationView) findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.salir) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                return false;
            }
        });

        // Configuración del drawer layout
        DrawerLayout dl = (DrawerLayout) findViewById(R.id.principal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, tb, R.string.abrir, R.string.cerrar);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dl.isDrawerOpen(GravityCompat.START)) {
                    dl.closeDrawer(GravityCompat.START);
                } else {
                    dl.openDrawer(GravityCompat.START);
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.principal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private Activity getActivity() {
        return null;
    }
}