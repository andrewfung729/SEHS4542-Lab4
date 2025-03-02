package com.example.sehs4542_lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SeasonSelectorActivity extends AppCompatActivity {

    private Button btnSpring, btnSummer, btnAutumn, btnWinter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_selector);

        // Initialize buttons
        btnSpring = findViewById(R.id.btnSpring);
        btnSummer = findViewById(R.id.btnSummer);
        btnAutumn = findViewById(R.id.btnAutumn);
        btnWinter = findViewById(R.id.btnWinter);

        // Set click listeners for all season buttons
        setupSeasonButton(btnSpring, "spring", R.string.season_spring, R.drawable.spring);
        setupSeasonButton(btnSummer, "summer", R.string.season_summer, R.drawable.summer);
        setupSeasonButton(btnAutumn, "autumn", R.string.season_autumn, R.drawable.autumn);
        setupSeasonButton(btnWinter, "winter", R.string.season_winter, R.drawable.winter);
    }

    /**
     * Set up click listener for a season button
     * 
     * @param button        The button to set up
     * @param seasonName    The season name in lowercase
     * @param stringResId   The string resource ID for the season
     * @param drawableResId The drawable resource ID for the season image
     */
    private void setupSeasonButton(Button button, final String seasonName, final int stringResId, final int drawableResId) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to open season detail activity
                Intent intent = new Intent(SeasonSelectorActivity.this, SeasonDetailActivity.class);
                
                // Put season information as extras
                intent.putExtra("SEASON_NAME", seasonName);
                intent.putExtra("SEASON_TEXT", getString(stringResId));
                intent.putExtra("SEASON_IMAGE_RES", drawableResId);
                
                // If we have toast messages for the seasons
                int toastMessageId = getToastMessageId(seasonName);
                if (toastMessageId != 0) {
                    intent.putExtra("SEASON_TOAST_MESSAGE", getString(toastMessageId));
                }
                
                // Start the detail activity
                startActivity(intent);
            }
        });
    }
    
    /**
     * Get the toast message resource ID for a given season
     * 
     * @param seasonName The season name
     * @return The resource ID, or 0 if not found
     */
    private int getToastMessageId(String seasonName) {
        switch (seasonName) {
            case "spring":
                return R.string.season_toast_spring;
            case "summer":
                return R.string.season_toast_summer;
            case "autumn":
                return R.string.season_toast_autumn;
            case "winter":
                return R.string.season_toast_winter;
            default:
                return 0;
        }
    }
}
