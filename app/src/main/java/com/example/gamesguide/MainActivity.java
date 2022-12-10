package com.example.gamesguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.gamesguide.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private ActivityMainBinding binding;

    RecyclerView recyclerView;
    String nama[], diff[], role[];
    int img[] = {R.drawable.gwen_0, R.drawable.sett_0, R.drawable.diana_0, R.drawable.darius_0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //code work manager
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueueUniqueWork("Notifikasi",
                        ExistingWorkPolicy.REPLACE, request);
            }
        });

        //mengganti actionbar dengan toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //memanggil drawer_layout dari activity_main.xml
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //membuat hamburger icon pada toolbar dan animasinya
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        toggle.syncState();

        //code recycler view
        recyclerView = findViewById(R.id.rvChamp);
        nama = getResources().getStringArray(R.array.champion);
        diff = getResources().getStringArray(R.array.difficulty);
        role = getResources().getStringArray(R.array.role);
        ChampionAdapter cAdapter = new ChampionAdapter(this, nama, diff, role, img);
        recyclerView.setAdapter(cAdapter);
        LinearLayoutManager lm = new LinearLayoutManager((this),
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(lm);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_champ:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentChamp()).addToBackStack(null).commit();
                break;
            case R.id.nav_build:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentBuild()).addToBackStack(null).commit();
                break;
            case R.id.nav_items:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentItems()).addToBackStack(null).commit();
                break;
            case R.id.nav_alarm:
                Intent mAlarm = new Intent(MainActivity.this, AlarmMenu.class);
                startActivity(mAlarm);
                break;
            case R.id.nav_cropping:
                Intent mCrop = new Intent(MainActivity.this, CroppingImg.class);
                startActivity(mCrop);
                break;
            case R.id.nav_restapi:
                Intent mRest = new Intent(MainActivity.this, RestApi.class);
                startActivity(mRest);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //drawer menu fragment handler
    //on back press behavior
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    public void menuPertama(View view){
        Intent intent = new Intent(getApplicationContext(), MenuKedua.class);
        startActivity(intent);
    }

    public void menuKedua(View view){
        Intent intent = new Intent(getApplicationContext(), MenuKetiga.class);
        startActivity(intent);
    }

    public void menuKetiga(View view){
        Intent intent = new Intent(getApplicationContext(), MenuKeempat.class);
        startActivity(intent);
    }

    public void menuKeempat(View view){
        Intent intent = new Intent(getApplicationContext(), MenuKelima.class);
        startActivity(intent);
    }
}