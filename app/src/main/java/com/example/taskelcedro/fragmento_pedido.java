package com.example.taskelcedro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmento_pedido#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmento_pedido extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final double PRECIO_POR_BIDON = 1500;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragmento_pedido() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmento_pedido.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmento_pedido newInstance(String param1, String param2) {
        fragmento_pedido fragment = new fragmento_pedido();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento_pedido, container, false);
        EditText nombreInput = view.findViewById(R.id.editTextText9);         // Campo de nombre
        EditText direccionInput = view.findViewById(R.id.editTextText10);     // Campo de dirección
        EditText telefonoInput = view.findViewById(R.id.editTextText11);      // Campo de teléfono
        EditText cantidadBidonesInput = view.findViewById(R.id.editTextNumber); // Campo de cantidad de bidones
        EditText valorPagarInput = view.findViewById(R.id.editTextText13);    // Campo de valor a pagar
        Button btnHacerPedido = view.findViewById(R.id.btnhacerpedido);

        cantidadBidonesInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String cantidadBidonesStr = cantidadBidonesInput.getText().toString();

                if (!cantidadBidonesStr.isEmpty()) {
                    try {
                        int cantidadBidones = Integer.parseInt(cantidadBidonesStr);

                        double valorPagar = cantidadBidones * PRECIO_POR_BIDON;

                        valorPagarInput.setText(String.valueOf(valorPagar));
                    } catch (NumberFormatException e) {
                        valorPagarInput.setText("0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btnHacerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verificamos si los campos están vacíos
                if (nombreInput.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "El campo de nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (direccionInput.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "El campo de dirección no puede estar vacío", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (telefonoInput.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "El campo de teléfono no puede estar vacío", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (cantidadBidonesInput.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Debe ingresar la cantidad de bidones", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Si todos los campos están llenos, mostramos el mensaje de pedido realizado
                Toast.makeText(getActivity(), "Su pedido fue enviado", Toast.LENGTH_SHORT).show();
            }


        });
        return view;
    }
}