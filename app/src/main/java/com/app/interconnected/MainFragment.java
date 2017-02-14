package com.app.interconnected;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.interconnected.Adapter.Kegiatan;
import com.app.interconnected.Adapter.OrganisasiAdapter;
import com.app.interconnected.Adapter.RecyclerViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Kegiatan> dataSet;
    private DatabaseReference ref;
    private FirebaseAuth mAuth;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataSet = new ArrayList<Kegiatan>();

        FirebaseUser user = mAuth.getInstance().getCurrentUser();

        ref = FirebaseDatabase.getInstance().getReference().child("Organisasi").child("Nama Organisasi");

        initDataset();

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        rvView = (RecyclerView) v.findViewById(R.id.recyclerView_kegiatan);
        rvView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        rvView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(dataSet);
        rvView.setAdapter(adapter);

        rvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DetailKegiatan.class));
            }
        });
        return v;
    }

    private void initDataset(){

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    Kegiatan keg = postSnapshot.getValue(Kegiatan.class);

                    dataSet.add(keg);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), "No Data Found" +databaseError.toException(), Toast.LENGTH_SHORT).show();
            }
        });

        /* dataSet.add("Karin");
        dataSet.add("Ingrid");
        dataSet.add("Helga");
        dataSet.add("Renate");
        dataSet.add("Elke");
        dataSet.add("Ursula");
        dataSet.add("Erika");
        dataSet.add("Christa");
        dataSet.add("Gisela");
        dataSet.add("Monika"); */

    }
}
