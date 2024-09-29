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
        Button btnInfoCliente = view.findViewById(R.id.btnVerInfo); // Asegúrate de que el ID coincide
        btnInfoCliente.setOnClickListener(v -> {
            info_cliente();  // Llamar a la función que maneja la navegación
        });

        return view;
    }

    // Función para navegar al fragmento de información del cliente
    public void info_cliente() {
        fragmento_info_cliente fragment = new fragmento_info_cliente();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.principal, fragment); // Reemplaza con el ID del contenedor en tu actividad
        transaction.addToBackStack(null);  // Añadir a la pila para poder regresar
        transaction.commit();
    }
}

