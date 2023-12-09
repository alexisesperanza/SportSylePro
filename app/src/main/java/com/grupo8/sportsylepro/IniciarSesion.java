package com.grupo8.sportsylepro;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.grupo8.sportsylepro.databinding.FragmentIniciarSesionBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IniciarSesion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IniciarSesion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseAuth mAuth;

    private FragmentIniciarSesionBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1="Si";
    private String mParam2="No";

    public IniciarSesion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IniciarSesion.
     */
    // TODO: Rename and change types and number of parameters
    public static IniciarSesion newInstance(String param1, String param2) {
        IniciarSesion fragment = new IniciarSesion();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentIniciarSesionBinding.inflate(inflater, container,false);
        View root = binding.getRoot();
        final Button iniciarSesion = binding.btIniciarSesion;
        final EditText correo = binding.edtCorreo3;
        final EditText contra = binding.edtContra3;


        mAuth = FirebaseAuth.getInstance();

        iniciarSesion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (correo.getText().toString().isEmpty() || contra.getText().toString().isEmpty()){

                    Toast.makeText(getActivity(), "Por favor, asegurese de llenar todos los campos.",
                            Toast.LENGTH_SHORT).show();
              }
                else{

                    // [START sign_in_with_email]
                    String email = correo.getText().toString();
                    String password = contra.getText().toString();
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(getActivity(), "Bienvenido, " + email,
                                                Toast.LENGTH_SHORT).show();
                                        MainActivity m = new MainActivity();
                                        m.usuario = email;
                                        Intent bridge = new Intent(getActivity(), MainActivity.class);
                                        startActivity(bridge);
                                        Toast.makeText(getContext(), "Sesion iniciada con exito.",
                                                Toast.LENGTH_LONG).show();


                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(getActivity(), "Usuario o contraseña incorrectos.",
                                                Toast.LENGTH_SHORT).show();

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