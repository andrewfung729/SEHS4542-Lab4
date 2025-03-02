package com.example.sehs4542_lab4;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
        
        // Make root layout focusable to capture focus from EditText
        findViewById(R.id.main_register).setFocusableInTouchMode(true);

        // Set up register button click listener
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide keyboard
                hideKeyboard();
                
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
    
    /**
     * Hide the soft keyboard
     */
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            currentFocus.clearFocus();
        }
    }
    
    /**
     * Override dispatchTouchEvent to detect touches outside input fields
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View currentFocus = getCurrentFocus();
            if (currentFocus instanceof TextInputEditText) {
                // Get touch coordinates and view position
                float x = event.getRawX();
                float y = event.getRawY();
                int[] location = new int[2];
                currentFocus.getLocationOnScreen(location);
                
                // Check if touch is outside the focused view
                if (x < location[0] || x > location[0] + currentFocus.getWidth() ||
                    y < location[1] || y > location[1] + currentFocus.getHeight()) {
                    // Transfer focus to parent layout and hide keyboard
                    findViewById(R.id.main_register).requestFocus();
                    hideKeyboard();
                }
            }
        }
        return super.dispatchTouchEvent(event);
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
