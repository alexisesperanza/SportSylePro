package com.grupo8.sportsylepro.ui.tucuenta;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.grupo8.sportsylepro.LoginActivity;
import com.grupo8.sportsylepro.MainActivity;
import com.grupo8.sportsylepro.databinding.FragmentTucuentaBinding;

public class TuCuentaFragment extends Fragment {

    private FragmentTucuentaBinding binding;
    public FirebaseUser user;
    FirebaseUser userView = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        TuCuentaViewModel tuCuentaViewModel =
                new ViewModelProvider(this).get(TuCuentaViewModel.class);
        binding = FragmentTucuentaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button btnSalir = binding.btnCerrarSesion;
        user = FirebaseAuth.getInstance().getCurrentUser();
        final TextView txtTitulo = binding.txtTitulo;
        final TextView txtCorreo = binding.txtCorreo;
        final TextView Pre2= binding.txtPr2;
        mAuth = FirebaseAuth.getInstance();


        if (user != null) {

            String emailView = userView.getEmail();
            txtTitulo.setText("Sesion iniciada.");
            txtCorreo.setText(emailView);
            btnSalir.setText("Cerrar sesion");
            btnSalir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth.signOut();
                    Intent n = new Intent(getActivity(), MainActivity.class);
                    startActivity(n);
                    Toast.makeText(getContext(), "La sesion ha sido cerrada con exito.",
                            Toast.LENGTH_LONG).show();
                }
            });

        }
        else {
            btnSalir.setText("Iniciar sesion");
            txtTitulo.setText("Sesion no iniciada.");
            txtCorreo.setVisibility(View.INVISIBLE);
            Pre2.setVisibility(View.INVISIBLE);
            btnSalir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ini = new Intent(getActivity(),LoginActivity.class);
                    startActivity(ini);
                }
            });
        }


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