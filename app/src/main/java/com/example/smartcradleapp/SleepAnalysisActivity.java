package com.example.smartcradleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.*;

public class SleepAnalysisActivity extends AppCompatActivity {

    TextView analysisText;
    DatabaseReference alertsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_analysis);

        analysisText = findViewById(R.id.analysisText);
        alertsRef = FirebaseDatabase.getInstance().getReference("alertHistory");

        alertsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int totalAlerts = 0;
                int soundAlerts = 0;
                int wetBedAlerts = 0;
                int fireAlerts = 0;
                int securityAlerts = 0;

                for (DataSnapshot alert : snapshot.getChildren()) {
                    totalAlerts++;
                    String type = alert.child("type").getValue(String.class);
                    if (type == null) continue;

                    switch (type) {
                        case "sound":
                            soundAlerts++;
                            break;
                        case "wetBed":
                            wetBedAlerts++;
                            break;
                        case "fire":
                            fireAlerts++;
                            break;
                        case "security":
                            securityAlerts++;
                            break;
                    }
                }

                String result = "Total Alerts Logged: " + totalAlerts + "\n\n"
                        + "🔊 Sound Alerts: " + soundAlerts + "\n"
                        + "💧 Wet Bed Alerts: " + wetBedAlerts + "\n"
                        + "🔥 Fire Alerts: " + fireAlerts + "\n"
                        + "🚨 Security Alerts: " + securityAlerts + "\n\n";

                if (soundAlerts > 10 || wetBedAlerts > 5) {
                    result += "⚠️ Baby's sleep is likely disturbed frequently.\nConsider checking noise and comfort levels.";
                } else {
                    result += "✅ Baby seems to be sleeping peacefully most of the time.";
                }

                analysisText.setText(result);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                analysisText.setText("Error loading analysis.");
            }
        });
    }
}