package com.example.smartcradleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AlertHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AlertAdapter adapter;
    ArrayList<AlertItem> alertList = new ArrayList<>();
    DatabaseReference alertRef;
    TextView header;

    String alertType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_history);

        header = findViewById(R.id.alert_header);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AlertAdapter(alertList);
        recyclerView.setAdapter(adapter);

        alertType = getIntent().getStringExtra("alertType");
        header.setText(getHeaderText(alertType));

        alertRef = FirebaseDatabase.getInstance().getReference("alerts").child(alertType);

        alertRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                alertList.clear();
                for (DataSnapshot alertSnapshot : snapshot.getChildren()) {
                    String message = alertSnapshot.child("message").getValue(String.class);
                    String timestamp = alertSnapshot.child("timestamp").getValue(String.class);
                    if (message != null && timestamp != null) {
                        alertList.add(new AlertItem(message, timestamp));
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AlertHistoryActivity.this, "Failed to load alerts: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getHeaderText(String alertType) {
        switch (alertType) {
            case "fire": return "🔥 Fire Alert History";
            case "wetBed": return "💧 Wet Bed Alert History";
            case "sound": return "🔊 Sound Alert History";
            case "security": return "🚨 Security Alert History";
            default: return "Alert History";
        }
    }
}