package com.example.sehs4542_lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SeasonDetailActivity extends AppCompatActivity {

    private TextView tvSeasonName;
    private ImageView ivSeasonImage;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_detail);
        
        // Initialize views
        tvSeasonName = findViewById(R.id.tvSeasonName);
        ivSeasonImage = findViewById(R.id.ivSeasonImage);
        
        // Get data from intent
        Intent intent = getIntent();
        if (intent != null) {
            // Get season information from extras
            String seasonText = intent.getStringExtra("SEASON_TEXT");
            int imageRes = intent.getIntExtra("SEASON_IMAGE_RES", -1);
            String toastMessage = intent.getStringExtra("SEASON_TOAST_MESSAGE");
            
            // Set the season name text
            tvSeasonName.setText(getString(R.string.season_detail_format, seasonText));
            
            // Set the season image if available
            if (imageRes != -1) {
                ivSeasonImage.setImageResource(imageRes);
            }
            
            // Show toast message if available
            if (toastMessage != null && !toastMessage.isEmpty()) {
                showCustomToast(toastMessage);
            }
        }
    }
    
    /**
     * Show a custom toast with the given message
     * 
     * @param message The message to display in the toast
     */
    private void showCustomToast(String message) {
        // Create custom toast
        Toast toast = new Toast(getApplicationContext());
        
        // Set longer display duration
        toast.setDuration(Toast.LENGTH_LONG);
        
        // Use custom layout
        View view = LayoutInflater.from(this).inflate(R.layout.custom_toast, null);
        
        // Set title and details
        TextView tvTitle = view.findViewById(R.id.tvToastTitle);
        TextView tvDetails = view.findViewById(R.id.tvToastDetails);
        
        tvTitle.setText("Season Information");
        tvDetails.setText(message);
        
        // Set view for the toast
        toast.setView(view);
        
        // Show toast
        toast.show();
    }
}
