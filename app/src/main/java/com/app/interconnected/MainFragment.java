package com.app.interconnected;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.interconnected.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataSet = new ArrayList<>();
        initDataset();

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        rvView = (RecyclerView) v.findViewById(R.id.recyclerView_kegiatan);
        rvView.setHasFixedSize(true);

        /**
         * Kita menggunakan LinearLayoutManager untuk list standar
         * yang hanya berisi daftar item
         * disusun dari atas ke bawah
         */
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

        /**
         * Tambahkan item ke dataset
         * dalam prakteknya bisa bermacam2
         * tidak hanya String seperti di kasus ini
         */
        dataSet.add("Karin");
        dataSet.add("Ingrid");
        dataSet.add("Helga");
        dataSet.add("Renate");
        dataSet.add("Elke");
        dataSet.add("Ursula");
        dataSet.add("Erika");
        dataSet.add("Christa");
        dataSet.add("Gisela");
        dataSet.add("Monika");

    }
}
