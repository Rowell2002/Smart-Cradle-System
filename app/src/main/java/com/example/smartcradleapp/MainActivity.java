package com.example.smartcradleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    CardView cardFire, cardWetBed, cardSound, cardSecurity, cardSleepAnalysis;
    TextView latestAlert;
    Switch themeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load theme from SharedPreferences before setting content view
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isDark = prefs.getBoolean("dark_mode", false);
        AppCompatDelegate.setDefaultNightMode(
                isDark ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        latestAlert = findViewById(R.id.latestAlert);
        themeSwitch = findViewById(R.id.themeSwitch);

        cardFire = findViewById(R.id.card_fire);
        cardWetBed = findViewById(R.id.card_wetbed);
        cardSound = findViewById(R.id.card_sound);
        cardSecurity = findViewById(R.id.card_security);
        cardSleepAnalysis = findViewById(R.id.card_sleep_analysis);

        // Set theme switch based on saved preference
        themeSwitch.setChecked(isDark);
        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("dark_mode", isChecked);
            editor.apply();
            AppCompatDelegate.setDefaultNightMode(
                    isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
            );
            recreate(); // Restart activity to apply the theme
        });


    }




}