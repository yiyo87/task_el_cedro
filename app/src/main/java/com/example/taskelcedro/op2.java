package com.example.taskelcedro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link op2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class op2 extends Fragment {

    public op2() {
        // Required empty public constructor
    }

    public static op2 newInstance(String param1, String param2) {
        op2 fragment = new op2();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_op2, container, false);

        // Obtener el botón por su ID y asignar el onClickListener
        //botones para ver la informacion de la empresa
        Button btnInfoCliente = view.findViewById(R.id.btnVerInfo);
        btnInfoCliente.setOnClickListener(v -> {
            info_cliente();  // Llamar a la función que maneja la navegación
        });
        Button btnInfoKaukari = view.findViewById(R.id.btninfokaukari);
        btnInfoKaukari.setOnClickListener(v -> {
            info_kaukari();  // Llamar a la función que maneja la navegación
        });
        Button btnentrelagos = view.findViewById(R.id.btnentrelagos);
        btnentrelagos.setOnClickListener(v -> {
            info_entrelagos();  // Llamar a la función que maneja la navegación
        });
        Button btnelcedro = view.findViewById(R.id.btnelcedro);
        btnelcedro.setOnClickListener(v -> {
            info_elcedro();  // Llamar a la función que maneja la navegación
        });

        // botones de para hacer los pedidos
        Button btnpedido1 = view.findViewById(R.id.btnpedido1);
        btnpedido1.setOnClickListener(v -> {
            fragmento_pedido();  // Llamar a la función que maneja la navegación
        });
        Button btnpedido2 = view.findViewById(R.id.btnpedido2);
        btnpedido2.setOnClickListener(v -> {
            fragmento_pedido2();  // Llamar a la función que maneja la navegación
        });
        Button btnpedido3 = view.findViewById(R.id.btnpedido3);
        btnpedido3.setOnClickListener(v -> {
            fragmento_pedido3();  // Llamar a la función que maneja la navegación
        });
        Button btnpedido4 = view.findViewById(R.id.btnpedido4);
        btnpedido4.setOnClickListener(v -> {
            fragmento_pedido3();  // Llamar a la función que maneja la navegación
        });

        return view;
    }

    // Función para navegar al fragmento de información del cliente
    public void info_cliente() {
        fragmento_info_cliente fragment = new fragmento_info_cliente();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contenedor, fragment); // Reemplaza con el ID del contenedor en tu actividad
        transaction.addToBackStack(null);  // Añadir a la pila para poder regresar
        transaction.commit();
    }

    public void info_kaukari() {
        fragmento_kaukari fragment = new fragmento_kaukari();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contenedor, fragment); // Reemplaza con el ID del contenedor en tu actividad
        transaction.addToBackStack(null);  // Añadir a la pila para poder regresar
        transaction.commit();
    }

    public void info_entrelagos() {
        fragmento_entrelagos fragment = new fragmento_entrelagos();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contenedor, fragment); // Reemplaza con el ID del contenedor en tu actividad
        transaction.addToBackStack(null);  // Añadir a la pila para poder regresar
        transaction.commit();
    }

    public void info_elcedro() {
        fragmento_elcedro fragment = new fragmento_elcedro();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contenedor, fragment); // Reemplaza con el ID del contenedor en tu actividad
        transaction.addToBackStack(null);  // Añadir a la pila para poder regresar
        transaction.commit();
    }

    // fragmentos para los pedidos
    public void fragmento_pedido() {
        fragmento_pedido fragment = new fragmento_pedido();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contenedor, fragment); // Reemplaza con el ID del contenedor en tu actividad
        transaction.addToBackStack(null);  // Añadir a la pila para poder regresar
        transaction.commit();
    }

    public void fragmento_pedido2() {
        fragmento_pedido2 fragment = new fragmento_pedido2();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contenedor, fragment); // Reemplaza con el ID del contenedor en tu actividad
        transaction.addToBackStack(null);  // Añadir a la pila para poder regresar
        transaction.commit();
    }

    public void fragmento_pedido3() {
        fragmento_pedido3 fragment = new fragmento_pedido3();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contenedor, fragment); // Reemplaza con el ID del contenedor en tu actividad
        transaction.addToBackStack(null);  // Añadir a la pila para poder regresar
        transaction.commit();
    }

    public void fragmento_pedido4() {
        fragmento_pedido4 fragment = new fragmento_pedido4();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contenedor, fragment); // Reemplaza con el ID del contenedor en tu actividad
        transaction.addToBackStack(null);  // Añadir a la pila para poder regresar
        transaction.commit();

    }
}
