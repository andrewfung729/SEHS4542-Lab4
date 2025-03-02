package com.example.sehs4542_lab4;

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

import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText etUsername, etAge, etPassword, etConfirmPassword, etEmail;
    private TextInputLayout tilUsername, tilAge, tilPassword, tilConfirmPassword, tilEmail;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        initializeViews();

        // Set up register button click listener
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear all error messages
                clearAllErrors();
                
                // Get user inputs
                String username = etUsername.getText().toString().trim();
                String age = etAge.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();
                String email = etEmail.getText().toString().trim();

                // Validate inputs
                if (validateInput(username, age, password, confirmPassword, email)) {
                    // Show custom toast message
                    showCustomToast("You have registered successfully! Here is your information:", 
                            "Username: " + username, 
                            "Age: " + age, 
                            "Email: " + email);
                }
            }
        });
    }

    private void initializeViews() {
        // Initialize TextInputEditText fields
        etUsername = findViewById(R.id.etUsername);
        etAge = findViewById(R.id.etAge);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etEmail = findViewById(R.id.etEmail);
        
        // Initialize TextInputLayout fields
        tilUsername = (TextInputLayout) etUsername.getParent().getParent();
        tilAge = (TextInputLayout) etAge.getParent().getParent();
        tilPassword = (TextInputLayout) etPassword.getParent().getParent();
        tilConfirmPassword = (TextInputLayout) etConfirmPassword.getParent().getParent();
        tilEmail = (TextInputLayout) etEmail.getParent().getParent();
        
        btnRegister = findViewById(R.id.btnRegister);
    }

    private void clearAllErrors() {
        tilUsername.setError(null);
        tilAge.setError(null);
        tilPassword.setError(null);
        tilConfirmPassword.setError(null);
        tilEmail.setError(null);
    }

    private boolean validateInput(String username, String age, String password, String confirmPassword, String email) {
        boolean isValid = true;
        
        // Check username
        if (username.isEmpty()) {
            tilUsername.setError("Username is required");
            isValid = false;
        }

        // Check password
        if (password.isEmpty()) {
            tilPassword.setError("Password is required");
            isValid = false;
        }

        // Check confirm password
        if (confirmPassword.isEmpty()) {
            tilConfirmPassword.setError("Confirm Password is required");
            isValid = false;
        } else if (!password.equals(confirmPassword)) {
            // Check if passwords match
            tilConfirmPassword.setError("Passwords do not match");
            isValid = false;
        }

        // Check email
        if (email.isEmpty()) {
            tilEmail.setError("Email is required");
            isValid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("Please enter a valid email address");
            isValid = false;
        }

        return isValid;
    }

    private void showCustomToast(String title, String... details) {
        // Create custom toast
        Toast toast = new Toast(getApplicationContext());
        
        // Set longer display duration
        toast.setDuration(Toast.LENGTH_LONG);
        
        // Use custom layout
        View view = LayoutInflater.from(this).inflate(R.layout.custom_toast, null);
        
        // Set title and details
        TextView tvTitle = view.findViewById(R.id.tvToastTitle);
        TextView tvDetails = view.findViewById(R.id.tvToastDetails);
        
        tvTitle.setText(title);
        
        // Build details string
        StringBuilder detailsBuilder = new StringBuilder();
        for (String detail : details) {
            detailsBuilder.append(detail).append("\n");
        }
        tvDetails.setText(detailsBuilder.toString());
        
        // Set toast position and view
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(view);
        
        // Show toast
        toast.show();
    }
}
