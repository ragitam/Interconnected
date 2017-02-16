package com.app.interconnected;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.interconnected.Adapter.OrganisasiAdapter;
import com.app.interconnected.Adapter.RecycleAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrganizationFragment extends Fragment {

    private RecycleAdapter rAdapter;
    private ArrayList<OrganisasiAdapter> listGambar = new ArrayList<OrganisasiAdapter>();
    private DatabaseReference ref;
    private FirebaseAuth mAuth;
    private View v;
    private TextView t;

    public OrganizationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment/

        FirebaseUser user = mAuth.getInstance().getCurrentUser();

        ref = FirebaseDatabase.getInstance().getReference().child("Data User");
        ref.keepSynced(true);

        v = inflater.inflate(R.layout.fragment_organization, container, false);

        t = (TextView) v.findViewById(R.id.org_name);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() == null){
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }

        RecyclerView rView = (RecyclerView) v.findViewById(R.id.recyclerView_org);
        rView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(getActivity(),2);
        rView.setLayoutManager(llm);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                listGambar.clear();
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    OrganisasiAdapter org = postSnapshot.getValue(OrganisasiAdapter.class);
                    listGambar.add(org);
                    Log.e("Organisasi",org.getOrganisasi());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), "No Data Found" +databaseError.toException(), Toast.LENGTH_SHORT).show();
            }
        });

        rAdapter = new RecycleAdapter(listGambar);
        rView.setAdapter(rAdapter);
        return v;
    }
}
