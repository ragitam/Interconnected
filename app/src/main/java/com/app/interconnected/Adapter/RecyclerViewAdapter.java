package com.app.interconnected.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.interconnected.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Kegiatan> rvData;

    public RecyclerViewAdapter(ArrayList<Kegiatan> inputData) {
        rvData = inputData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView namaKegiatan;
        public TextView namaOrganisasi;
        public TextView presentase;

        public ViewHolder(View v) {
            super(v);
            namaKegiatan = (TextView) v.findViewById(R.id.nama_kegiatan);
            namaOrganisasi = (TextView) v.findViewById(R.id.nama_organisa);
            presentase = (TextView) v.findViewById(R.id.angka_persentase);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Kegiatan name = rvData.get(position);
        holder.namaKegiatan.setText(rvData.get(position).getNamaKegiatan());
        holder.namaOrganisasi.setText(rvData.get(position).getNamaOrg());
        holder.presentase.setText("Frau " + position);
    }

    @Override
    public int getItemCount() {
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return rvData.size();
    }
}