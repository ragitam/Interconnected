package com.app.interconnected;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.interconnected.Adapter.RecycleAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrganizationFragment extends Fragment {

    private RecycleAdapter rAdapter;
    private ArrayList<String> listGambar;

    public OrganizationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        listGambar = new ArrayList<>();

        View v = inflater.inflate(R.layout.fragment_organization, container, false);

        RecyclerView rView = (RecyclerView) v.findViewById(R.id.recyclerView_org);
        rView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(getActivity(),2);
        rView.setLayoutManager(llm);

        ambidata();
        rAdapter = new RecycleAdapter(getActivity(),listGambar);
        rView.setAdapter(rAdapter);
        return v;
    }

    public void ambidata(){
        listGambar= new ArrayList<String>();

        listGambar.add("Himadif");
        listGambar.add("Om Ganteng");
        listGambar.add("Tim Ocon");
        listGambar.add("Panitia 17 Agustus");
    }
}
