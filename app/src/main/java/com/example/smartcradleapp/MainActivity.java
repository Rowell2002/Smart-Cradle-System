package com.example.smartcradleapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView alertText;
    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertText = findViewById(R.id.alertText);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("alerts");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                StringBuilder alerts = new StringBuilder();
                for (DataSnapshot child : snapshot.getChildren()) {
                    alerts.append(child.getKey()).append(": ")
                            .append(child.getValue().toString()).append("\n");
                }
                alertText.setText(alerts.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                alertText.setText("Error: " + error.getMessage());
            }
        });
    }
}