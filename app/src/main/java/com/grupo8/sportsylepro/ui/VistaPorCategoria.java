package com.grupo8.sportsylepro.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.grupo8.sportsylepro.R;
import com.grupo8.sportsylepro.VistaDetalle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VistaPorCategoria extends AppCompatActivity {
    TextView txtData;
    GridView grdProd;
    ArrayList<String> cArtImg =new ArrayList<String>();
    ArrayList<String> cName =new ArrayList<String>();
    ArrayList<String> cPrecio =new ArrayList<String>();
    ArrayList<String> cDescription =new ArrayList<String>();
    DatabaseReference cData;
    CatAdapter catAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_por_categoria);
        Bundle extras = getIntent().getExtras();
        String data = extras.getString("Categoria");
        grdProd = findViewById(R.id.grdProductoCat);
        catAdapter = new CatAdapter(cArtImg, cName, cPrecio);
        grdProd.setAdapter(catAdapter);
        grdProd.setOnItemClickListener(this::onItemClick);

        if (extras==null){
            data=null;
        } else {


            cData = FirebaseDatabase.getInstance().getReference("Products").child(data);
            LoadDataFromFirebase();


        }

    }

    private void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CatAdapter item = (CatAdapter) parent.getItemAtPosition(position);
        String Nombre;
        Nombre = cName.get(position);
        String ImagenArt = cArtImg.get(position);
        String Desc = cDescription.get(position);
        String precio = cPrecio.get(position);
        Intent intent = new Intent(this, VistaDetalle.class);
        intent.putExtra("Nombre",Nombre);
        intent.putExtra("Imagen",ImagenArt);
        intent.putExtra("Descripcion",Desc);
        intent.putExtra("Precio",precio);
        startActivity(intent);
    }
    public class CatAdapter extends BaseAdapter {

        ArrayList<String> ArtImg;
        ArrayList<String> Description;
        ArrayList<String> Precio;
        CatAdapter(ArrayList<String> ArtImg, ArrayList<String> Description, ArrayList<String> Precio){
            this.ArtImg=ArtImg;
            this.Description=Description;
            this.Precio=Precio;

        }
        @Override
        public int getCount() {
            return this.ArtImg.size();
        }


        @Override
        public long getItemId(int position) {
            return Description.hashCode();
        }
        @Override
        public CatAdapter getItem(int id){
            return this;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.grid_view_custom, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.gridImage);
            TextView textView = (TextView) view.findViewById(R.id.gridText);
            final CatAdapter item= (CatAdapter) getItem(position);
            textView.setText(Description.get(position));
            Picasso.get().load(ArtImg.get(position)).into(imageView);
            return view;
        }
    }
    public void LoadDataFromFirebase(){
        cData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot cs : snapshot.getChildren()){

                    cName.add(cs.child("Titulo").getValue().toString());
                    cArtImg.add(cs.child("Imagen").getValue().toString());
                    cDescription.add(cs.child("Descripcion").getValue().toString());
                    cPrecio.add(cs.child("Precio").getValue().toString());
                }
                catAdapter.notifyDataSetChanged();  // THIS WILL NOTIFY YOUR ADAPTER WHEN DATA IS CHANGED

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void cerrar(View v){
        this.finish();
    }
}