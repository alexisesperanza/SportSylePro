package com.grupo8.sportsylepro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class VistaDetalle extends AppCompatActivity {
    TextView txtNombre, txtDescripcion,txtPrecio;
    ImageView imgProducto;
    String rNombre,Categoria, rImagen, rDescripcion, rPrecio;

    public DatabaseReference dData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_detalle);
        txtNombre = (TextView) findViewById(R.id.txtData);
        imgProducto = findViewById(R.id.imvProducto);
        txtDescripcion=findViewById(R.id.txtDescription);
        imgProducto=findViewById(R.id.imvProducto);
        txtPrecio=findViewById(R.id.txtPrecio);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras==null){
                rNombre=null;
            } else{
                rNombre = extras.getString("Nombre");
                rImagen = extras.getString("Imagen");
                rDescripcion=extras.getString("Descripcion");
                rPrecio = extras.getString("Precio");
                txtPrecio.setText(rPrecio);
                txtNombre.setText(rNombre);
                txtDescripcion.setText(rDescripcion.toString());
                Picasso.get().load(rImagen).into(imgProducto);


            }
        }else{
            rNombre=(String) savedInstanceState.getSerializable("Nombre");
            txtNombre.setText(rNombre);
        }

    }
    public void close(View v){
    this.finish();
    }



}