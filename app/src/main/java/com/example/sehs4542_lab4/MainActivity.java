package com.example.sehs4542_lab4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnOpenRegisterForm = findViewById(R.id.btnOpenRegisterForm);
        Button btnOpenLifecycle = findViewById(R.id.btnOpenLifecycle);
        Button btnOpenSeasonSelector = findViewById(R.id.btnOpenSeasonSelector);

        btnOpenRegisterForm.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        btnOpenLifecycle.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LifecycleActivity.class);
            startActivity(intent);
        });
        
        btnOpenSeasonSelector.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SeasonSelectorActivity.class);
            startActivity(intent);
        });
    }
}