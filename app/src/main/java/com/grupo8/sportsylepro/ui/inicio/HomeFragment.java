package com.grupo8.sportsylepro.ui.inicio;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.common.annotation.NonNullApi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.grupo8.sportsylepro.MainActivity;
import com.grupo8.sportsylepro.R;
import com.grupo8.sportsylepro.VistaDetalle;
import com.grupo8.sportsylepro.databinding.FragmentHomeBinding;

import com.squareup.picasso.*;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    ArrayList<String> ArtImg=new ArrayList<String>();
    ArrayList<String> Description=new ArrayList<String>();
    DatabaseReference mData;
    HomeAdapter homeAdapter;


    @RequiresApi(api = 34)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final GridView gridView = binding.grdProductos;
        final TextView textView = binding.textHome;
        mData= FirebaseDatabase.getInstance().getReference("Products").child("Destacados");
        LoadDataFromFirebase();
        homeAdapter=new HomeAdapter(ArtImg,Description);
        gridView.setAdapter(homeAdapter);
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        gridView.setOnItemClickListener(this::onItemClick);
        return root;
    }
    @RequiresApi(api = 34)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        HomeAdapter item = (HomeAdapter) parent.getItemAtPosition(position);
        String desc;
        desc = Description.get(position);

        Intent intent = new Intent(getActivity(), VistaDetalle.class);
        intent.putExtra("Description",desc);
        startActivity(intent);
    }
    public class HomeAdapter extends BaseAdapter{
        ArrayList<String> ArtImg;
        ArrayList<String> Description;
        HomeAdapter(ArrayList<String> ArtImg, ArrayList<String> Description){
            this.ArtImg=ArtImg;
            this.Description=Description;

        }
        @Override
        public int getCount() {
            return this.ArtImg.size();
        }



        @Override
        public long getItemId(int position)
        {
            return Description.hashCode();
        }
        public HomeAdapter getItem(int id){

            return this;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.grid_view_custom, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.gridImage);
            TextView textView = (TextView) view.findViewById(R.id.gridText);
            final HomeAdapter item=getItem(position);
            textView.setText(Description.get(position));
            Picasso.get().load(ArtImg.get(position)).into(imageView);
            return view;
        }



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void LoadDataFromFirebase(){
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
               for (DataSnapshot ds : datasnapshot.getChildren()){
                   Description.add(ds.child("Titulo").getValue().toString());
                   ArtImg.add(ds.child("Imagen").getValue().toString());
               }
                homeAdapter.notifyDataSetChanged();  // THIS WILL NOTIFY YOUR ADAPTER WHEN DATA IS CHANGED
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

            @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}