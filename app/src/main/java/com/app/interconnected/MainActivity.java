package com.app.interconnected;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.interconnected.Adapter.Kegiatan;
import com.app.interconnected.Adapter.OrganisasiAdapter;
import com.app.interconnected.Adapter.UserInformation;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private DatabaseReference ref, reff;

    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment fragment = new MainFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() == null){
            startActivity(new Intent(this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ref = FirebaseDatabase.getInstance().getReference();
        ref.keepSynced(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //How to change elements in the header programatically
        View headerView = navigationView.getHeaderView(0);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_add){
            LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
            View v=inflater.inflate(R.layout.add_kegiatan,null);
            final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setView(v);

            final EditText namaKegiatan = (EditText)v.findViewById(R.id.nama_kegiatan);
            final EditText namaOrganisasi = (EditText)v.findViewById(R.id.nama_organisasi);
            final EditText penanggungJawab = (EditText)v.findViewById(R.id.pj_kegiatan);

            builder.setPositiveButton("Add Kegiatan", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String kegiatan = namaKegiatan.getText().toString().trim();
                    String namaOrg = namaOrganisasi.getText().toString().trim();
                    String pj = penanggungJawab.getText().toString().trim();

                    if(TextUtils.isEmpty(kegiatan)){
                        namaKegiatan.setError("Enter Activity's Name!");
                        return;
                    }
                    if(TextUtils.isEmpty(namaOrg)){
                        namaOrganisasi.setError("Enter Organization's Name!");
                        return;
                    }
                    if(TextUtils.isEmpty(pj)){
                        penanggungJawab.setError("Enter PIC's Name!");
                        return;
                    }
                    final Kegiatan keg = new Kegiatan(kegiatan,pj,namaOrg);

                    //ref.child("Organisasi").child("Nama Organisasi").child(namaOrg).child(kegiatan).setValue(pj);
                    //ref.child("Organisasi").child("Nama Organisasi").child(namaOrg).child(kegiatan).setValue(keg);
                    ref.child("Organisasi").child(keg.getNamaKegiatan()).setValue(keg);


                    Toast.makeText(MainActivity.this, "Your activity has been created", Toast.LENGTH_SHORT).show();

                    dialogInterface.dismiss();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_dashboard){
            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }else if(id == R.id.nav_org){
            startActivity(new Intent(this,Organization.class));
        }else if(id == R.id.nav_timeline){
            Calendar cal = new Calendar();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, cal);
            fragmentTransaction.commit();
        }else if(id == R.id.nav_exit){
            mAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
