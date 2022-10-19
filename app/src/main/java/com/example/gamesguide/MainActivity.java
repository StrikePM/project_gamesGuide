package com.example.gamesguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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