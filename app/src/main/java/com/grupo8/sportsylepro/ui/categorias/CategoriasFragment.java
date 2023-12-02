package com.grupo8.sportsylepro.ui.categorias;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.grupo8.sportsylepro.databinding.FragmentCategoriasBinding;
import com.grupo8.sportsylepro.productos;

public class CategoriasFragment extends Fragment {

    private FragmentCategoriasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CategoriasViewModel categoriasViewModel =
                new ViewModelProvider(this).get(CategoriasViewModel.class);

        binding = FragmentCategoriasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        categoriasViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void toropaHombre(View v){

        String contexto="ropahombre";
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}