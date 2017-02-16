package com.app.interconnected;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AnggotaOrg extends AppCompatActivity {

    ListView list;
    ImageButton search;
    private ArrayList<String> dataSet;
    String[] nama = {"Avriza", "Agita", "Difa"};
    String[] jabatan = {"Ketua", "Wakil Ketua", "Wakil Ketua"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anggota_org);
        dataSet = new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter adt = new ArrayAdapter<String>(this,R.layout.list_anggota,nama);
        list = (ListView)findViewById(R.id.list_anggota);
        list.setAdapter(adt);
    }

}
