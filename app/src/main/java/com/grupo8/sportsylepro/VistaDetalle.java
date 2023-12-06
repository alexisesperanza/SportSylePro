package com.grupo8.sportsylepro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class VistaDetalle extends AppCompatActivity {
    TextView txtDescription;
    String Received;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_detalle);
        txtDescription = (TextView) findViewById(R.id.txtData);
        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras==null){
                Received=null;
            } else{
                Received = extras.getString("Description");
                txtDescription.setText(Received);
            }
        }else{
            Received=(String) savedInstanceState.getSerializable("Description");
            txtDescription.setText(Received);
        }
    }
}