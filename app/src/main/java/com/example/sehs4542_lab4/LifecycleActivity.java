package com.example.sehs4542_lab4;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LifecycleActivity extends AppCompatActivity {

    private TextView tvLifecycleEvents;
    private StringBuilder eventLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_lifecycle), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        tvLifecycleEvents = findViewById(R.id.tvLifecycleEvents);
        Button btnGoBack = findViewById(R.id.btnGoBack);
        
        // Initialize event log
        if (savedInstanceState != null) {
            eventLog = new StringBuilder(savedInstanceState.getString("eventLog", ""));
        } else {
            eventLog = new StringBuilder();
        }
        
        // Set up Go Back button
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        // Log onCreate event
        logLifecycleEvent("onCreate");
        // showToast(getString(R.string.lifecycle_created));
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        logLifecycleEvent("onStart");
        // showToast(getString(R.string.lifecycle_started));
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        logLifecycleEvent("onResume");
        showToast(getString(R.string.lifecycle_resumed));
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        logLifecycleEvent("onPause");
        showToast(getString(R.string.lifecycle_paused));
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        logLifecycleEvent("onStop");
        // showToast(getString(R.string.lifecycle_stopped));
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        logLifecycleEvent("onDestroy");
        showToast(getString(R.string.lifecycle_destroyed));
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("eventLog", eventLog.toString());
    }
    
    /**
     * Log lifecycle event with timestamp
     */
    private void logLifecycleEvent(String event) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());
        String timestamp = sdf.format(new Date());
        
        eventLog.append(timestamp).append(" - ").append(event).append("\n");
        tvLifecycleEvents.setText(eventLog.toString());
    }
    
    /**
     * Show a custom toast message
     */
    private void showToast(String message) {
        // Create custom toast
        Toast toast = new Toast(getApplicationContext());
        
        // Set longer display duration
        toast.setDuration(Toast.LENGTH_LONG);
        
        // Use custom layout
        View view = LayoutInflater.from(this).inflate(R.layout.custom_toast, null);
        
        // Set title and details
        TextView tvTitle = view.findViewById(R.id.tvToastTitle);
        TextView tvDetails = view.findViewById(R.id.tvToastDetails);
        
        tvTitle.setText("Activity Lifecycle");
        tvDetails.setText(message);
        
        // Set view for the toast (DO NOT set gravity - will be ignored in newer Android versions)
        toast.setView(view);
        
        // Show toast
        toast.show();
    }
}
