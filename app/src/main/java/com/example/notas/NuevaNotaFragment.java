package com.example.notas;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.notas.db.entity.NotaEntity;

public class NuevaNotaFragment extends DialogFragment {



    public static NuevaNotaFragment newInstance() {
        return new NuevaNotaFragment();
    }

    private View view;
    private EditText etTitulo, etContenido;
    private RadioGroup rgColor;
    private Switch swNotaFavorita;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nueva nota");
        builder.setMessage("Introduzca los datos de la nueva nota")
                .setPositiveButton("Guardar la nota", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String titulo= etTitulo.getText().toString();
                        String contenido= etContenido.getText().toString();
                        String color ="azul";
                        switch (rgColor.getCheckedRadioButtonId())
                        {
                            case R.id.radioButtonColorRojo:
                                color="rojo"; break;
                            case R.id.radioButtonColorVerde:
                                    color="verde"; break;
                            case R.id.radioButtonColorAzul:
                                        color="azul"; break;
                        }
                        boolean favorita= swNotaFavorita.isChecked();

                        NuevaNotaViewModel mViewModel = ViewModelProviders.of(getActivity()).get(NuevaNotaViewModel.class);
                        mViewModel.insertarNota(new NotaEntity(titulo,contenido,favorita,color));
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });

        LayoutInflater inflater= getActivity().getLayoutInflater();
        view=inflater.inflate(R.layout.nueva_nota_fragment, null);

        etTitulo=view.findViewById(R.id.editTextTitulo);
        etContenido=view.findViewById(R.id.editTextContenido);
        rgColor= view.findViewById(R.id.radioGroupColor);
        swNotaFavorita=view.findViewById(R.id.switchNotaFavorita);

        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
