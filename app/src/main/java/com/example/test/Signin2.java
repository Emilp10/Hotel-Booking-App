package com.example.test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Signin2 extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnSignIn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin2);

        mAuth = FirebaseAuth.getInstance();

        // Initialize EditText and Button
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        // Set click listener for the Sign In button
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    // Method to handle sign in
    private void signIn() {
        // Get the email and password entered by the user
        String t1 = edtEmail.getText().toString().trim();
        String t2 = edtPassword.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing In...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Check if email or password is empty
        if (t1.isEmpty() || t2.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        } else {
            mAuth.signInWithEmailAndPassword(t1, t2)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            edtPassword.setText("");
                            progressDialog.dismiss();

                            Toast.makeText(this, "Sign In Successful!", Toast.LENGTH_SHORT).show();

                            // Navigate to the Home2 activity
                            Intent intent = new Intent(Signin2.this, Home2.class);
                            startActivity(intent);
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                            edtEmail.setText("");
                            edtPassword.setText("");
                        }
                    });
        }


}
}