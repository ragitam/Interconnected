package com.app.interconnected.Adapter;


import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.interconnected.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private ArrayList<OrganisasiAdapter> listGambar;
    private Activity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cv;
        private TextView mTextView;
        private ImageView mImage;

        public ViewHolder(View v) {
            super(v);
            cv=(CardView)v.findViewById(R.id.card_view);
            mTextView=(TextView)v.findViewById(R.id.org_name);
            mImage=(ImageView)v.findViewById(R.id.org_img);
        }
    }

    public RecycleAdapter(Activity activity,ArrayList<OrganisasiAdapter> list) {
        this.listGambar = list;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return listGambar.size();
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_org, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextView.setText(listGambar.get(position).getOrgName());
        AssetManager assetManager = activity.getAssets();
        InputStream is;
        try {
            is = assetManager.open(listGambar.get(position)+".png");
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            holder.mImage.setImageBitmap(bitmap);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
