package com.grupo8.sportsylepro.ui.categorias;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.grupo8.sportsylepro.VistaDetalle;
import com.grupo8.sportsylepro.databinding.FragmentCategoriasBinding;
import com.grupo8.sportsylepro.ui.VistaPorCategoria;

public class CategoriasFragment extends Fragment {

    private FragmentCategoriasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CategoriasViewModel categoriasViewModel =
                new ViewModelProvider(this).get(CategoriasViewModel.class);

        binding = FragmentCategoriasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        final TableRow RCaballero = binding.RCaballero;
        final TableRow RDama = binding.RDama;
        final TableRow RNino = binding.RNino;
        final TableRow ZCaballero = binding.ZCaballero;
        final TableRow ZDama = binding.ZDama;
        final TableRow ZNino = binding.ZNino;

        RCaballero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VistaPorCategoria.class);
                intent.putExtra("Categoria","Ropa Caballero");
                startActivity(intent);
            }
        });
        RDama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VistaPorCategoria.class);
                intent.putExtra("Categoria","Ropa Dama");
                startActivity(intent);
            }
        });
        RNino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VistaPorCategoria.class);
                intent.putExtra("Categoria","Ropa infantil");
                startActivity(intent);
            }
        });
        ZCaballero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VistaPorCategoria.class);
                intent.putExtra("Categoria","Zapato Caballero");
                startActivity(intent);
            }
        });
        ZDama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VistaPorCategoria.class);
                intent.putExtra("Categoria","Zapato Dama");
                startActivity(intent);
            }
        });
        ZNino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VistaPorCategoria.class);
                intent.putExtra("Categoria","Zapato infantil");
                startActivity(intent);
            }
        });
        categoriasViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}