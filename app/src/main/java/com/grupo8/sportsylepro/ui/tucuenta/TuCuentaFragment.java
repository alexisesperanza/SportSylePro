package com.grupo8.sportsylepro.ui.tucuenta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.grupo8.sportsylepro.databinding.FragmentTucuentaBinding;

public class TuCuentaFragment extends Fragment {

    private FragmentTucuentaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TuCuentaViewModel tuCuentaViewModel =
                new ViewModelProvider(this).get(TuCuentaViewModel.class);

        binding = FragmentTucuentaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        tuCuentaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}