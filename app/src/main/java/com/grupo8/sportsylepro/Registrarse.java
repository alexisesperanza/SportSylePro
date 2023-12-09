package com.grupo8.sportsylepro;

import static android.app.ProgressDialog.show;
import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.grupo8.sportsylepro.databinding.FragmentRegistrarseBinding;

public class Registrarse extends Fragment {
    FirebaseAuth mAuth;
    private FragmentRegistrarseBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Registrarse() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Registrarse newInstance(String param1, String param2) {
        Registrarse fragment = new Registrarse();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrarseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        final Button btnRegistrarse = binding.btnRegistrarse;
        final EditText correo = binding.edtCorreo;
        final EditText nContr=binding.edtNuevaContra;

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (correo.getText().toString().isEmpty() ||  nContr.getText().toString().isEmpty()) {

                    Toast.makeText(getActivity(), "Por favor, asegurese de llenar todos los campos.",
                            Toast.LENGTH_SHORT).show();

            }
                else{
                    String email = correo.getText().toString();
                    String password = nContr.getText().toString();

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(getActivity(), "Usuario registrado con exito, inicia sesion",
                                                Toast.LENGTH_LONG).show();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(getActivity(), "Error al registrar usuario",
                                                Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                }
            }
        });


        // Inflate the layout for this fragment
        return root;
    }
}