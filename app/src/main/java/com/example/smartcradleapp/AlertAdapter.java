package com.example.smartcradleapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertViewHolder> {

    private ArrayList<AlertItem> alertList;

    public AlertAdapter(ArrayList<AlertItem> alertList) {
        this.alertList = alertList;
    }

    @NonNull
    @Override
    public AlertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_item, parent, false);
        return new AlertViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlertViewHolder holder, int position) {
        AlertItem alert = alertList.get(position);
        holder.message.setText(alert.getMessage());
        holder.timestamp.setText(alert.getTimestamp());
    }

    @Override
    public int getItemCount() {
        return alertList.size();
    }

    public static class AlertViewHolder extends RecyclerView.ViewHolder {
        TextView message, timestamp;

        public AlertViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.alert_message);
            timestamp = itemView.findViewById(R.id.alert_timestamp);
        }
    }
}